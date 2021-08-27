package com.example.service;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * ユーザサービスの実装クラス。
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	/** ユーザリポジトリ（コンストラクタインジェクション） */
	private final UserRepository userRepository;

	/**
	 * ユーザマスタからユーザ情報を1件取得する。
	 *
	 * @param userId 検索対象のユーザID
	 * @return 検索結果。ユーザエンティティ
	 */
	@Override
	@Nullable
	public User getUser(String userId) {
		return userRepository.findById(userId).orElse(null);
	}

}
