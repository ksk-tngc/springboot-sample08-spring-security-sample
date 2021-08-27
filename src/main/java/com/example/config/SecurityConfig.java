package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

/**
 * セキュリティ設定クラス。
 */
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * Spring Security標準のユーザー情報を表すインスタンス。
	 * コンストラクタインジェクション。
	 */
	private final UserDetailsService userDetailsService;

	/**
	 * パスワードの暗号化用に、BCrypt（ビー・クリプト）エンコーダを取得。
	 * <p>Spring Securityのログイン認証はパスワードの暗号化必須。
	 *
	 * @return BCryptエンコーダ
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * セキュリティ対象外の設定
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		// セキュリティを適用しない
		// WebJarsやCSSなどの静的リソース、H2 Databaseのコンソールはログイン認証せずにアクセス可能としておく。
		web.ignoring()
				.antMatchers("/webjars/**")
				.antMatchers("/css/**")
				.antMatchers("/js/**")
				.antMatchers("/h2-console/**");
//				.anyRequest(); // MEMO: この設定を行うと一時的に全てのURLをセキュリティ対象外にできる
	}

	/**
	 * セキュリティの各種設定
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// ログイン認証不要なページの設定
		http.authorizeRequests()
				.antMatchers("/").permitAll() // 直リンクOK
				.antMatchers("/login").permitAll() // 直リンクOK
				.anyRequest().authenticated(); // それ以外は直リンクNG（anyRequest ⇒ 全てのパス）

		// ログイン処理の設定
		http.formLogin()
				.loginProcessingUrl("/login") // ログイン処理のリクエスト先 （ログイン処理のPOST先URL。ログインボタン等のth:actionで指定するパス）
				.loginPage("/login") // ログインページのパス（ログイン画面のGETリクエスト先。未指定時、Spring Security標準のログイン画面が使用される）
				.failureUrl("/login?error") // ログイン失敗時の遷移先
				.usernameParameter("userId") // ログインページのユーザーIDのname属性
				.passwordParameter("password") // ログインページのパスワードのname属性
				.defaultSuccessUrl("/login/success", true); // 成功時の遷移先

		// ログアウト処理の設定
		// Spring Securityがログアウト処理を行ってくれるためログアウト用のコントローラは不要
		http.logout()
//				.logoutUrl("/logout") // ログアウト処理のリクエスト先（POSTメソッド用）※Spring Securityのデフォルトログアウト処理はPOSTメソッドで送る
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // ログアウト処理のリクエスト先（GETメソッド用）※GETメソッドでログアウトする場合はこの設定を使用する
				.logoutSuccessUrl("/login?logout"); // ログアウト成功時の遷移先
	}

	/**
	 * ログイン認証の設定
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// パスワード暗号化用エンコーダ
		PasswordEncoder passwordEncoder = passwordEncoder();

		// インメモリ認証
		// 仮のユーザーIDとパスワードを用意してログインできるようにする。（インメモリ認証）
		// ユーザマスタ等（DB）が使えない場合やテストユーザを用意するのに有効。
		auth.inMemoryAuthentication()
				.withUser("user01@example.com") // ユーザを追加
				.password(passwordEncoder.encode("pass")) // パスワード設定（Spring Securityではパスワードの暗号化必須）
				.roles("GENERAL") // ロール設定
				.and() // andで繋ぐ
				.withUser("admin@example.com") // ユーザを追加
				.password(passwordEncoder.encode("pass")) // パスワード設定（Spring Securityではパスワードの暗号化必須）
				.roles("ADMIN"); // ロール設定

		// ユーザマスタの情報で認証
		auth
				.userDetailsService(userDetailsService) // ユーザー認証に使用するサービスクラス
				.passwordEncoder(passwordEncoder); // パスワード暗号化用エンコーダ
	}

}
