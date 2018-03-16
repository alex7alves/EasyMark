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
public class TabelasFuncionario extends javax.swing.JFrame {
    Connection con2;
    PreparedStatement pst2;
    ResultSet rs2 ;
    int ev ;
    String login;
    public TabelasFuncionario() throws ClassNotFoundException {
        initComponents();
        con2=ConectaBd.Conectabd();
        //ListarFuncionarios();
    }
     public TabelasFuncionario(int evento, String log) throws ClassNotFoundException {
        initComponents();
        con2=ConectaBd.Conectabd();
        ev=evento;
        login = log;
        if(evento ==1){
            jLabel1.setText(" Funcionarios cadastrados ");
            ListarFuncionarios(); 
        }
         if(evento ==2){
            jLabel1.setText(" Produtos da padaria ");
            ListarPadaria();
        }
         if(evento ==3){
            jLabel1.setText(" Produtos do restaurante ");
            ListarRestaurante();
        }
        if(evento ==4){
            jLabel1.setText("Todos os funcionarios cadastrados ");
            ListarTodosFuncionarios(); 
        }
        if(evento ==5){
            jLabel1.setText(" Pedidos cadastrados ");
            ListarPedidos(); 
            // mudou de CPF para login logo devera fazer :
            // ALTER TABLE "Pedido" ALTER cpffunc TYPE varchar(100)
            // e jogar o login nele para não mudar as chaves extrangeiras
            /* e depois fazer
            ALTER TABLE nomedatabela CHANGE nomedacolunaatual novonomedacoluna varchar(100) not null; 
                
            exemplo: ALTER TABLE clientes CHANGE fone telefone varchar(100) not null;
            */
            
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
        String sql = "select *from \"Funcionario\"";
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
public void ListarPedidos() throws ClassNotFoundException{
        String sql = "select *from \"Pedido\"";
        try {
            pst2=con2.prepareStatement(sql);
            rs2= pst2.executeQuery();
            tblfuncionario.setModel(DbUtils.resultSetToTableModel(rs2));
            
       }catch(SQLException erro)
       {
           JOptionPane.showMessageDialog(null,erro);
       }
    }
public void EditarTabela() throws ClassNotFoundException{
         
        try {
            int editar = tblfuncionario.getSelectedRow();
           if(ev==1){
               TCadFuncionario tf = new TCadFuncionario(ev,login);
               tf.setVisible(true);
               tf.txtnome.setText(tblfuncionario.getModel().getValueAt(editar,1).toString());
               tf.txtcpf.setText(tblfuncionario.getModel().getValueAt(editar,16).toString());
               tf.txtdatnasc.setText(tblfuncionario.getModel().getValueAt(editar,10).toString());
               tf.txtrua.setText(tblfuncionario.getModel().getValueAt(editar,2).toString());
               tf.txtbairro.setText(tblfuncionario.getModel().getValueAt(editar,4).toString());
               tf.txtnum.setText(tblfuncionario.getModel().getValueAt(editar,3).toString());
               tf.txtcid.setText(tblfuncionario.getModel().getValueAt(editar,5).toString());
               tf.txtest.setText(tblfuncionario.getModel().getValueAt(editar,6).toString());
               tf.txtcep.setText(tblfuncionario.getModel().getValueAt(editar,8).toString());
               tf.txttel.setText(tblfuncionario.getModel().getValueAt(editar,9).toString());
               tf.txtcel.setText(tblfuncionario.getModel().getValueAt(editar,7).toString());
               tf.txtemail.setText(tblfuncionario.getModel().getValueAt(editar,13).toString());
               tf.txtdatent.setText(tblfuncionario.getModel().getValueAt(editar,14).toString());
               tf.txtfunc.setText(tblfuncionario.getModel().getValueAt(editar,11).toString());
               tf.txthor.setText(tblfuncionario.getModel().getValueAt(editar,15).toString());
               tf.txtsal.setText(tblfuncionario.getModel().getValueAt(editar,12).toString());
               tf.txtlog.setText(tblfuncionario.getModel().getValueAt(editar,0).toString());
               tf.txtsenha.setText(tblfuncionario.getModel().getValueAt(editar,17).toString());
               this.dispose();
           }
            
            if(ev ==2){
               TCadProdPadaria tpad = new TCadProdPadaria(ev,login);
               tpad.setVisible(true);
               tpad.txtcod.setText(tblfuncionario.getModel().getValueAt(editar,0).toString());
               tpad.txtnome.setText(tblfuncionario.getModel().getValueAt(editar,1).toString());
               tpad.txtpreco.setText(tblfuncionario.getModel().getValueAt(editar,2).toString());
               tpad.txtquant.setText(tblfuncionario.getModel().getValueAt(editar,3).toString());
               tpad.txtval.setText(tblfuncionario.getModel().getValueAt(editar,4).toString());
               tpad.txtforn.setText(tblfuncionario.getModel().getValueAt(editar,5).toString());
               this.dispose();
            }
            if(ev==3){
               TCadProduto tprod = new TCadProduto(ev,login);
               tprod.setVisible(true);
               tprod.txtcod.setText(tblfuncionario.getModel().getValueAt(editar,0).toString());
               tprod.txtnome.setText(tblfuncionario.getModel().getValueAt(editar,1).toString());
               tprod.txtpreco.setText(tblfuncionario.getModel().getValueAt(editar,2).toString());
               tprod.txtingr.setText(tblfuncionario.getModel().getValueAt(editar,3).toString());
               tprod.txtdesc.setText(tblfuncionario.getModel().getValueAt(editar,4).toString());
               this.dispose();
            }
             if(ev==4){
               TCadFuncionarioDono tfd = new TCadFuncionarioDono(ev,login);
               tfd.setVisible(true);
               tfd.txtnome.setText(tblfuncionario.getModel().getValueAt(editar,1).toString());
               tfd.txtcpf.setText(tblfuncionario.getModel().getValueAt(editar,16).toString());
               tfd.txtdatnasc.setText(tblfuncionario.getModel().getValueAt(editar,10).toString());
               tfd.txtrua.setText(tblfuncionario.getModel().getValueAt(editar,2).toString());
               tfd.txtbairro.setText(tblfuncionario.getModel().getValueAt(editar,4).toString());
               tfd.txtnum.setText(tblfuncionario.getModel().getValueAt(editar,3).toString());
               tfd.txtcid.setText(tblfuncionario.getModel().getValueAt(editar,5).toString());
               tfd.txtest.setText(tblfuncionario.getModel().getValueAt(editar,6).toString());
               tfd.txtcep.setText(tblfuncionario.getModel().getValueAt(editar,8).toString());
               tfd.txttel.setText(tblfuncionario.getModel().getValueAt(editar,9).toString());
               tfd.txtcel.setText(tblfuncionario.getModel().getValueAt(editar,7).toString());
               tfd.txtemail.setText(tblfuncionario.getModel().getValueAt(editar,13).toString());
               tfd.txtdatent.setText(tblfuncionario.getModel().getValueAt(editar,14).toString());
               tfd.txtfunc.setText(tblfuncionario.getModel().getValueAt(editar,11).toString());
               tfd.txthor.setText(tblfuncionario.getModel().getValueAt(editar,15).toString());
               tfd.txtsal.setText(tblfuncionario.getModel().getValueAt(editar,12).toString());
               tfd.txtlog.setText(tblfuncionario.getModel().getValueAt(editar,0).toString());
               tfd.txtsenha.setText(tblfuncionario.getModel().getValueAt(editar,17).toString());
               tfd.txttipo.setText(tblfuncionario.getModel().getValueAt(editar,18).toString());
               this.dispose();
           }
             if(ev==5){
               TCadPedido tped = new TCadPedido(ev,login);
               tped.setVisible(true);
               tped.txtcliente.setText(tblfuncionario.getModel().getValueAt(editar,0).toString());
               tped.txtproduto.setText(tblfuncionario.getModel().getValueAt(editar,1).toString());
               tped.txtlogin.setText(tblfuncionario.getModel().getValueAt(editar,2).toString());
               this.dispose();             
            }
       }catch(Exception erro)
       {
            JOptionPane.showMessageDialog(null,erro);
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

        jButton1.setText("Editar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Atualizar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Sair");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)))
                .addGap(0, 102, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Atualizar
        try {
            if(ev ==1){
            jLabel1.setText(" Funcionarios cadastrados ");
            ListarFuncionarios(); 
        }
         if(ev ==2){
            jLabel1.setText(" Produtos da padaria ");
            ListarPadaria();
        }
         if(ev==3){
            jLabel1.setText(" Produtos do restaurante ");
            ListarRestaurante();
        }
        if(ev ==4){
            jLabel1.setText("Todos os funcionarios cadastrados ");
            ListarTodosFuncionarios(); 
        }
        if(ev ==5){
            jLabel1.setText(" Pedidos cadastrados ");
            ListarPedidos();
        }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Gerente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Editar
        try {
            EditarTabela();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TabelasFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            // sair
            telaRetorno();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TabelasFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(TabelasFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TabelasFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TabelasFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TabelasFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TabelasFuncionario().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TabelasFuncionario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblfuncionario;
    // End of variables declaration//GEN-END:variables
}
