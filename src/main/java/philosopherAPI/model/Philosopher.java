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

    private Month birthMonth;
    private Integer birthDay;
    private Integer birthYear;
    private String birthCountry;
    private String birthCity;

    private Month deathMonth;
    private Integer deathDay;
    private Integer deathYear;
    private String deathCountry;
    private String deathCity;

    private String description;

    private List<String> notableWorks;
}
