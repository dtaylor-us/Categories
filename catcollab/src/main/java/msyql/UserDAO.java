package msyql;

import model.User;

import java.util.List;

public interface UserDAO {
    public User getUserByID(int userID);
    public List<User> getUserList();
    public int insertUser(User user);
    public boolean updateUser(User user);
    public boolean deleteUser (int userID);
}
