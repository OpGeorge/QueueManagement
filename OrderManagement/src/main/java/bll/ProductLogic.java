package bll;
import bll.Validator.PriceValidator;
import bll.Validator.Validator;
import dataaccess.ProductDAO;
import model.Orders;
import model.Product;
import java.util.ArrayList;
import java.util.List;

/**
 * The ProductLogic class handles the business logic related to Products.
 *
 * This class provides methods for inserting, selecting, updating, and deleting Products, while applying validation rules
 * through a list of Validators. It interacts with the ProductDAO class to perform database operations.
 */
public class ProductLogic {

    private List<Validator<Product>> prt;
    private ProductDAO prtDAO;

    public ProductLogic() {

        prtDAO = new ProductDAO();
        prt = new ArrayList<>();
        prt.add(new PriceValidator());
    }

    public void insertProduct(Product p) {
        for (Validator<Product> validate : prt) {
            validate.validate(p);
        }
        prtDAO.insert(p);
    }

    public Product selectProduct(Product p,int id) {
        for (Validator<Product> validate : prt) {
            validate.validate(p);
        }
        return prtDAO.selectByID(id);
    }

    public void updateProduct(Product p,int id) {
        for (Validator<Product> validate : prt) {
            validate.validate(p);
        }
        prtDAO.update(p, id);
    }

    public void deleteProduct(Product p,int id) {
        for (Validator<Product> validate : prt) {
            validate.validate(p);
        }
        prtDAO.delete(id);
    }

    public ArrayList<Product> findAllProducts()
    {

        return prtDAO.findAll();
    }
}
