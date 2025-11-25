package com.cqgc.dto;

import lombok.Data;

@Data
public class TransferVO {
    private String fromName;
    private String toName;
    private Double amount;
}
