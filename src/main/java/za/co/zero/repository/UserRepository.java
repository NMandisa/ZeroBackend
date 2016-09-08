package za.co.zero.repository;

import org.springframework.data.repository.CrudRepository;
import za.co.zero.domain.User;

import java.util.List;

/**
 * Created by NMandisa Mkhungo on 9/1/2016.
 */
public interface UserRepository extends CrudRepository<User,Long> {

    @Override
    List<User> findAll();

    @Override
    User findOne(Long userId);

    User findByEmailAddress(String emailAddress);
}
