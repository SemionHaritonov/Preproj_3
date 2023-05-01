package ru.stud.homer.SecurityApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.stud.homer.SecurityApp.models.User;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);

    User findUserByEmail(String email);

    List<User> findAllByOrderByIdAsc();
}
