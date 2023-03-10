package com.loyalty.pkg.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.loyalty.pkg.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	
	@Query(value = "SELECT email FROM users1 WHERE email = :email", nativeQuery = true)
	String getUserEmail(@Param("email")String email);
	
	@Query(value = "SELECT password_at FROM users1 WHERE email = :email", nativeQuery = true)
	String getUserPassword(@Param("email")String email);
	
	@Query(value = "SELECT verified FROM users1 WHERE email = :email", nativeQuery = true)
	int isVerified(@Param("email")String email);
	
	@Query(value = "SELECT * FROM users1 WHERE email = :email", nativeQuery = true)
    User getUserDetails(@Param("email")String email);

	@Modifying
	@Query(value = "INSERT INTO users1 (first_name, last_name, email, password_at, token, code) VALUES"
			+ "(:first_name, :last_name, :email, :password_at, :token, :code)", nativeQuery = true)
	@Transactional
	void registerUser(@Param("first_name") String first_name,
		 	          @Param("last_name") String last_name,
			          @Param("email") String email,
			          @Param("password_at") String password_at,
			          @Param("token") String token,
			          @Param("code") int code);

	@Modifying
	@Query(value = "UPDATE users1 SET token=null, code=null, verified=1, verified_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE "
			+ "token= :token AND code= :code", nativeQuery = true)
	@Transactional
	void verifyAccount(@Param("token") String token, @Param("code") String code);

	@Query(value = "SELECT token FROM users1 WHERE token = :token", nativeQuery = true)
	String checkToken(@Param("token") String token);

}
