/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esasymark;

/**
 *
 * @author Alex Alves
 */
import java.sql.*;
import javax.swing.JOptionPane;
public class ConectaBd {
    public static Connection Conectabd() throws ClassNotFoundException {
        
        try {
          // conecta com o banco
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/easymark","postgres","rev");
            //JOptionPane.showMessageDialog(null, "Conectado com sucesso");
            return con;
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null, erro);
            return null;
        } 
       
    }
}

