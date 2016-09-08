package za.co.zero.service.impl;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.zero.domain.OneTimePassword;
import za.co.zero.domain.User;
import za.co.zero.dto.OneTimePasswordDTO;
import za.co.zero.repository.OneTimePasswordRepository;
import za.co.zero.repository.UserRepository;
import za.co.zero.service.OTPService;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by NMandisa Mkhungo on 9/5/2016.
 */
@Service
public class DefaultOTPService implements OTPService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OneTimePasswordRepository oneTimePasswordRepository;


    private String otp;
    private List<String> feedBack;//FeedBack to the user
    private boolean verified = false;

    @Override
    public boolean isVerified() {
        return verified;
    }

    @Override
    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    @Override
    public List<String> getFeedBack() {
        return feedBack;
    }

    @Override
    public void setFeedBack(List<String> feedBack) {
        this.feedBack = feedBack;
    }

    @Override
    public String getOtp() {
        return otp;
    }


    @Override
    public List<OneTimePassword> getAllOneTimePasswords() {
        return oneTimePasswordRepository.findAll();
    }

    @Override
    public void GenerateOTP(String emailAddress) {
        otp = "";
        // Server generates an OTP and waits for client to send this
        Random r = new Random();
        for (int i = 0; i < 6; i++) {
            otp += r.nextInt(10);
        }
        System.out.println("Your OTP :" + otp);


        final long ONE_MINUTE_IN_MILLIS = 60000;//millisecs
        Calendar date = Calendar.getInstance();
        long t = date.getTimeInMillis();
        Date afterAddingTwoMins = new Date(t + (2 * ONE_MINUTE_IN_MILLIS));

        OneTimePassword oneTimePassword = new OneTimePassword();
        oneTimePassword.setEmailAddress(emailAddress);
        oneTimePassword.setOtp(otp);
        oneTimePassword.setOtpIssuedDateTime(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date()));
        oneTimePassword.setOtpExpireDateTime(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(afterAddingTwoMins));
        oneTimePasswordRepository.save(oneTimePassword);


    }

    @Override
    public void verifyProfile(OneTimePasswordDTO oneTimePasswordDTO) {
        //Find the oneTime and User by Email to check
        OneTimePassword verifyOneTimePassword = oneTimePasswordRepository.findByEmailAddress(oneTimePasswordDTO.getEmailAddress());
        User user = userRepository.findByEmailAddress(oneTimePasswordDTO.getEmailAddress());

        //checking that OTP is still with the range of valid time
        DateTime otpIssuedTime = DateTime.parse(verifyOneTimePassword.getOtpIssuedDateTime());

        // Server starts a timer of 2 minutes during which the OTP is valid.
        //Creating the timer
        String dateCount = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date());
        DateTime countTime = DateTime.parse(dateCount);
        long optTimer;
        optTimer = countTime.getMinuteOfDay() - otpIssuedTime.getMinuteOfDay();
        System.out.println("Differents now since issued date" + optTimer);

        //Reinitialize the feedback;
        feedBack = new ArrayList<>();

        String newEmail = oneTimePasswordDTO.getEmailAddress();
        if (newEmail.equals(verifyOneTimePassword.getEmailAddress())) {//Check if the user has been issued an OTP
            feedBack.add("You were issued an OTP");
            if (optTimer <= 2) {//Check if the OTP is still valid andi has not expired
                System.out.println("Your OTP is still valid" + optTimer);
                feedBack.add("Your OTP is still valid");
                if (oneTimePasswordDTO.getEmailAddress().equalsIgnoreCase(verifyOneTimePassword.getEmailAddress()) && oneTimePasswordDTO.getOtp().equalsIgnoreCase(verifyOneTimePassword.getOtp())) {
                    //Your profile is being validated
                    user.setActivate(true);
                    userRepository.save(user);
                    feedBack.add("Your Profile is now verified!");
                    verified = true;
                } else {
                    //Your profile is being validated
                    feedBack.add("Your Email and OPT dont match one on the database");
                }
            } else if (optTimer > 2) {
                //User took more than 2 minutes and hence the OTP is invalid
                feedBack.add("Your OTP has Expired!(Time out)");
            }
        } else if (user.isActivate() == true) {//Checking if the user has already been verified
            feedBack.add("Seems like your profile has already been verified");
            verified = true;
        } else {
            feedBack.add("Unable to verify Profile (Not Verified)!");
        }

    }
}
