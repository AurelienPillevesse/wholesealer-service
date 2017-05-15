package io.github.aurelienpillevesse.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class DAO<T> {
    protected Connection connect = null;
    protected ResultSet rs = null;
	protected PreparedStatement st = null;

    public DAO(){
        String dbUrl = "jdbc:postgresql://ec2-54-228-235-185.eu-west-1.compute.amazonaws.com:5432/d8idsnbp0ajcc9?user=sravfdspfvqyrc&password=7737a286eb833d0fb3a9d778b5da20847e7a49064acd7e4a170d2e8367aa8332&sslmode=require";
        try {
        	Class.forName("org.postgresql.Driver");
			this.connect = DriverManager.getConnection(dbUrl);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	        System.err.println(e.getClass().getName()+": "+e.getMessage());
	        System.exit(0);
		}
    }

    /**
    * Create object
    * @param object
    * @return boolean
    */
    public abstract boolean create(T object);

    /**
    * Delete object
    * @param object
    * @return boolean
    */
    public abstract boolean delete(T object);

    /**
    * Update object
    * @param object
    * @return boolean
    */
    public abstract void updateStock(T object);

    /**
    * Search by isbn
    * @param isbn
    * @return T
    */
    public abstract T find(String isbn);
    
    /**
     * Search all books
     * @return T
     */
     public abstract List<T> findAll();
}
