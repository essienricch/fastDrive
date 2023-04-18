package project.colon.fastdrive.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Referee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;
    private int age;
    @OneToOne
    private Address address;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Gender gender;


    private String occupation;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set <Driver> drivers;



}
