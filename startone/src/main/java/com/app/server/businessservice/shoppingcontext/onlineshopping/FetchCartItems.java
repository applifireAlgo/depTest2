package com.app.server.businessservice.shoppingcontext.onlineshopping;
import com.app.server.repository.shoppingcontext.onlineshopping.CartRepository;
import com.app.shared.shoppingcontext.onlineshopping.Cart;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.spartan.server.session.bizService.SessionDataMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;
import com.spartan.pluggable.exception.layers.ds.SessionDataNotFoundException;
import java.util.List;

@Component
public class FetchCartItems {

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private CartRepository<Cart> cartRepository;

    @Autowired
    private SessionDataMgtService sessionService;

    public List<Cart> fetchCartItems() throws SpartanPersistenceException, SessionDataNotFoundException, Exception {
        java.lang.String userIdFromSession = (java.lang.String) sessionService.getSessionData("userId");
        if (userIdFromSession == null) {
            throw new com.spartan.pluggable.exception.layers.ds.SessionDataNotFoundException();
        }
        java.util.List<com.app.shared.shoppingcontext.onlineshopping.Cart> cartList = cartRepository.findByUserId(userIdFromSession);
        return cartList;
    }
}
