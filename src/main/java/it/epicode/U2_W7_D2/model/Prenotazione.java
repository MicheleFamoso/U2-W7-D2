package it.epicode.U2_W7_D2.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Data
@Entity
public class Prenotazione {
    @Id
    @GeneratedValue
    private int id;
    private LocalDate dataRichiesta;
    private String preferenze;

   @ManyToOne
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;

   @ManyToOne
    @JoinColumn(name = "viaggio_id")
    private Viaggio viaggio;

}
