package it.epicode.U2_W7_D2.controller;


import it.epicode.U2_W7_D2.dto.LoginDto;
import it.epicode.U2_W7_D2.dto.UserDto;
import it.epicode.U2_W7_D2.exception.NonTrovatoException;
import it.epicode.U2_W7_D2.exception.ValidationException;
import it.epicode.U2_W7_D2.model.User;
import it.epicode.U2_W7_D2.service.AuthService;
import it.epicode.U2_W7_D2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/auth/register")
    public User register(@RequestBody @Validated UserDto userDto, BindingResult bindingResult) throws ValidationException {
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .reduce("",(s,e)->s+e));

        }

      return   userService.saveUser(userDto);


    }

    @GetMapping("/auth/login")
    public String login(@RequestBody @Validated LoginDto loginDto, BindingResult bindingResult) throws ValidationException, NonTrovatoException {
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .reduce("",(s,e)->s+e));

        }


        return authService.login(loginDto);

    }


}
