package se.iths.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Size(min = 2, message = "Firstname too short!")
    private String firstName;
    @NotEmpty
    @Size(min = 2, message = "Lastname too short!")
    private String lastName;
    @NotEmpty
    @Email(message = "Invalid email. Try again.")
    private String email;

    @Digits(integer = 15, fraction = 0)
    private String phoneNumber;

}
