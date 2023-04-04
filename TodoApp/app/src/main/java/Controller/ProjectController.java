/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Project;
import Util.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucas
 */
public class ProjectController {
    
    public void save(Project project) {
        
        String sql = "INSERT INTO projects(name, "
                + "description, "
                + "createdAt, "
                + "updatedAt) VALUES (?, ?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            //cria conexão ao banco de dados
            conn = ConnectionFactory.getConnection();
            
            //preparando a query
            statement = conn.prepareStatement(sql);
            
            //setando os valores dos statements
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            
            //executando a query
            statement.execute();
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar o projeto" + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }
    }
    
    public void update(Project project) {
        
        String sql = "UPDATE projects SET name = ?, "
                + "description = ?, "
                + "createdAt = ? "
                + "updatedAt = ? WHARE id = ?";
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            //estabelecendo conexão com o banco de dados
            conn = ConnectionFactory.getConnection();
            
            //preparando a query
            statement = conn.prepareStatement(sql);
            
            //setando os statements
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            statement.setInt(5, project.getId());
            
            //executando query
            statement.execute();
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o projeto" + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }
        
    }
    
    public void removeById(int id) throws SQLException {
        
        String sql = "DELETE FROM projects WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            //estabelecendo a conexão com o banco de dados
            conn = ConnectionFactory.getConnection();
            
            //Preparando a query
            statement = conn.prepareStatement(sql);
            
            //setando o statement
            statement.setInt(1, id);
            
            //executando query
            statement.execute();

        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar projeto");
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }
        
    }
    
    public List<Project> getAll() {
        
        String sql = "SELECT * FROM projects";
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        //classe que vai recuperar os dados do banco de dados
        ResultSet resultSet = null;
        
        List<Project> projects = new ArrayList<Project>();
        
        try {
            //Estabelencendo conexão com o banco de dados
            conn = ConnectionFactory.getConnection();
            
            //Preparando a query
            statement = conn.prepareStatement(sql);
            
            //valor retornado pela execução da query
            resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
                
                Project project = new Project();
                
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setCreatedAt(resultSet.getDate("createdAt"));
                project.setUpdatedAt(resultSet.getDate("updatedAt"));
                
                projects.add(project);
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao fazer lista dos projetos" + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(conn, statement, resultSet);
        }
        
        return projects;
    }
}
