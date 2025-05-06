package com.gentlecorp.account.models.entities;

import com.gentlecorp.account.models.enums.AccountType;
import com.gentlecorp.account.models.enums.StatusType;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@StaticMetamodel(Account.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Account_ {

	public static final String CREATED = "created";
	public static final String OVERDRAFT_LIMIT = "overdraftLimit";
	public static final String RATE_OF_INTEREST = "rateOfInterest";
	public static final String VERSION = "version";
	public static final String USER_ID = "userId";
	public static final String BALANCE = "balance";
	public static final String ID = "id";
	public static final String STATE = "state";
	public static final String TRANSACTION_LIMIT = "transactionLimit";
	public static final String CATEGORY = "category";
	public static final String UPDATED = "updated";
	public static final String USERNAME = "username";

	
	/**
	 * @see com.gentlecorp.account.models.entities.Account#created
	 **/
	public static volatile SingularAttribute<Account, LocalDateTime> created;
	
	/**
	 * @see com.gentlecorp.account.models.entities.Account#overdraftLimit
	 **/
	public static volatile SingularAttribute<Account, BigDecimal> overdraftLimit;
	
	/**
	 * @see com.gentlecorp.account.models.entities.Account#rateOfInterest
	 **/
	public static volatile SingularAttribute<Account, Double> rateOfInterest;
	
	/**
	 * @see com.gentlecorp.account.models.entities.Account#version
	 **/
	public static volatile SingularAttribute<Account, Integer> version;
	
	/**
	 * @see com.gentlecorp.account.models.entities.Account#userId
	 **/
	public static volatile SingularAttribute<Account, UUID> userId;
	
	/**
	 * @see com.gentlecorp.account.models.entities.Account#balance
	 **/
	public static volatile SingularAttribute<Account, BigDecimal> balance;
	
	/**
	 * @see com.gentlecorp.account.models.entities.Account#id
	 **/
	public static volatile SingularAttribute<Account, UUID> id;
	
	/**
	 * @see com.gentlecorp.account.models.entities.Account#state
	 **/
	public static volatile SingularAttribute<Account, StatusType> state;
	
	/**
	 * @see com.gentlecorp.account.models.entities.Account#transactionLimit
	 **/
	public static volatile SingularAttribute<Account, Integer> transactionLimit;
	
	/**
	 * @see com.gentlecorp.account.models.entities.Account#category
	 **/
	public static volatile SingularAttribute<Account, AccountType> category;
	
	/**
	 * @see com.gentlecorp.account.models.entities.Account
	 **/
	public static volatile EntityType<Account> class_;
	
	/**
	 * @see com.gentlecorp.account.models.entities.Account#updated
	 **/
	public static volatile SingularAttribute<Account, LocalDateTime> updated;
	
	/**
	 * @see com.gentlecorp.account.models.entities.Account#username
	 **/
	public static volatile SingularAttribute<Account, String> username;

}

