package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {

    void saveUser(User user);

    void updateUser(long id, String firstName, String lastName, String email);

    void removeUserById(long id);

    List<User> getAllUsers();
}
