package bll.Validator;

import model.Product;

public class PriceValidator implements Validator<Product>{




    @Override
    public void validate(Product product) {
        if(product.getPret() <= 0)
        {
            throw new IllegalArgumentException("Wrong listed price");
        }
    }
}
