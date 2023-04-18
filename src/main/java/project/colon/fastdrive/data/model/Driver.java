package project.colon.fastdrive.data.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String phoneNumber;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Address address;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private int age;
    private String licenseId;
    private String licenseImage;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private BankInformation bankInformation;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Referee referee;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private AppUser user;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    private List<Ride> ride;

}
