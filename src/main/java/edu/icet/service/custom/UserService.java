package edu.icet.service.custom;

import edu.icet.dto.User;
import edu.icet.service.SuperService;
import javafx.collections.ObservableList;

public interface UserService extends SuperService {

    ObservableList<User> getAll();

    boolean addUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(String userId);

    User searchUser(String userId);
}
