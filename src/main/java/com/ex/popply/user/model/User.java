package com.ex.popply.user.model;

import java.time.LocalDateTime;

import com.ex.popply.user.exception.ForbiddenUserException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ex.popply.common.model.BaseTimeEntity;
import com.ex.popply.user.model.dto.request.SignUpRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_user")
@Builder
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long userId;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String phoneNumber;

	@Enumerated(EnumType.STRING)
	@Builder.Default
	private AccountRole accountRole = AccountRole.USER;

	@Enumerated(EnumType.STRING)
	@Builder.Default
	private AccountState accountState = AccountState.NORMAL;

	private String provider;
	private String providerId;

	@Builder.Default
	private LocalDateTime lastLoginAt = LocalDateTime.now();

	public User(SignUpRequest request, PasswordEncoder passwordEncoder) {
		this.email = request.getEmail();
		this.password = passwordEncoder.encode(request.getPassword());
		this.name = request.getName();
		this.phoneNumber = request.getPhoneNumber();
		this.accountRole = AccountRole.USER;
		this.accountState = AccountState.NORMAL;
	}

	public void login() {
		if (!AccountState.NORMAL.equals(this.accountState)) {
			 throw ForbiddenUserException.EXCEPTION;
		}
		lastLoginAt = LocalDateTime.now();
	}
}
