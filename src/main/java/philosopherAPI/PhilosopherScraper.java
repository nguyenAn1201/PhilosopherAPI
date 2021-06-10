package philosopherAPI;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import philosopherAPI.model.Philosopher;

import java.io.IOException;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class PhilosopherScraper implements CommandLineRunner {
    @Override
    public void run(String[] args) throws IOException {
        List<String> philosophersURLS = scrapeURLs();
        List<Philosopher> philosophersBios = scrapePhilosopherBios(philosophersURLS);
        System.out.println(philosophersBios);

    }

    private List<Philosopher> scrapePhilosopherBios(List<String> philosophersURLS) throws IOException {
        return philosophersURLS.stream().map(url -> {
            try {
                Document doc = Jsoup.connect(url).get();
                String name = doc.select(".bio-box.fact-box > .title").text();
                String title = doc.select(".topic-identifier").text();
                String description = doc.select(".topic-paragraph").first().text();

                Month birthMonth = null;
                Integer birthDay = null, birthYear;
                List<String> dob = Arrays.asList((doc.select("dl[data-label='born'] > dd, dl[data-label='baptized'] > dd").text().split(" ")));

                if (dob.contains("BCE")) {
                    birthYear = Integer.parseInt(dob.get(dob.indexOf("BCE") - 1));
                } else {
                    birthMonth = Month.valueOf(dob.get(0).toUpperCase());
                    birthDay = Integer.parseInt(dob.get(1).split(",")[0]);
                    birthYear = Integer.parseInt(dob.get(2));
                }

                List<String> dod = Arrays.asList(doc.select("dl[data-label='died'] > dd").text().split(" "));
                Month deathMonth = null;
                Integer deathDay = null, deathYear = null;
                if (dod.size() > 1) {
                    if (dod.contains("BCE")) {
                        deathYear = Integer.parseInt(dob.get(dob.indexOf("BCE") -1));
                    } else {
                        deathMonth = Month.valueOf(dod.get(0).toUpperCase());
                        deathDay = Integer.parseInt(dod.get(1).split(",")[0]);
                        deathYear = Integer.parseInt(dod.get(2));
                    }
                }

                List<String> notableWorks = doc.select("dl[data-label='notable works'] > dd > ul > li").eachText();

                Philosopher newPhilosopher = new Philosopher();
                newPhilosopher.setName(name);
                newPhilosopher.setTitle(title);
                newPhilosopher.setDescription(description);
                newPhilosopher.setBirthMonth(birthMonth);
                newPhilosopher.setBirthDay(birthDay);
                newPhilosopher.setBirthYear(birthYear);
                newPhilosopher.setDeathMonth(deathMonth);
                newPhilosopher.setDeathDay(deathDay);
                newPhilosopher.setDeathYear(deathYear);
                newPhilosopher.setNotableWorks(notableWorks);

                return newPhilosopher;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList());
    }

    private List<String> scrapeURLs() throws IOException {
        Document doc = Jsoup.connect("https://www.britannica.com/browse/Philosophers").get();
        List<String> philosopherURLS = doc.select(".card-body > a").eachAttr("href");

        return philosopherURLS.stream()
            .map(url -> "https://www.britannica.com"+url)
            .collect(Collectors.toList());
    }
}
