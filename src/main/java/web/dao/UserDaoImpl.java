package web.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import web.model.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.createQuery("update User u set u.firstName = :firstName, u.lastName = :lastName," +
                        " u.email = :email  where u.id = :id")
                .setParameter("id", user.getId())
                .setParameter("firstName", user.getFirstName())
                .setParameter("lastName", user.getLastName())
                .setParameter("email", user.getEmail())
                .executeUpdate();
    }

    @Override
    public void removeUserById(long id) {
        entityManager.createQuery("delete from User where id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }
}
