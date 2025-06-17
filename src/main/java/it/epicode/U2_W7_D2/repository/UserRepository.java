package it.epicode.U2_W7_D2.repository;

import it.epicode.U2_W7_D2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    public Optional<User> findByEmail(String email);

}
