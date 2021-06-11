package philosopherAPI;
import org.assertj.core.api.Assertions;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import philosopherAPI.model.DateOfBirth;
import philosopherAPI.model.DateOfDeath;

import java.io.IOException;
import java.time.Month;

@SpringBootTest
class PhilosopherScraperTests {
    @Autowired
    PhilosopherScraper philosopherScraper;

    @Test
    public void scrapeDateOfBirth_shouldReturnNewInstanceOfDateOfBirth() throws IOException {
        Document document = Jsoup.connect("https://www.britannica.com/biography/Voltaire").get();

        DateOfBirth expectedDOB = new DateOfBirth();
        expectedDOB.setBirthYear(1694);
        expectedDOB.setBirthMonth(Month.NOVEMBER);
        expectedDOB.setBirthDay(21);

        DateOfBirth actualDateOfBirth = philosopherScraper.scrapeDayOfBirth(document);

        Assertions.assertThat(actualDateOfBirth)
            .isEqualToComparingFieldByField(expectedDOB);
    }

    @Test
    public void scrapeDateOfDeath_shouldReturnNewInstanceOfDateOfDeath() throws IOException {
        Document document = Jsoup.connect("https://www.britannica.com/biography/Voltaire").get();

        DateOfDeath expectedDOD = new DateOfDeath();
        expectedDOD.setDeathYear(1778);
        expectedDOD.setDeathMonth(Month.MAY);
        expectedDOD.setDeathDay(30);

        DateOfDeath actualDateOfDeath = philosopherScraper.scrapeDateOfDeath(document);

        Assertions.assertThat(actualDateOfDeath)
            .isEqualToComparingFieldByField(expectedDOD);
    }
}