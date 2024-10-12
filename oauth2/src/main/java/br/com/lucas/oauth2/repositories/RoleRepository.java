package br.com.lucas.oauth2.repositories;

import br.com.lucas.oauth2.models.Role;
import br.com.lucas.oauth2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository <Role, Long> {
}
