package philosopherAPI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Month;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Philosopher {
    private String name;
    private String title;

    private DateOfBirth dateOfBirth;
    private String birthCountry;
    private String birthCity;

    private DateOfDeath dateOfDeath;
    private String deathCountry;
    private String deathCity;

    private String description;

    private List<String> notableWorks;
}
