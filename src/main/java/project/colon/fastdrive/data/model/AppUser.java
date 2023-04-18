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
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;
    private String password;
    private String createdAt;
    private Boolean isEnabled;
    @Enumerated(EnumType.STRING)
    private Set <Role> roles;
    private String profileImg;
}
