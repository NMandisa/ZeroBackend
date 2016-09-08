package za.co.zero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import za.co.zero.domain.OneTimePassword;
import za.co.zero.domain.User;
import za.co.zero.dto.OneTimePasswordDTO;
import za.co.zero.dto.UserDTO;
import za.co.zero.service.OTPService;
import za.co.zero.service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NMandisa Mkhungo on 9/1/2016.
 */

@CrossOrigin(allowedHeaders = {"*"}, origins = "*")
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    OTPService otpService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getAllUsers() {

        return userService.getAllUser();


    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<User> saveUser(@RequestBody User user) throws IOException {
        try{
            userService.saveUser(user);
            otpService.GenerateOTP(user.getEmailAddress());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception ex){
            userService.saveUser(user);
            otpService.GenerateOTP(user.getEmailAddress());
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }

    }

    @RequestMapping(value = "/verify", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<?> verifyAccount(@RequestBody OneTimePasswordDTO oneTimePassword) {
        List<String> feedBack;
        otpService.verifyProfile(oneTimePassword);
        if (otpService.isVerified() == true) {
            feedBack = otpService.getFeedBack();
            return new ResponseEntity<>(feedBack, HttpStatus.OK);
        } else {
            feedBack = otpService.getFeedBack();
            return new ResponseEntity<>(feedBack, HttpStatus.NOT_IMPLEMENTED);
        }


    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<?> loginUser(@RequestBody UserDTO login) {
        User loginUser = userService.loginUser(login.getEmailAddress(), login.getPassword());
        return loginUser != null ? new ResponseEntity<>(loginUser, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody UserDTO user) {
        List<String> feedBack;
        try {

            userService.updateUser(id, user.getEmailAddress(), user.getFirstName(), user.getSurname());
            feedBack = userService.getFeedBack();
            return new ResponseEntity<>(feedBack, HttpStatus.OK);
        } catch (Exception ex) {
            feedBack = userService.getFeedBack();
            return new ResponseEntity<>(feedBack, HttpStatus.NOT_MODIFIED);
        }
    }

    @RequestMapping(value = "/recover", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<?> forgotPassword(@RequestBody UserDTO user) {
        List<String> feedBack;
        try {
            userService.forgotPassword(user.getEmailAddress());
            feedBack = userService.getFeedBack();
            return new ResponseEntity<>(feedBack, HttpStatus.OK);
        } catch (Exception ex) {
            feedBack = userService.getFeedBack();
            return new ResponseEntity<>(feedBack, HttpStatus.NO_CONTENT);
        }
    }
}
