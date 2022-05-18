package com.bobocode;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private String surname;
    public String sex;
    int age;
}
