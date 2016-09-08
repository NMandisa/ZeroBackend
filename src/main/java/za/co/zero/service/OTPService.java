package za.co.zero.service;

import za.co.zero.domain.OneTimePassword;
import za.co.zero.dto.OneTimePasswordDTO;
import za.co.zero.dto.UserDTO;

import java.util.List;

/**
 * Created by NMandisa Mkhungo on 9/5/2016.
 */
public interface OTPService {


    List<OneTimePassword> getAllOneTimePasswords();

    void GenerateOTP(String emailAddress);

    void verifyProfile(OneTimePasswordDTO oneTimePasswordDTO);


    boolean isVerified();

    void setVerified(boolean verified);

    List<String> getFeedBack() ;

    void setFeedBack(List<String> feedBack) ;

    String getOtp() ;
}
