package com.example.springthymeleaf.JPARepository;

import com.example.springthymeleaf.bean.jpa.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserJPARepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findOneByEmail(String email);


    @Query("SELECT u FROM UserEntity u")
    Page<UserEntity> pageUser(Pageable pageable);

    @Query("SELECT u FROM UserEntity u WHERE u.userName LIKE %?1%")
    Page<UserEntity> pageByNameLike(String name, Pageable pageable);
}
