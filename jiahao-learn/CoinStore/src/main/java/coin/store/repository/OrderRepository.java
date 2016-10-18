package coin.store.repository;

import coin.store.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 11501 on 2016/10/18.
 */
@Repository
public interface OrderRepository extends MongoRepository<Order, String>{
    Order findBySn(String sn);
}
