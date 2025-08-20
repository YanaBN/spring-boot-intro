package mate.academy.controller;

import lombok.RequiredArgsConstructor;
import mate.academy.dto.UserRegistrationRequestDto;
import mate.academy.dto.UserResponseDto;
import mate.academy.exeptions.RegistrationException;
import mate.academy.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/registration")
    public UserResponseDto registerUser(@RequestBody UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        return userService.register(requestDto);
    }
}
