package com.example.springthymeleaf.services;

import com.example.springthymeleaf.bean.jpa.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {

    /**
     * @param userId
     * @return
     * @throws Exception
     */
    public Optional<UserEntity> findOneByUserId(Integer userId) throws Exception;

    /**
     *
     * @param email
     * @return
     * @throws Exception
     */
    public Optional<UserEntity> findOneByEmail(String email) throws Exception;

    /**
     * find All
     * @return
     * @throws Exception Exception
     */
    public List<UserEntity> findAll() throws Exception;

    /**
     *
     * @param userName
     * @param pageable
     * @return
     * @throws Exception
     */
    public Page<UserEntity> getPageByNameLike(String userName, Pageable pageable) throws Exception;
}
