package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.User;

/**
 * ユーザマスタのリポジトリインターフェース。
 *
 * <p>{@code JapRepository}を継承することでSpring Data JPAが用意している
 * 便利なメソッドを使用可能。
 */
@Repository // JpaRepositoryを継承する場合、省略可
public interface UserRepository extends JpaRepository<User, String> {
}
