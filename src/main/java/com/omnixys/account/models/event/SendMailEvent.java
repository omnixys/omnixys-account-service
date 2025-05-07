package com.omnixys.account.models.event;

import com.omnixys.account.models.entities.Account;

import java.util.UUID;

public record SendMailEvent(
    UUID id
) {
    public static SendMailEvent fromEntity(final Account account) {
        return new SendMailEvent(
            account.getId()
        );
    }
}
