package dev.shlok.productservice.inheritenceways.singletable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.INTEGER)
// Here a single table will be created containing all the columns from the subclasses and superclass
// So, in order to identify the type of user in the DB (TA, mentor etc.) we need a discriminator column
// We'll map the subclasses to a column number and that will be represented in the DB
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String password;
}
