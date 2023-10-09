package dev.shlok.productservice.inheritenceways.mappedsuperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "msc_mentor")
public class Mentor extends User {
    private int numberOfSessions;
    private int numberOfMentees;
}
