package it.epicode.U2_W7_D2.repository;


import it.epicode.U2_W7_D1.model.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DipendenteRepository extends JpaRepository<Dipendente,Integer> {
}
