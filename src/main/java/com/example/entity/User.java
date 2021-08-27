package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザマスタのエンティティ。
 */
@Entity
@Table(name = "USERS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	/** ユーザID */
	@Id
	@Email
	private String userId;

	/** パスワード */
	@NotNull
	private String password;

	/** ロール */
	@NotNull
	private String role;

}
