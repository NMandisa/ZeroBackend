package za.co.zero.dto;


/**
 * Created by NMandisa Mkhungo on 9/1/2016.
 */
public class UserDTO {

    private Long userId;
    private String firstName;
    private String surname;
    private String username;
    private String gender;
    private String password;
    private String emailAddress;
    private String otp;


    public UserDTO(){}

    public UserDTO(String  emailAddress,String password){
        this.emailAddress =emailAddress;
        this.password=password;

    }

    public UserDTO(String firstName,String surname,String username,String gender,String password){
        this.firstName=firstName;
        this.surname=surname;
        this.username=username;
        this.gender=gender;
        this.password=password;

    }


    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


}
