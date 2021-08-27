package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entity.User;

import lombok.RequiredArgsConstructor;

/**
 * Spring Securityのログイン認証機能で使用するサービスの実装クラス。
 *
 * <p>{@code UserDetailsService#loadUserByUsername()}を実装し、
 * {@code SecurityConfig}の中でSpring Securityにこの実装クラスを渡すことで
 * 標準のログイン認証機能が実現される。
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	/** ユーザーサービス（コンストラクタインジェクション） */
	private final UserService userService;

	/**
	 * ユーザIDでユーザマスタを検索し、{@code UserDetails} 型の
	 * インスタンス（Spring Security標準のユーザー情報）を返却する。
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// ユーザIDに紐づくユーザ情報をユーザマスタから取得
		User loginUser = userService.getUser(username);

		// ユーザが存在しない場合
		if (loginUser == null) {
			throw new UsernameNotFoundException("ユーザーが存在しません");
		}

		// 権限のリストを作成
		GrantedAuthority authority = new SimpleGrantedAuthority(loginUser.getRole());
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(authority);

		// Spring Security標準のユーザー情報（UserDetails）を作成
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(loginUser.getUserId(),
				loginUser.getPassword(), authorities);

		return userDetails;
	}

}
