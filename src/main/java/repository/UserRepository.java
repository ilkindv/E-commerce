package repository;

import model.entity.User;

import java.util.List;

public interface UserRepository {
    boolean createUser (User user);
    List<User> findAll();
    User findByUsername(String username);
    User findByFinNumber(String finNumber);
    User findByEmail(String email);
    void update(User user);
    boolean deleteAll(List<User> userList);
    boolean delete(User user);
}
