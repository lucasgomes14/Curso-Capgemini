/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Task;
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
public class TaskController {
    
    public void save(Task task) throws SQLException {
        String sql = "INSERT INTO tasks (idProject"
                + ", name"
                + ", description"
                + ", completed"
                + ", notes"
                + ", deadLine"
                + ", createdAt"
                + ", updatedAt) VALUES (?,?,?,?,?,?,?,?)";
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            //estabelecendo a conexão com o banco de dados
            conn = ConnectionFactory.getConnection();
            
            //preparando a query
            statement = conn.prepareStatement(sql);
            
            //setando os valores do statement
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.getCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadLine().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            
            //executando a query
            statement.execute();
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar a tarefa" + e.getMessage(),e);
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }
    }
    
    public void update(Task task) {
        
        String sql = "UPDATE tasks SET idProject = ?, "
                + "name = ?, "
                + "description = ?, "
                + "completed = ?, "
                + "notes = ?, "
                + "deadLine = ?, "
                + "createdAt = ?, "
                + "updatedat = ? WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            //estabelecendo a conexão com o banco de dados
            conn = ConnectionFactory.getConnection();
            
            //preparando a query
            statement = conn.prepareStatement(sql);
            
            //setando os valores do statement
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.getCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadLine().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.setInt(9, task.getId());
            
            //executando a query
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar a tarefa" + e.getMessage(),e);
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }
    }
    
    public void removeById(int taskId) throws SQLException {
        String sql = "SELECT FROM tasks WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            //estabelecendo a conexão com o banco de dados
            conn = ConnectionFactory.getConnection();
            
            //preparando a query
            statement = conn.prepareStatement(sql);
            
            //setando os valores do statement
            statement.setInt(1, taskId);
            
            //executando a query
            statement.execute();
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar a tarefa");
        } finally {
            ConnectionFactory.closeConnection(conn);
        }
    }
    
    public List<Task> getAll(int idProject) {
        
        String sql = "SELECT * FROM tasks WHERE idProject = ?";
        
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        //lista de tarefas que será devolvida quando a chamada do método acontecer
        List<Task> tasks = new ArrayList<Task>();
        
        try {
            //estabelecendo a conexão com o banco de dados
            conn = ConnectionFactory.getConnection();
            
            //preparando a query
            statement = conn.prepareStatement(sql);
            
            //setando os valores do statement
            statement.setInt(1, idProject);
            
            //valor retornado pela execução da query
            resultSet = statement.executeQuery();
            
            //enquanto houverem valores a serem percorridos no meu resultSet
            while(resultSet.next()) {
                
                Task task = new Task();
                
                task.setId(resultSet.getInt("id"));
                task.setIdProject(resultSet.getInt("idProject"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setCompleted(resultSet.getBoolean("completed"));
                task.setNotes(resultSet.getString("notes"));
                task.setDeadLine(resultSet.getDate("deadLine"));
                task.setCreatedAt(resultSet.getDate("createdAt"));
                task.setUpdatedAt(resultSet.getDate("updateAt"));
                
                tasks.add(task);        
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao fazer lista das tarefas" + e.getMessage(),e);
        } finally {
            ConnectionFactory.closeConnection(conn, statement, resultSet);            
        }
        return tasks;
    }
    
}
