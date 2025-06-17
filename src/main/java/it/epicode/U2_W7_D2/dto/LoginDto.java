package it.epicode.U2_W7_D2.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginDto {

    @NotEmpty(message = "Questo campo no puo essere vuoto")
    private String email;
    @NotEmpty(message = "Questo campo no puo essere vuoto")
    private String password;
}
