package db.msyql;


import db.UserDAO;
import model.User;

import java.util.List;

public class UserDAOImpl extends DBConnector implements UserDAO {
    public User getUserByID(int userID) {
        return null;
    }

    public List<User> getUserList() {
        return null;
    }

    public int insertUser(User user) {
        return 0;
    }

    public boolean updateUser(User user) {
        return false;
    }

    public boolean deleteUser(int userID) {
        return false;
    }
}
