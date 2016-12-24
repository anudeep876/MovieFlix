package egen.io.movieflix.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import egen.io.movieflix.entity.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> findAll() {
        TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
        return query.getResultList();
    }

    @Override
    public User findOne(String userId) {
        System.out.println(userId);
        return em.find(User.class, Integer.parseInt(userId));
    }

    @Override
    public User authUser(String userName, String password) {
        System.out.println(userName);
        TypedQuery<User> query = em.createNamedQuery("User.authUser", User.class);
        query.setParameter("puserName", userName);
        query.setParameter("ppassword", password);
        User user = query.getSingleResult();
        return user;
    }

    @Override
    public User findByEmail(String userEmail) {
        TypedQuery<User> query = em.createNamedQuery("User.findByEmail", User.class);
        query.setParameter("puserEmail", userEmail);
        List<User> users = query.getResultList();
        if (users.size() == 1) {
            return users.get(0);
        } else {
            return null;
        }
    }

    @Override
    public User create(User movie) {
        em.persist(movie);
        return movie;
    }

    @Override
    public User update(User movie) {
        return em.merge(movie);
    }

    @Override
    public void delete(User existing) {
        em.remove(existing);
    }

}
