package philosopherAPI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Month;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DateOfDeath {
    private Month deathMonth;
    private Integer deathDay;
    private Integer deathYear;
}
