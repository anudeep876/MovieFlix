package egen.io.movieflix.repository;

import java.util.List;

import egen.io.movieflix.entity.User;

public interface UserRepository {

    public List<User> findAll();

    public User findOne(String userId);

    public User authUser(String userName, String password);

    public User findByEmail(String userEmail);

    public User create(User user);

    public User update(User user);

    public void delete(User existing);

}
