package dataaccess;


import model.Orders;

public class OrderDAO extends AbstractDAO<Orders>{


    /**
     * Inserts an Orders object into the database.
     *
     * This method inserts an Orders object into the database by calling the insert method of the superclass.
     *
     * @param order The Orders object to insert into the database.
     * @return The number of rows affected by the insert operation, or 1 if an error occurs.
     */

    public int insert(Orders order){

        return  super.insert(order);
    }

    /**
     * Deletes an object from the database by its ID.
     *
     * This method deletes an object from the database by calling the delete method of the superclass.
     *
     * @param id The ID of the object to delete.
     * @return The number of rows affected by the delete operation, or 1 if an error occurs.
     */
    public int delete(int id){
        return  super.delete(id);
    }


    /**
     * Retrieves an Orders object from the database by its ID.
     *
     * This method retrieves an Orders object from the database by calling the findById method of the superclass.
     *
     * @param id The ID of the Orders object to retrieve.
     * @return The retrieved Orders object, or null if the object with the specified ID is not found.
     */
    public Orders selectByID(int id)
    {
        return  super.findById(id);
    }


    /**
     * Updates an Orders object in the database by its ID.
     *
     * This method updates an Orders object in the database by calling the update method of the superclass.
     *
     * @param order The updated Orders object.
     * @param id The ID of the Orders object to update.
     * @return The number of rows affected by the update operation, or 1 if an error occurs.
     */
    public int update(Orders order,int id)
    {
        return super.update(order,id);
    }
}
