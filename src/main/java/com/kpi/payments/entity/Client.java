package com.kpi.payments.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Client extends Entity {
    private int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private int phone;
}
