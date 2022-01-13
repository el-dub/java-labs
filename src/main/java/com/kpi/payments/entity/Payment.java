package com.kpi.payments.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class Payment extends Entity {
    private long paymentId;
    private LocalDateTime paymentTime;
    private long senderAccount;
    private long recipientAccount;
    private BigDecimal paymentAmount;
    private String paymentStatus;
}
