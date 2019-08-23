package com.example.app.ws.userrepo;

//import com.example.app.ws.ui.model.User;
import com.example.app.ws.ui.model.request.UserRequestModel;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserRequestModel, Long> {

    @Override
    Optional<UserRequestModel> findById(Long s);

    @Override
    <S extends UserRequestModel> S save(S s);

    @Override
    <S extends UserRequestModel> List<S> findAll(Example<S> example);

    @Override
    void delete(UserRequestModel entity);
}
