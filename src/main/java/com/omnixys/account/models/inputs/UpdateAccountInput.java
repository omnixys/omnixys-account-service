package com.omnixys.account.models.inputs;

import com.omnixys.account.models.enums.StatusType;

import java.math.BigDecimal;

public record UpdateAccountInput(
    int transactionLimit,
    BigDecimal balance,
    StatusType state
) {

}
