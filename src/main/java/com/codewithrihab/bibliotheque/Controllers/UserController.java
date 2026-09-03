package com.codewithrihab.bibliotheque.Controllers;

import com.codewithrihab.bibliotheque.dtos.UserDto;
import com.codewithrihab.bibliotheque.entities.User;
import com.codewithrihab.bibliotheque.entities.UserRequest;
import com.codewithrihab.bibliotheque.mappers.UserMapper;
import com.codewithrihab.bibliotheque.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequestMapping("/api/v1/users")
@RestController
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody UserRequest request,
            UriComponentsBuilder uriBuilder) {

        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("A user with this email already exists");
        }

        User user = userMapper.toEntity(request);

        User saved = userRepository.save(user);

        UserDto dto = userMapper.toDto(saved);

        var uri = uriBuilder
                .path("/api/v1/users/{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = (userRepository.findAll());
        List<UserDto> userDtos = users.stream().map(userMapper::toDto).toList();
        return ResponseEntity.ok(userDtos);

    }
}
