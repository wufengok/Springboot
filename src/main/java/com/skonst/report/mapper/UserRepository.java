package com.skonst.report.mapper;

import com.skonst.report.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserNameOrEmail(String userName, String email);

    User findByUserName(String userName);

}