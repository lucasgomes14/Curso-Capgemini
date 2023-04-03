/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author lucas
 */
public class ConnectionFactory {
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/TodoApp";
    public static final String USER = "curso";
    public static final String PASS = "12345678";
    
    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            throw new RuntimeException("Erro na conexão do banco de dados", e);
        }
    }
    
    public static void closeConnection(Connection connection) {
        try {
            if(connection != null)
                connection.close();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao fechar a conexão com o banco de dados", e);
        }
    }
    
    public static void closeConnection(Connection connection, PreparedStatement statement) {
        try {
            if(connection != null)
                connection.close();
            if(statement != null)
                statement.close();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao fechar a conexão com o banco de dados", e);
        }
    }
    
    public static void closeConnection(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        try {
            if(connection != null)
                connection.close();
            if(statement != null)
                statement.close();
            if(resultSet != null)
                resultSet.close();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao fechar a conexão com o banco de dados", e);
        }
    }
}
