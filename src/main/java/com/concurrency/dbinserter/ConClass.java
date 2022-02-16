package com.concurrency.dbinserter;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConClass {
    private static ConClass conClass;
    private Connection connection;

    private ConClass() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/mock",
            "intellij", "intellij");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection(){
        return this.connection;
    }

    public static ConClass getInstance(){
        try {
            if (conClass == null) {
                conClass = new ConClass();
            } else if (conClass.getConnection().isClosed()) {
                conClass = new ConClass();
            }
            return conClass;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return conClass;
    }

}
