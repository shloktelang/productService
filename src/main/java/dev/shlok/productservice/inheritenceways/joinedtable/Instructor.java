package dev.shlok.productservice.inheritenceways.joinedtable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "jt_instructor")
@PrimaryKeyJoinColumn(name = "user_id")
// here we are just naming the primary key column for superclass to user_id
public class Instructor extends User {
    private boolean isHandsome;
}
