package com.borscheva.spring.springboot.dao;

import com.borscheva.spring.springboot.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT user FROM User user", (User.class)).getResultList();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(Long id, User update) {
        User newUser = getUserById(id);
        newUser.setName(update.getName());
        newUser.setLastName(update.getLastName());
        newUser.setAge(update.getAge());
        entityManager.merge(newUser);
    }

    @Override
    public void deleteUser(Long id) {
        entityManager.createQuery("DELETE FROM User user WHERE user.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
