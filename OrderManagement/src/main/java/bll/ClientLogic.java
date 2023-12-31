package bll;

import bll.Validator.EmailValidator;
import bll.Validator.PhoneNrValidator;
import bll.Validator.Validator;
import dataaccess.ClientDAO;
import model.Clients;


import java.util.ArrayList;
import java.util.List;


/**
 * The ClientLogic class handles the business logic related to Clients.
 *
 * This class provides methods for inserting, selecting, updating, and deleting Clients, while applying validation rules
 * through a list of Validators. It interacts with the ClientDAO class to perform database operations.
 */
public class ClientLogic {

    private List<Validator<Clients>> clt;
    private ClientDAO cltDAO;

   public ClientLogic() {
        cltDAO = new ClientDAO();
        clt = new ArrayList<>();
        clt.add(new EmailValidator());
        clt.add(new PhoneNrValidator());

    }

    public void insertClinet(Clients c) {
        for (Validator<Clients> validate : clt) {
            validate.validate(c);
        }
        cltDAO.insert(c);
    }

    public Clients selectClinet(Clients c,int id) {
        for (Validator<Clients> validate : clt) {
            validate.validate(c);
        }
        return cltDAO.selectByID(id);
    }

    public void updateClient(Clients c,int id) {
        for (Validator<Clients> validate : clt) {
            validate.validate(c);
        }
        cltDAO.update(c, id);
    }

    public void deleteClient(Clients c,int id) {
        for (Validator<Clients> validate : clt) {
            validate.validate(c);
        }
        cltDAO.delete(id);
    }

    public ArrayList<Clients> findAllClinets()
    {
            return cltDAO.findAll();

    }
}
