package project.colon.fastdrive.data.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int cvv;
    private String cardNumber;
    private LocalDate expiryDate;
    @ManyToOne
    private Passenger passenger;
}
