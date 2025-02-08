package edu.icet.service.custom.impl;

import edu.icet.dto.User;
import edu.icet.entity.UserEntity;
import edu.icet.repository.DaoFactory;
import edu.icet.repository.custom.UserDao;
import edu.icet.service.custom.UserService;
import edu.icet.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl instance;

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance == null ? instance = new UserServiceImpl() : instance;
    }
    UserDao userDao = DaoFactory.getInstance().getDaoType(DaoType.USER);
    private final ModelMapper modelMapper = new ModelMapper();


    @Override
    public ObservableList<User> getAll() {
        List<UserEntity> userEntityDetails = userDao.getAll();

        ObservableList<User> users = FXCollections.observableArrayList();
        for (UserEntity entity : userEntityDetails) {
            User user = modelMapper.map(entity, User.class);
            users.add(user);
        }
        return users;
    }

    @Override
    public boolean addUser(User user) {
        UserEntity entity = modelMapper.map(user, UserEntity.class);
        return userDao.save(entity);
    }

    @Override
    public boolean updateUser(User user) {
        UserEntity entity = modelMapper.map(user, UserEntity.class);
        return userDao.update(entity);
    }

    @Override
    public boolean deleteUser(String userId) {
        return userDao.delete(userId);
    }

    @Override
    public User searchUser(String userId) {
        UserEntity customerEntity = userDao.search(userId);

        if (customerEntity != null) {
            return modelMapper.map(customerEntity, User.class);
        }
        return null;
    }
    public ObservableList<String> getUserIds(){
        ObservableList<String> userIdsList = FXCollections.observableArrayList();
        getAll().forEach(User ->
                userIdsList.add(String.valueOf(User.getId())) );

        return userIdsList;
    }

}
