package za.co.zero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.co.zero.domain.OneTimePassword;
import za.co.zero.service.OTPService;

import java.util.List;

/**
 * Created by NMandisa Mkhungo on 9/6/2016.
 */
@CrossOrigin(allowedHeaders = {"*" }, origins = "*")
@RestController
@RequestMapping(value = "/onetimepasswords")
public class OneTimePasswordController {

    @Autowired
    OTPService otpService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<OneTimePassword> getAllOTPs() {
        return otpService.getAllOneTimePasswords();
    }
}
