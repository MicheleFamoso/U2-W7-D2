package it.epicode.U2_W7_D2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DipendenteDto {

    @NotNull
    private String userName;
    @NotEmpty(message = "Inserisci il nome!")
    private String nome;
    @NotEmpty(message = "Inserisci il cognome!")
    private String cognome;
    @Email(message = "L'email deve essere scritta in un formato valido")
    private String email;
}
