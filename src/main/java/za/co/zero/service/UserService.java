package za.co.zero.service;

import za.co.zero.domain.User;

import java.util.List;

/**
 * Created by NMandisa Mkhungo on 9/1/2016.
 */
public interface UserService {
    /**
     * This is a service to save a User entity
     *
     * @param user to save
     * @return a persisted User
     */
    User saveUser(User user);

    /**
     * This is a service that returns all users
     *
     * @return a list of users
     */
    List<User> getAllUser();

    User loginUser(String emailAddress,String password);

    void updateUser(Long userid,String emailAddress,String firstName,String surname);

    void forgotPassword(String emailAddress);

    boolean isUpdated() ;

    void setUpdated(boolean updated) ;

    List<String> getFeedBack() ;

}
