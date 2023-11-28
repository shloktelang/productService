package dev.shlok.productservice.clients.authenticationclient.dtos;

import dev.shlok.productservice.models.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Role extends BaseModel {
    private String name;

//    private List<User> users
}
