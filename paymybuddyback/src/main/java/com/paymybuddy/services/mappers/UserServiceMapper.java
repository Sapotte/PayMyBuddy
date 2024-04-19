package com.paymybuddy.services.mappers;

import com.paymybuddy.controllers.dto.PostUser;
import com.paymybuddy.db.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserServiceMapper {

    @Mapping(target = "accountBalance", source = "accountBalance")
    @Mapping(target = "id", ignore = true)
    User mapPostUserToUser(PostUser postUser, Float accountBalance);
}
