package za.co.zero.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.zero.domain.User;
import za.co.zero.repository.UserRepository;
import za.co.zero.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NMandisa Mkhungo on 9/1/2016.
 */
@Service
public class DefaultUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private JavaMailSender javaMailService;

    private List<String> feedBack;//FeedBack to the user
    private boolean updated = false;

    @Override
    public boolean isUpdated() {
        return updated;
    }

    @Override
    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    @Override
    public List<String> getFeedBack() {
        return feedBack;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User loginUser(String emailAddress, String password) {
        User user = userRepository.findByEmailAddress(emailAddress);
        return user != null && (user.getPassword().equals(password)) ? user : null;
    }

    @Override
    public void updateUser(Long userid, String emailAddress, String firstName, String surname) {
        User user;
        feedBack = new ArrayList<>();
        try {
            user = userRepository.findOne(userid);
            if (user != null) {
                user.setEmailAddress(emailAddress);
                user.setFirstName(firstName);
                user.setSurname(surname);
                userRepository.save(user);
                setUpdated(true);
                System.out.println("Your Profile has been updated");
                feedBack.add("Your Profile has been updated");
            }
        } catch (Exception ex) {
            System.out.println("Your Profile has been updated");
            feedBack.add("Unable to update profile");
            setUpdated(false);
        }
    }

    @Override
    public void forgotPassword(String emailAddress) {
        String password;
        User user;
        feedBack = new ArrayList<>();
        try {
            user = userRepository.findByEmailAddress(emailAddress);
            if (user != null) {//Checking if the user exists
                password = user.getPassword();//Found Password
                feedBack.add("Your password has been sent to your email,[Password] :" + password);
            } else {
                System.out.println("Sorry We couldn't find you in the system");
                feedBack.add("Sorry We couldn't find you in the system");
            }
        } catch (Exception ex) {
            System.out.println("Sorry We couldn't find you in the system");
            feedBack.add("Sorry We couldn't find you in the system");
        }
    }


//    SimpleMailMessage mailMessage=new SimpleMailMessage();
//    mailMessage.setTo("ntobeko@mkhungo.co.za");
//    mailMessage.setSubject("Registration");
//    mailMessage.setText("Hello Dummy Email Your registration is successfull");
//    javaMailService.send(mailMessage);

}
