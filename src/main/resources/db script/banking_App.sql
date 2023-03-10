
 -- USERS TABALE STRUCTURE:
 CREATE TABLE users1
 (
    user_id INT GENERATED ALWAYS AS IDENTITY NOT NULL PRIMARY KEY, 
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    token VARCHAR(255) NULL,
    code INT NULL,
    verified INT DEFAULT 0,
    verified_at DATETIME,
    updated_at TIMESTAMP,
    created_at TIMESTAMP CURRENT_TIMESTAMP
    
 );
 
 
 -- BANK ACCOUNTS TABLE STRUCTURE:
 CREATE TABLE accounts1(
     account_id INT GENERATED AS IDENTITY NOT NULL PRIMARY KEY,
     user_id INT,
     account_number VARCHAR(100) NOT NULL,
     account_name VARCHAR(50) NOT NULL,
     account_type VARCHAR(50) NOT NULL,
     balance DECIMAL(18, 2) DEFAULT 0.00,
     created_at TIMESTAMP,
     updated_at TIMESTAMP,
     FOREIGN KEY(user_id) REFERENCES users1(user_id) ON DELETE CASCADE
 );
 
 
 -- TRANSACTION HISTORY TABLE:
 CREATE TABLE transaction_history(
    transaction_id INT GENERATED AS IDENTITY NOT NULL PRIMARY KEY,
    account_id INT,
    transaction_type VARCHAR(50) NOT NULL,
    amount DECIMAL(18, 2),
    source VARCHAR(50) NULL,
    status VARCHAR(50) NULL, -- success / failed
    reason_code VARCHAR(100) NULL, -- INSUFFICIENT FUNDS
    created_at TIMESTAMP,
    FOREIGN KEY(account_id) REFERENCES accounts1(account_id) ON DELETE CASCADE
 );
 
 -- PAYMENTS TABLE STRUCTURE:
 CREATE TABLE payments(
    payment_id INT GENERATED AS IDENTITY NOT NULL PRIMARY KEY,
    account_id INT,
    beneficiary VARCHAR(50) NULL,
    beneficiary_acc_no VARCHAR(255) NULL,
    amount DECIMAL(18, 2) NULL,
    reference_no VARCHAR(100) NULL,
    status VARCHAR(50) NULL, -- success / failed
    reason_code VARCHAR(100) NULL, -- INSUFFICIENT FUNDS
    created_at TIMESTAMP,    
    FOREIGN KEY(account_id) REFERENCES accounts1(account_id) ON DELETE CASCADE
 );
 
 
 -- TRANSACTION HISTORY VIEW:
 CREATE VIEW v_transaction_history
 AS
 SELECT 
    t.transaction_id,
    a.account_id,
    u.user_id,
    t.transaction_type,
    t.amount,
    t.source,
    t.status,
    t.reason_code,
    t.created_at
FROM
	transaction_history t
INNER JOIN
	accounts1 a
ON
	t.account_id = a.account_id
INNER JOIN
	users1 u
ON
	a.user_id = u.user_id;
 
 
 
 -- PAYMENT HISTORY VIEW:
 CREATE VIEW v_payments
 AS
 SELECT
	p.payment_id,
    a.account_id,
    u.user_id,
    p.beneficiary,
    p.beneficiary_acc_no,
    p.amount,
    p.status,
    p.reference_no,
    p.reason_code,
    p.created_at
FROM
 payments p
INNER JOIN
	accounts1 a
 ON
	p.account_id = a.account_id
INNER JOIN
	users1 u
ON
	a.user_id = u.user_id;
 
 
 
 
 
 select * from v_transaction_history;
 
 select * from v_payments;
 
 select * from payments;
 
 select * from users1;
 
 select * from accounts1;
 
 select * from transaction_history;
 
 
 
 
 
 
 
 