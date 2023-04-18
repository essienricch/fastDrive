package project.colon.fastdrive.data.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonUnwrapped
    private AppUser user;
    @OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL)
    private List <Ride> ride;
}
