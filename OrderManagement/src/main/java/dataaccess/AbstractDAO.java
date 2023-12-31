package dataaccess;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 * Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 * @Source http://www.java-blog.com/mapping-javaobjects-database-reflection-generics
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    /**
     * Creates a SELECT query with a specified field.
     *
     * This method constructs a SELECT query that retrieves all columns
     * from a table and filters the result based on the specified field.
     *
     * @param field The field used for filtering the result.
     * @return The generated SELECT query as a String.
     */
    protected String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " = ? ;");

        return sb.toString();
    }

    /**
     * Creates a SELECT query to retrieve all records from a table.
     *
     * This method constructs a SELECT query that retrieves all columns
     * from a table without any filters.
     *
     * @return The generated SELECT query as a String.
     */
    protected String createSelectQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(";");

        return sb.toString();
    }

    /**
     * Creates a DELETE query to delete a record from a table based on the specified ID.
     *
     * This method constructs a DELETE query that deletes a record from a table
     * based on the provided ID value.
     *
     * @param id The ID value used to identify the record to be deleted.
     * @return The generated DELETE query as a String.
     */
    protected String createDeleteQuery(int id) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM " + type.getSimpleName() + " WHERE " + type.getDeclaredFields()[0].getName() + "=" + id+";");

        return sb.toString();
    }

    /**
     * Creates an INSERT query to insert a record into a table.
     *
     * This method constructs an INSERT query that inserts a record into
     * a table based on the provided object `t`. The object is assumed to be
     * of type `T`.
     *
     * @param t The object representing the record to be inserted.
     * @return The generated INSERT query as a String.
     */

    protected String createInsertQuery(T t) {

        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName() + " (");
        int idJump = 1;
        int stArg = 1;

        for (Field field : type.getDeclaredFields()) {
            if (idJump == 1) {
                idJump = 0;
                continue;
            }
            if (stArg == 1) {
                sb.append(field.getName());
                stArg = 0;

            } else {
                sb.append(", " + field.getName());
            }

        }

        sb.append(") VALUES (");


        try {
            stArg = 1;
            idJump = 1;
            for (Field field : type.getDeclaredFields()) {
                PropertyDescriptor propertyDescriptor = null;
                propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                Method method = propertyDescriptor.getReadMethod();
                Object value = method.invoke(t);

                if (idJump == 1) {
                    idJump = 0;
                    continue;

                }
                if (stArg == 1) {
                    if (value instanceof String) {
                        sb.append("'" + value + "'");
                    } else {
                        sb.append(value);
                    }
                    stArg = 0;
                } else {
                    if (value instanceof String) {
                        sb.append(", '" + value + "'");
                    } else {
                        sb.append(", " + value);
                    }
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        sb.append(");");

        return sb.toString();
    }


    /**
     * Creates an UPDATE query to update a record in a table based on the specified ID and object.
     *
     * This method constructs an UPDATE query that updates a record in a table
     * based on the provided object `t` and the specified ID. The object is assumed to be
     * of type `T`.
     *
     * @param t  The object representing the updated record.
     * @param id The ID value used to identify the record to be updated.
     * @return The generated UPDATE query as a String.
     */
    protected String createUpdateQuery(T t, int id) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE " + type.getSimpleName() + " SET ");
        try {
            int idArg = 1;
            int stArg = 1;
            String condition = "";
            for (Field field : type.getDeclaredFields()) {
                PropertyDescriptor propertyDescriptor = null;
                propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                Method method = propertyDescriptor.getReadMethod();
                Object value = method.invoke(t);

                if (idArg == 1) {
                    condition = field.getName() + "=" + id ;
                    idArg = 0;
                    continue;
                }
                if (stArg == 1) {
                    if (value instanceof String) {
                        sb.append(field.getName() + "='" + value.toString() + "'");
                    } else {
                            sb.append(field.getName()+ "="+ value );
                    }
                    stArg =0;
                }
                else{
                    if (value instanceof String) {
                        sb.append(","+field.getName() + "='" + value.toString() + "'");
                    } else {
                        sb.append(","+field.getName()+ "="+ value );
                    }
                }
            }
            sb.append("WHERE "+ condition +";");
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }


    /**
     * Creates a list of objects of type T from a ResultSet.
     *
     * This method takes a ResultSet and constructs a list of objects of type T
     * by mapping the column values to the corresponding fields in the object.
     *
     * @param resultSet The ResultSet containing the data.
     * @return A list of objects of type T.
     */
    protected List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T) ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * Finds an object of type T by its ID.
     *
     * This method retrieves an object of type T from the database by its ID.
     *
     * @param id The ID of the object to find.
     * @return The found object of type T, or null if not found.
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "FindById in AbstractDao" + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }


    /**
     * Finds all objects of type T.
     *
     * This method retrieves all objects of type T from the database.
     *
     * @return An ArrayList containing all objects of type T, or null if an error occurs.
     */
    public ArrayList<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery();
        try {
            connection = ConnectionFactory.getConnection();


            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return (ArrayList<T>) createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "FindAll in AbstractDao" + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return null;
    }


    /**
     * Inserts an object of type T into the database.
     *
     * This method inserts an object of type T into the database.
     *
     * @param t The object of type T to insert.
     * @return The number of rows affected by the insert operation, or 1 if an error occurs.
     */

    public int insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery(t);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            return  statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "Insert in AbstractDao" + e.getMessage());
        } finally {

            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 1;
    }



    /**
     * Updates an object of type T in the database.
     *
     * This method updates an object of type T in the database using the specified ID.
     *
     * @param t The object of type T to update.
     * @param id The ID of the object to update.
     * @return The number of rows affected by the update operation, or 1 if an error occurs.
     */
    public int update(T t, int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createUpdateQuery(t,id);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            return  statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "Update in AbstractDao" + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 1;
    }


    /**
     * Deletes an object from the database by its ID.
     *
     * This method deletes an object from the database by its ID.
     *
     * @param id The ID of the object to delete.
     * @return The number of rows affected by the delete operation, or 1 if an error occurs.
     */
    public int delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createDeleteQuery(id);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            return  statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "Delete one object in AbstractDao" + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 1;
    }

}







