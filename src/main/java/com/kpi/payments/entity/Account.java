package com.kpi.payments.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Account extends Entity {
    private long accountNumber;
    private BigDecimal moneyAmount;
    private boolean locked;
    private String accountName;
}
