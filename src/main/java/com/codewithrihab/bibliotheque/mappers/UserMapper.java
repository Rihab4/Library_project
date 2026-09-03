package com.codewithrihab.bibliotheque.mappers;

import com.codewithrihab.bibliotheque.dtos.UserDto;
import com.codewithrihab.bibliotheque.entities.User;
import com.codewithrihab.bibliotheque.entities.UserRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRequest request);

    UserDto toDto(User user);
}
