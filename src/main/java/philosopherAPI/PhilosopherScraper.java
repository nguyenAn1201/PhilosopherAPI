package philosopherAPI;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import philosopherAPI.model.Date;
import philosopherAPI.model.Philosopher;

import java.io.IOException;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PhilosopherScraper implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(PhilosopherScraper.class);

    @Override
    public void run(String[] args) throws IOException {
        List<String> philosophersURLS = scrapeURLs();
        List<Philosopher> philosophersBios = scrapePhilosopherBios(philosophersURLS);

    }

    public List<Philosopher> scrapePhilosopherBios(List<String> philosophersURLS) throws IOException {
        return philosophersURLS.stream().map(url -> {
            try {
                Document doc = Jsoup.connect(url).get();
                String name = doc.select(".bio-box.fact-box > .title").text();
                String title = doc.select(".topic-identifier").text();
                String description = doc.select(".topic-paragraph").first().text();

                Date dob = scrapeDayOfBirth(doc);
                Date dod = scrapeDateOfDeath(doc);

                List<String> notableWorks = doc.select("dl[data-label='notable works'] > dd > ul > li").eachText();
//                List<NotableWork> notableWorks = notableWorks.stream().map(work -> {
//                    NotableWork notableWork = new NotableWork();
//                    notableWork.setUrl();
//                    notableWork.setTitle();
//                    return notableWorks;
//                })

                Philosopher newPhilosopher = new Philosopher();
                newPhilosopher.setName(name);
                newPhilosopher.setTitle(title);
                newPhilosopher.setDescription(description);
                newPhilosopher.setBirthMonth(dob.getMonth());
                newPhilosopher.setBirthDay(dob.getDay());
                newPhilosopher.setBirthYear(dob.getYear());
                newPhilosopher.setDeathMonth(dod.getMonth());
                newPhilosopher.setDeathDay(dod.getDay());
                newPhilosopher.setDeathYear(dod.getYear());
                newPhilosopher.setNotableWorks(null);

                return newPhilosopher;
            } catch (IOException e) {
                logger.info("Failed to scrape philosophers biographies! Error: {}", e.getMessage());
                return null;
            }
        }).collect(Collectors.toList());
    }

    public Date scrapeDayOfBirth(Document document) {
        Date date = new Date();

        List<String> dob = Arrays.asList((document.select("dl[data-label='born'] > dd, dl[data-label='baptized'] > dd").text().split(" ")));
        if (dob.contains("BCE")) {
            date.setYear(Integer.parseInt(dob.get(dob.indexOf("BCE") - 1)));
        } else {
            if (Arrays.toString(Month.values()).contains(dob.get(0).toUpperCase())) {
                date.setMonth(Month.valueOf(dob.get(0).toUpperCase()));
                date.setDay(Integer.parseInt(dob.get(1).split(",")[0]));
                date.setYear(Integer.parseInt(dob.get(2)));
            } else {
                date.setYear(Integer.parseInt(dob.get(0)));
            }
        }
        return date;
    }

    public Date scrapeDateOfDeath(Document document) {
        Date dateOfDeath = new Date();
        List<String> dod = Arrays.asList(document.select("dl[data-label='died'] > dd").text().split(" "));

        if (dod.size() > 1) {
            if (dod.contains("BCE")) {
                dateOfDeath.setYear(Integer.parseInt(dod.get(dod.indexOf("BCE") -1)));
            } else {
                dateOfDeath.setMonth(Month.valueOf(dod.get(0).toUpperCase()));
                dateOfDeath.setDay(Integer.parseInt(dod.get(1).split(",")[0]));
                dateOfDeath.setYear(Integer.parseInt(dod.get(2)));
            }
        }

        return dateOfDeath;
    }

    private List<String> scrapeURLs() throws IOException {
        Document doc = Jsoup.connect("https://www.britannica.com/browse/Philosophers").get();
        List<String> philosopherURLS = doc.select(".card-body > a").eachAttr("href");

        return philosopherURLS.stream()
            .map(url -> "https://www.britannica.com"+url)
            .collect(Collectors.toList());
    }
}
