package com.example.service;

import com.example.entity.User;

/**
 * ユーザサービスのインターフェース。
 */
public interface UserService {

	/**
	 * ユーザマスタからユーザ情報を1件取得する。
	 *
	 * @param userId 検索対象のユーザID
	 * @return 検索結果。ユーザエンティティ
	 */
	User getUser(String userId);

}
