package com.omnixys.account.models.dto;

import com.omnixys.account.models.enums.AccountType;

import java.util.UUID;

public record AccountDTO(
    int transactionLimit,
    UUID userId,
    String username,
    AccountType category
) {

}
