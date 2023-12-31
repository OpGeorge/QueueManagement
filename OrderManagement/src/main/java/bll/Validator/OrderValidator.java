package bll.Validator;

import dataaccess.ProductDAO;
import model.Orders;
import model.Product;

public class OrderValidator implements Validator<Orders>{
    @Override
    public void validate(Orders orders) {
        ProductDAO p2 = new ProductDAO();
        Product p = p2.findById(orders.getProductId());
        if(p.getCantitate()< orders.getCantitate())
        {
            throw new IllegalArgumentException("Too many products ordered");
        }
    }
}
