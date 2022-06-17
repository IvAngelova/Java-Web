package com.example.coffee_shop.repository;

import com.example.coffee_shop.model.entity.UserEntity;
import com.example.coffee_shop.model.view.UserViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<UserEntity> findByUsernameAndPassword(String username, String password);

    @Query("select u from UserEntity u order by size(u.orders) desc")
    List<UserEntity> findAllByCountOfOrdersDesc();
}
