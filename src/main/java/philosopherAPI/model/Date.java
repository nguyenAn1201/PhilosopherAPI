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
public class Date {
    private Month month;
    private Integer day;
    private Integer year;
}
