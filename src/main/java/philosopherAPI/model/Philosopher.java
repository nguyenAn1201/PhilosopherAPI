package philosopherAPI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Month;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "philosophers")
public class Philosopher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private String title;

    @Column(name = "birth_month")
    @Enumerated(EnumType.STRING)
    private Month birthMonth;
    @Column(name = "birth_day")
    private Integer birthDay;
    @Column(name = "birth_year")
    private Integer birthYear;

    @Column(name="birth_country")
    private String birthCountry;
    @Column(name="birth_city")
    private String birthCity;

    @Column(name="death_month")
    @Enumerated(EnumType.STRING)
    private Month deathMonth;
    @Column(name = "death_day")
    private Integer deathDay;
    @Column(name = "death_year")
    private Integer deathYear;

    @Column(name="death_country")
    private String deathCountry;
    @Column(name="death_city")
    private String deathCity;

    @Column(nullable = false)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "philosopher")
    private List<NotableWork> notableWorks;

    @Override
    public String toString() {
        return String.format(
            "Philosopher[id=%d, name='%s', title='%s', DOB='%s/%d/%d', DOD='%s/%d/%d', description='%s']",
            id, name, title, birthMonth.toString(), birthDay, birthYear, deathMonth.toString(), deathDay,
            deathYear, description
        );
    }
}
