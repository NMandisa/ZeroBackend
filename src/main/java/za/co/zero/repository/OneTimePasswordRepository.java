package za.co.zero.repository;

import org.springframework.data.repository.CrudRepository;
import za.co.zero.domain.OneTimePassword;

import java.util.List;

/**
 * Created by NMandisa Mkhungo on 9/5/2016.
 */
public interface OneTimePasswordRepository extends CrudRepository<OneTimePassword,Long> {


    @Override
    List<OneTimePassword> findAll();

    @Override
    OneTimePassword findOne(Long userId);

    OneTimePassword findByEmailAddress(String emailAddress);

    OneTimePassword findByOtp(int otp);
}
