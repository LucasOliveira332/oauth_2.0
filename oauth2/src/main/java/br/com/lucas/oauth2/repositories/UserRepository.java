package br.com.lucas.oauth2.repositories;

import br.com.lucas.oauth2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository <User, UUID> {

    public Optional<User> findByUsername(String username);
}
