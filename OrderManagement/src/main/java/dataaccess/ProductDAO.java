package dataaccess;


import model.Product;

public class ProductDAO extends AbstractDAO<Product>{


    /**
     * Inserts a Product object into the database.
     *
     * This method inserts a Product object into the database by calling the insert method of the superclass.
     *
     * @param product The Product object to insert into the database.
     * @return The number of rows affected by the insert operation, or 1 if an error occurs.
     */
    public int insert(Product product) {

        return  super.insert(product);
    }

    /**
     * Deletes a record from the database by its ID.
     *
     * This method deletes a record from the database by calling the delete method of the superclass.
     *
     * @param id The ID of the record to delete.
     * @return The number of rows affected by the delete operation, or 1 if an error occurs.
     */
    public int delete(int id){
        return  super.delete(id);
    }


    /**
     * Retrieves a Product object from the database by its ID.
     *
     * This method retrieves a Product object from the database by calling the findById method of the superclass.
     *
     * @param id The ID of the Product object to retrieve.
     * @return The retrieved Product object, or null if no record with the specified ID is found.
     */
    public Product selectByID(int id)
    {
        return  super.findById(id);
    }


    /**
     * Updates a Product object in the database by its ID.
     *
     * This method updates a Product object in the database by calling the update method of the superclass.
     *
     * @param product The Product object containing the updated data.
     * @param id The ID of the Product object to update.
     * @return The number of rows affected by the update operation, or 1 if an error occurs.
     */
    public int update(Product product,int id)
    {
        return super.update(product,id);
    }

}
