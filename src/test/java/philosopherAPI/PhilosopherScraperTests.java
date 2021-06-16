package philosopherAPI;
import org.assertj.core.api.Assertions;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import philosopherAPI.model.Date;

import java.io.IOException;
import java.time.Month;

@SpringBootTest
class PhilosopherScraperTests {
    @Autowired
    PhilosopherScraper philosopherScraper;

    @Test
    public void scrapeDateOfBirth_shouldReturnNewInstanceOfDateOfBirth() throws IOException {
        Document document = Jsoup.connect("https://www.britannica.com/biography/Voltaire").get();

        Date expectedDOB = new Date();
        expectedDOB.setMonth(Month.NOVEMBER);
        expectedDOB.setDay(21);
        expectedDOB.setYear(1694);

        Date actualDate = philosopherScraper.scrapeDayOfBirth(document);

        Assertions.assertThat(actualDate)
            .isEqualToComparingFieldByField(expectedDOB);
    }

    @Test
    public void scrapeDateOfDeath_shouldReturnNewInstanceOfDateOfDeath() throws IOException {
        Document document = Jsoup.connect("https://www.britannica.com/biography/Voltaire").get();

        Date expectedDOD = new Date();
        expectedDOD.setMonth(Month.MAY);
        expectedDOD.setDay(30);
        expectedDOD.setYear(1778);

        Date actualDateOfDeath = philosopherScraper.scrapeDateOfDeath(document);

        Assertions.assertThat(actualDateOfDeath)
            .isEqualToComparingFieldByField(expectedDOD);
    }
}