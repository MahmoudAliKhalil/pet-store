package eg.gov.iti.jets.petstore.entities;

import eg.gov.iti.jets.petstore.enums.Gender;
import eg.gov.iti.jets.petstore.enums.Roles;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "USER_INFO")
@Data
@Inheritance
@DiscriminatorColumn(name = "USER_TYPE")
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "phone_number", length = 11)
    private String phoneNumber;
    private Address address;
    @Enumerated(EnumType.STRING)
    private Roles role;
    private String userName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "birth_date")
    private LocalDate birthDate;
}
