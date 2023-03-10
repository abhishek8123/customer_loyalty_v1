package com.loyalty.pkg.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Account {
	
	    @Id
	    private int account_id;
	    private int user_id;
	    private String account_number;
	    private String account_name;
	    private String account_type;
	    private BigDecimal balance;
	    private LocalDateTime created_at;
	    private LocalDateTime updated_at;

}
