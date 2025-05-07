package com.omnixys.account.models.inputs;

import com.omnixys.account.models.enums.AccountType;

import java.util.UUID;

public record CreateAccountInput (
    int transactionLimit,
    UUID userId,
    String username,
    AccountType category
) {

}
