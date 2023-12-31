package dataaccess;

import model.Clients;

public class ClientDAO extends AbstractDAO<Clients>{



    /**
     * Inserts a Clients object into the database.
     *
     * This method inserts a Clients object into the database by calling the insert method of the superclass.
     *
     * @param clients The Clients object to insert into the database.
     * @return The number of rows affected by the insert operation, or 1 if an error occurs.
     */
    public int insert(Clients clients){

        return super.insert(clients);
    }
    /**
     * Deletes a Clients object from the database by its ID.
     *
     * This method deletes a Clients object from the database by calling the delete method of the superclass.
     *
     * @param id The ID of the Clients object to delete.
     * @return The number of rows affected by the delete operation, or 1 if an error occurs.
     */
    public int delete(int id){
        return super.delete(id);
    }


    /**
     * Retrieves a Clients object from the database by its ID.
     *
     * This method retrieves a Clients object from the database by calling the findById method of the superclass.
     *
     * @param id The ID of the Clients object to retrieve.
     * @return The Clients object with the specified ID, or null if not found.
     */
    public Clients selectByID(int id)
    {
        return super.findById(id);
    }


    /**
     * Updates a Clients object in the database by its ID.
     *
     * This method updates a Clients object in the database by calling the update method of the superclass.
     *
     * @param clients The updated Clients object.
     * @param id The ID of the Clients object to update.
     * @return The number of rows affected by the update operation, or 1 if an error occurs.
     */
    public int update(Clients clients,int id)
    {
        return super.update(clients,id);
    }

}
