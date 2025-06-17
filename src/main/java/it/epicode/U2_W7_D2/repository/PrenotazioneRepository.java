package it.epicode.U2_W7_D2.repository;


import it.epicode.U2_W7_D1.model.Dipendente;
import it.epicode.U2_W7_D1.model.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione,Integer> {


    boolean existsByDipendenteAndDataRichiesta(Dipendente dipendente, LocalDate dataRichiesta);
}
