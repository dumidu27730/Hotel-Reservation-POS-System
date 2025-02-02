package edu.icet.service.custom.impl;

import edu.icet.dto.User;
import edu.icet.entity.UserEntity;
import edu.icet.repository.DaoFactory;
import edu.icet.repository.custom.UserDao;
import edu.icet.service.custom.UserService;
import edu.icet.util.DaoType;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl instance;

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance == null ? instance = new UserServiceImpl() : instance;
    }
    UserDao userDao = DaoFactory.getInstance().getDaoType(DaoType.USER);

    @Override
    public ObservableList<User> getAll() {
        return null;
    }

    @Override
    public boolean addUser(User user) {
        UserEntity entity = new ModelMapper().map(user, UserEntity.class);
        return userDao.save(entity);
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(String userId) {
        return false;
    }

    @Override
    public User searchUser(String userId) {
        return null;
    }
}
