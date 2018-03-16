/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esasymark;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Alex Alves
 */
public class DeletarItens extends javax.swing.JFrame {
   Connection con2;
    PreparedStatement pst2;
    ResultSet rs2 ;
    int ev ;
    
    String login;
    /**
     * Creates new form DeletarItens
     */
    public DeletarItens() throws ClassNotFoundException {
        initComponents();
        con2=ConectaBd.Conectabd();
    }
 public DeletarItens(int evento,String log) throws ClassNotFoundException {
        initComponents();
        con2=ConectaBd.Conectabd();
        ev=evento;
        login = log;
        if(evento ==1){
            jLabel1.setText(" Deletar funcionarios cadastrados ");
            ListarFuncionarios(); 
        }
         if(evento ==2){
            jLabel1.setText(" Deletar produtos da padaria ");
            ListarPadaria();
        }
         if(evento ==3){
            jLabel1.setText(" Deletar produtos do restaurante ");
            ListarRestaurante();
        }
        if(evento ==4){
            jLabel1.setText("Deletar funcionarios cadastrados ");
            ListarTodosFuncionarios(); 
        }
      
    }
 public void telaRetorno() throws SQLException, ClassNotFoundException{
            String sql = "Select * from \"Funcionario\" where login = ?";
            pst2 = con2.prepareStatement(sql);
            pst2.setString(1, login);
            rs2 = pst2.executeQuery();
            if(rs2.next()){
                if(rs2.getString("tipo").equals("funcionario")){
                    TFuncionario f;
                    f = new TFuncionario(login);
                    f.setVisible(true);
                    dispose();
                }else if(rs2.getString("tipo").equals("gerente")){
                    Gerente g;
                    g = new Gerente(login);
                    g.setVisible(true);
                    dispose();
                }else if(rs2.getString("tipo").equals("dono")){
                    dono d = new dono(login);
                    d.setVisible(true);
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Problema na passagem de paramentro");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Nenhum valor retornado");
            }
    }
public void ListarFuncionarios() throws ClassNotFoundException{
        String sql = "select *from \"Funcionario\" where tipo = 'funcionario'";
        try {
            pst2=con2.prepareStatement(sql);
            rs2= pst2.executeQuery();
            tblfuncionario.setModel(DbUtils.resultSetToTableModel(rs2));
            
       }catch(SQLException erro)
       {
           JOptionPane.showMessageDialog(null,erro);
       }
    }
public void ListarTodosFuncionarios() throws ClassNotFoundException{
        String sql = "select nome,tipo,salario from \"Funcionario\"";
        try {
            pst2=con2.prepareStatement(sql);
            rs2= pst2.executeQuery();
            tblfuncionario.setModel(DbUtils.resultSetToTableModel(rs2));
            
       }catch(SQLException erro)
       {
           JOptionPane.showMessageDialog(null,erro);
       }
    }

public void ListarPadaria() throws ClassNotFoundException{
        String sql = "select  p.codigo,p.nome,p.preco,f.quantidade, f.validade,f.fornecedor from \"Padaria\" f,\"Produto\" p where p.codigo = f.idproduto";
        try {
            pst2=con2.prepareStatement(sql);
            rs2= pst2.executeQuery();
            tblfuncionario.setModel(DbUtils.resultSetToTableModel(rs2));
            
       }catch(SQLException erro)
       {
           JOptionPane.showMessageDialog(null,erro);
       }
    }
public void ListarRestaurante() throws ClassNotFoundException{
        String sql = "select  p.codigo,p.nome,p.preco,f.ingredprinc, f.descricao from \"Restaurante\" f,\"Produto\" p where p.codigo = f.idproduto";
        try {
            pst2=con2.prepareStatement(sql);
            rs2= pst2.executeQuery();
            tblfuncionario.setModel(DbUtils.resultSetToTableModel(rs2));
            
       }catch(SQLException erro)
       {
           JOptionPane.showMessageDialog(null,erro);
       }
    }
public void DeletarFuncionario() throws ClassNotFoundException{
    int editar = tblfuncionario.getSelectedRow();    
    String log =tblfuncionario.getModel().getValueAt(editar,0).toString();
    String sql = "Delete from \"Funcionario\" where login = ?";
         try{
            pst2 = con2.prepareStatement(sql);
            pst2.setString(1, log);
           
            pst2.execute();
            JOptionPane.showMessageDialog(null, "O funcionario foi demitido", "Demitir", JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        } 
    }
public void DeletarProduto() throws ClassNotFoundException{
    int editar = tblfuncionario.getSelectedRow();    
    String cod =tblfuncionario.getModel().getValueAt(editar,0).toString();
    String sql = "Delete from \"Produto\" where codigo = ?";
         try{
            pst2 = con2.prepareStatement(sql);
            pst2.setString(1, cod);
           
            pst2.execute();
          /*  String sql2 = "Delete from \"Padaria\" where idproduto = ?";  
            pst2 = con2.prepareStatement(sql2);
            pst2.setString(1,cod);
           
            pst2.execute();*/
            JOptionPane.showMessageDialog(null, "Produto deletado", "Deletar", JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        } 
    }
public void DeletarCardapioRestaurante() throws ClassNotFoundException{
    int editar = tblfuncionario.getSelectedRow();    
    String cod =tblfuncionario.getModel().getValueAt(editar,0).toString();
    String sql = "Delete from \"Produto\" where codigo = ?";
         try{
            pst2 = con2.prepareStatement(sql);
            pst2.setString(1, cod);
           
            pst2.execute();
            String sql2 = "Delete from \"Restaurante\" where idproduto = ?";  
            pst2 = con2.prepareStatement(sql2);
            pst2.setString(1,cod);
           
            pst2.execute();
            JOptionPane.showMessageDialog(null, "Produto deletado", "Deletar", JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        } 
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblfuncionario = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("null");

        jLabel2.setText(" Selecione uma linha da tabela para editar o que foi  cadastrado ");

        tblfuncionario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblfuncionario);

        jButton1.setText("Sair");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Deletar cardapio ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Deletar produto");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Demitir funcionario");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       try {
           // Deletar funionario
           DeletarFuncionario();
           ListarTodosFuncionarios();
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(DeletarItens.class.getName()).log(Level.SEVERE, null, ex);
       }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       try {
           // Deletar produto
           DeletarProduto();
           ListarPadaria();
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(DeletarItens.class.getName()).log(Level.SEVERE, null, ex);
       }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       try {
           // Deletar cardapio do restaurante
           DeletarCardapioRestaurante();
           ListarRestaurante();
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(DeletarItens.class.getName()).log(Level.SEVERE, null, ex);
       }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       try {
           // TODO add your handling code here:
           telaRetorno();
       } catch (SQLException ex) {
           Logger.getLogger(DeletarItens.class.getName()).log(Level.SEVERE, null, ex);
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(DeletarItens.class.getName()).log(Level.SEVERE, null, ex);
       }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DeletarItens.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DeletarItens.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DeletarItens.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DeletarItens.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new DeletarItens().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(DeletarItens.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    protected javax.swing.JLabel jLabel1;
    protected javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    protected javax.swing.JTable tblfuncionario;
    // End of variables declaration//GEN-END:variables
}
