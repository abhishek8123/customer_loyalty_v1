package com.loyalty.pkg.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "A")
public class User {
	
	@Id
	private int user_id;

	@NotEmpty(message = "The First name field cannot be empty")
	@Size(min = 3, message = "The First name field must greater that 3 characters")
	private String first_name;

	@NotEmpty(message = "The Last name field cannot be empty")
	@Size(min = 3, message = "The First name field must greater that 3 characters")
	private String last_name;

	@Email
	@NotEmpty
	@Pattern(regexp = "([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})", message = "Please Enter a Valid Email")
	private String email;

    @NotEmpty
    @NotNull
	private String password_at;
    
	private String token;
	private String code;
	private int varified;
	private Date varified_at;
	private Date updated_at;
	private Date created_at;
	

}
