package it.epicode.U2_W7_D2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Dipendente {
    @Id
    @GeneratedValue
    private int id;
    private String userName;
    private String nome;
    private String cognome;
    private String email;
    private String avatar;
    @JsonIgnore
    @OneToMany(mappedBy = "dipendente")
    private List<Prenotazione> prenotazioni;

}
