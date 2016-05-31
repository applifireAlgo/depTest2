package com.app.server.businessservice.shoppingcontext.onlineshopping;
import com.app.server.repository.shoppingcontext.onlineshopping.OrderMainRepository;
import com.app.shared.shoppingcontext.onlineshopping.OrderMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class OrderMainBusinessService {

    @Autowired
    private OrderMainRepository orderMainRepository;

    public void update(OrderMain entity) throws Exception {
        if (entity.isHardDelete()) {
            orderMainRepository.delete(entity.getOrderId());
        } else {
            orderMainRepository.deleteOrderDetails(entity.getDeletedOrderDetailsList());
            orderMainRepository.update(entity);
        }
    }

    public void update(List<OrderMain> entity) throws Exception {
        for (OrderMain _ordermain : entity) {
            if (_ordermain.isHardDelete()) {
                orderMainRepository.delete(_ordermain.getOrderId());
            } else {
                orderMainRepository.deleteOrderDetails(_ordermain.getDeletedOrderDetailsList());
                orderMainRepository.update(_ordermain);
            }
        }
    }
}
