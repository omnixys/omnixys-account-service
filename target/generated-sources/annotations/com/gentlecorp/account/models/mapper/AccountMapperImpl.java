package com.gentlecorp.account.models.mapper;

import com.gentlecorp.account.models.entities.Account;
import com.gentlecorp.account.models.enums.AccountType;
import com.gentlecorp.account.models.event.CreateAccountDTO;
import com.gentlecorp.account.models.inputs.CreateAccountInput;
import com.gentlecorp.account.models.inputs.UpdateAccountInput;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-06T14:09:38+0200",
    comments = "version: 1.6.0.Beta2, compiler: javac, environment: Java 23.0.2 (Homebrew)"
)
@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public Account toAccount(CreateAccountInput input) {
        if ( input == null ) {
            return null;
        }

        Account.AccountBuilder account = Account.builder();

        account.category( input.category() );
        account.transactionLimit( input.transactionLimit() );
        account.username( input.username() );
        account.userId( input.userId() );

        return account.build();
    }

    @Override
    public Account toAccount(UpdateAccountInput input) {
        if ( input == null ) {
            return null;
        }

        Account.AccountBuilder account = Account.builder();

        account.balance( input.balance() );
        account.state( input.state() );
        account.transactionLimit( input.transactionLimit() );

        return account.build();
    }

    @Override
    public CreateAccountInput toCreateInput(CreateAccountDTO createAccountDTO) {
        if ( createAccountDTO == null ) {
            return null;
        }

        int transactionLimit = 0;
        UUID userId = null;
        String username = null;
        AccountType category = null;

        transactionLimit = createAccountDTO.transactionLimit();
        userId = createAccountDTO.userId();
        username = createAccountDTO.username();
        if ( createAccountDTO.category() != null ) {
            category = Enum.valueOf( AccountType.class, createAccountDTO.category() );
        }

        CreateAccountInput createAccountInput = new CreateAccountInput( transactionLimit, userId, username, category );

        return createAccountInput;
    }
}
