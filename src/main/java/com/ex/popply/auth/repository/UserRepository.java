package com.ex.popply.auth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ex.pop.user.model.AccountRole;
import com.ex.pop.user.model.AccountState;
import com.ex.pop.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String account);

    List<User> findAllByAccountRole(AccountRole role);

    Optional<User> findByEmailAndAccountState(String email, AccountState accountState);

    List<User> findAllByUserIdIn(List<Long> userId);

    List<User> findByUserIdIn(List<Long> userIds);
}
