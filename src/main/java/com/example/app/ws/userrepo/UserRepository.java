package com.example.app.ws.userrepo;

import com.example.app.ws.ui.model.response.UserRest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserRest, Long> {

    @Override
    Optional<UserRest> findById(Long s);

    @Override
    <S extends UserRest> S save(S s);
}
