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
public class DateOfBirth {
    private Month birthMonth;
    private Integer birthDay;
    private Integer birthYear;
}
