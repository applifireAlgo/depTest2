package com.app.shared.shoppingcontext.acl;
import com.app.shared.shoppingcontext.onlineshopping.Cart;
import java.util.List;
import com.app.shared.shoppingcontext.onlineshopping.OrderMain;

public class OrderACL {

    public OrderACL(List<Cart> _cart) {
        this.cartInput = _cart;
        this.doMapping();
    }

    private List<Cart> cartInput;

    private OrderMain ordermainOutput;

    public OrderMain getOrder() {
        return ordermainOutput;
    }

    public void doMapping() {
        ordermainOutput = new OrderMain();
        java.util.List<com.app.shared.shoppingcontext.onlineshopping.OrderDetails> lstorderDetailsEntities = new java.util.ArrayList<com.app.shared.shoppingcontext.onlineshopping.OrderDetails>();
        for (com.app.shared.shoppingcontext.onlineshopping.Cart _orderDetails : cartInput) {
            com.app.shared.shoppingcontext.onlineshopping.OrderDetails orderdetails = new com.app.shared.shoppingcontext.onlineshopping.OrderDetails();
            orderdetails.setItemId(_orderDetails.getItemId());
            orderdetails.setItemPrice(_orderDetails.getItemPrice());
            orderdetails.setItemQuantity(_orderDetails.getItemQuantity());
            orderdetails.setSubTotal(_orderDetails.getSubTotal());
            lstorderDetailsEntities.add(orderdetails);
        }
        ordermainOutput.setOrderDetails(lstorderDetailsEntities);
    }
}
