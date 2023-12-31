package bll;

import bll.Validator.OrderValidator;
import bll.Validator.Validator;
import dataaccess.OrderDAO;
import dataaccess.ProductDAO;
import model.Orders;
import model.Product;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * The OrderLogic class handles the business logic related to Orders.
 *
 * This class provides methods for inserting, selecting, updating, and deleting Orders, while applying validation rules
 * through a list of Validators. It interacts with the OrderDAO class to perform database operations.
 */
public class OrderLogic {
    private List<Validator<Orders>> ord;
    private OrderDAO ordDAO;

    public OrderLogic() {

        ordDAO = new OrderDAO();
        ord = new ArrayList<>();
        ord.add(new OrderValidator());
    }

    public void insertOrder(Orders o,int idProduct) {
        for (Validator<Orders> validate : ord) {
            validate.validate(o);
        }
        ordDAO.insert(o);
        ProductDAO p = new ProductDAO();
        Field fields;
        Product produs = p.selectByID(idProduct);
        produs.setCantitate(produs.getCantitate() - o.getCantitate());
        p.update(produs,idProduct);

    }

    public Orders selectOrder(Orders o,int id) {
        for (Validator<Orders> validate : ord) {
            validate.validate(o);
        }
        return ordDAO.selectByID(id);
    }

    public void updateOrder(Orders o,int id) {
        for (Validator<Orders> validate : ord) {
            validate.validate(o);
        }
        ordDAO.update(o, id);
    }

    public void deleteOrder(Orders o,int id) {
        for (Validator<Orders> validate : ord) {
            validate.validate(o);
        }
        ordDAO.delete(id);
    }

    public ArrayList<Orders> findAllOrders()
    {

       return ordDAO.findAll();
    }
}
