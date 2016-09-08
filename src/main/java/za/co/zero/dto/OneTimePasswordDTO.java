package za.co.zero.dto;



/**
 * Created by NMandisa Mkhungo on 9/5/2016.
 */
public class OneTimePasswordDTO {


    private Long otpId;
    private String emailAddress;
    private String otp;
    private String otpIssuedDateTime;
    private String otpExpireDateTime;

    public String getOtpIssuedDateTime() {
        return otpIssuedDateTime;
    }

    public void setOtpIssuedDateTime(String otpIssuedDateTime) {
        this.otpIssuedDateTime = otpIssuedDateTime;
    }

    public String getOtpExpireDateTime() {
        return otpExpireDateTime;
    }

    public void setOtpExpireDateTime(String otpExpireDateTime) {
        this.otpExpireDateTime = otpExpireDateTime;
    }

    public OneTimePasswordDTO(){}

    public OneTimePasswordDTO(String emailAddress,String otp){
        this.emailAddress=emailAddress;
        this.otp=otp;
    }

    public Long getOtpId() {
        return otpId;
    }

    public void setOtpId(Long otpId) {
        this.otpId = otpId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
