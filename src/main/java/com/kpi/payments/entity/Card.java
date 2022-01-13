package com.kpi.payments.entity;

import lombok.Data;

@Data
public class Card extends Entity {
    private long cardNumber;
    private long userId;
    private long accountNumber;
}
