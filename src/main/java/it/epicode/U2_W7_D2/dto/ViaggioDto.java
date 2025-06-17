package it.epicode.U2_W7_D2.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ViaggioDto {

    @NotEmpty(message = "La destinazione non puo essere vuota")
    private String destinazione;
    @NotNull(message = "La data non puo essere nulla ")
    private LocalDate data;


}
