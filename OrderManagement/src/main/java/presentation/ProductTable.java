package presentation;

import model.Product;

import java.util.ArrayList;

public class ProductTable extends AbstractJTable<Product>{
    public ProductTable(ArrayList<Product> t) {
        super(t);
    }
}
