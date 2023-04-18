package project.colon.fastdrive.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class BankInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String bankName;
    private String accountName;
    private String accountNumber;

}
