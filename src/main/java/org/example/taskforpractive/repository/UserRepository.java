package org.example.taskforpractive.repository;

import org.example.taskforpractive.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findUserModelByUsernameAndPassword(String username, String password);
}
