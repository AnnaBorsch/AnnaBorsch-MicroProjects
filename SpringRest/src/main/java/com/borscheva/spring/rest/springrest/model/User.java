package com.borscheva.spring.rest.springrest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private long id;
    private String name;
    private String lastName;
    private Byte age;

}
