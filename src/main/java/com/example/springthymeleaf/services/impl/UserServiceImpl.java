package com.example.springthymeleaf.services.impl;

import com.example.springthymeleaf.JPARepository.UserJPARepository;
import com.example.springthymeleaf.bean.jpa.UserEntity;
import com.example.springthymeleaf.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    @Autowired
    public UserJPARepository userJPARepository;


    /**
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public Optional<UserEntity> findOneByUserId(Integer userId) throws Exception {

        return userJPARepository.findById(userId);
    }

    /**
     * @param email
     * @return
     * @throws Exception
     */
    @Override
    public Optional<UserEntity> findOneByEmail(String email) throws Exception {
        return userJPARepository.findOneByEmail(email);
    }

    /**
     * @return
     * @throws Exception
     */
    @Override
    public List<UserEntity> findAll() throws Exception {
        return userJPARepository.findAll();
    }

    /**
     *
     * @param userName
     * @param pageable
     * @return
     * @throws Exception
     */
    @Override
    public Page<UserEntity> getPageByNameLike(String userName, Pageable pageable) throws Exception {
        return userName.isEmpty() ? userJPARepository.pageUser(pageable) : userJPARepository.pageByNameLike(userName, pageable);
    }
}
