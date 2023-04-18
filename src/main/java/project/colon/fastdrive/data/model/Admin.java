package project.colon.fastdrive.data.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long adminId;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private AppUser user;
    private String employeeId;
}
