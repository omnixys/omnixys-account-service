package com.gentlecorp.account.models.entities;

import com.gentlecorp.account.models.enums.TransactionType;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;
import java.util.UUID;

@StaticMetamodel(Transaction.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Transaction_ {

	public static final String TRANSACTION_TYPE = "transactionType";
	public static final String AMOUNT = "amount";
	public static final String SENDER = "sender";
	public static final String RECIPIENT = "recipient";
	public static final String ID = "id";
	public static final String TRANSACTION_DATE = "transactionDate";

	
	/**
	 * @see com.gentlecorp.account.models.entities.Transaction#transactionType
	 **/
	public static volatile SingularAttribute<Transaction, TransactionType> transactionType;
	
	/**
	 * @see com.gentlecorp.account.models.entities.Transaction#amount
	 **/
	public static volatile SingularAttribute<Transaction, Integer> amount;
	
	/**
	 * @see com.gentlecorp.account.models.entities.Transaction#sender
	 **/
	public static volatile SingularAttribute<Transaction, UUID> sender;
	
	/**
	 * @see com.gentlecorp.account.models.entities.Transaction#recipient
	 **/
	public static volatile SingularAttribute<Transaction, UUID> recipient;
	
	/**
	 * @see com.gentlecorp.account.models.entities.Transaction#id
	 **/
	public static volatile SingularAttribute<Transaction, UUID> id;
	
	/**
	 * @see com.gentlecorp.account.models.entities.Transaction#transactionDate
	 **/
	public static volatile SingularAttribute<Transaction, LocalDateTime> transactionDate;
	
	/**
	 * @see com.gentlecorp.account.models.entities.Transaction
	 **/
	public static volatile EntityType<Transaction> class_;

}

