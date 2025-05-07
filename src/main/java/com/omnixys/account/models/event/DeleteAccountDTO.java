package com.omnixys.account.models.event;

import com.omnixys.account.security.CustomUserDetails;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Datenübertragungsobjekt für ein neues Kundenkonto, gesendet vom Customer-Service.
 *
 */
public record DeleteAccountDTO(
    UUID id,
    String username
) {}
