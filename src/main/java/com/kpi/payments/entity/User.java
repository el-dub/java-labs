package com.kpi.payments.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class User extends Entity {
    private long userId;
    private RoleType role;
    private String lastName;
    private String firstName;
    private String middleName;
    private String phone;
    private String email;
    private LocalDate dateOfBirth;
    private boolean locked;
}
