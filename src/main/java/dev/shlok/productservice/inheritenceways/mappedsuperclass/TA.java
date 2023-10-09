package dev.shlok.productservice.inheritenceways.mappedsuperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "msc_user")
public class TA extends User{
    private double averageRating;
}
