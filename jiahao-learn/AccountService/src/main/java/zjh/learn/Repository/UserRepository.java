package zjh.learn.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import zjh.learn.bean.User;

/**
 * Created by jiahao on 16-8-9.
 */
public interface UserRepository extends MongoRepository<User, String> {

    User findByName(String name);

    User findByPhone(String phone);

    User findByNameAndPassword(String name, String password);

    User findByPhoneAndPassword(String phone, String password);

    User save(User user);
}
