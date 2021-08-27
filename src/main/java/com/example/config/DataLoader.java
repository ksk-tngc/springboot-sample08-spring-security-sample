package com.example.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.entity.User;
import com.example.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * データローダコンポーネント。
 *
 * <p>Spring Boot起動時にDB初期データを登録するコンポーネント。
 */
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

	/**
	 * ユーザマスタのリポジトリ。（コンストラクタインジェクション）
	 */
	private final UserRepository userRepository;

	/**
	 * パスワード暗号化用エンコーダ。（コンストラクタインジェクション）
	 * <p>Spring Securityのログイン認証はパスワードの暗号化必須。
	 */
	private final PasswordEncoder passwordEncoder;

	/**
	 * Spring Boot起動時にコマンドラインとして実行する処理。
	 * <p>DB初期データを登録する。
	 */
	@Override
	public void run(String... args) throws Exception {
		// ユーザマスタの初期データ登録
		User user = new User("tom@example.com", passwordEncoder.encode("pass"), "GENERAL");
		userRepository.save(user); // DB登録

		user = new User("jack@example.com", passwordEncoder.encode("pass"), "GENERAL");
		userRepository.save(user); // DB登録
	}

}
