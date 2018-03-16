/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esasymark;


import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class TCadPedido extends javax.swing.JFrame {

    Connection c = null;
    PreparedStatement s = null;
    ResultSet r = null;
    String login = null;
    int idc;
    int flag,preco,preco2;
    
    public TCadPedido(String log) throws ClassNotFoundException {
        initComponents();
        this.setLocationRelativeTo(null);
        c = ConectaBd.Conectabd();
        salvaredicao.setEnabled(false);
        login = log;
    }
     public TCadPedido(int ev,String log) throws ClassNotFoundException {
        initComponents();
        this.setLocationRelativeTo(null);
        c = ConectaBd.Conectabd();
        login = log;
        if(ev==5){
            salvaredicao.setEnabled(true);
        }
    }

    private TCadPedido() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     public void telaRetorno() throws SQLException, ClassNotFoundException{
            String sql = "Select * from \"Funcionario\" where login = ?";
            s = c.prepareStatement(sql);
            s.setString(1, login);
            r = s.executeQuery();
            if(r.next()){
                if(r.getString("tipo").equals("funcionario")){
                    TFuncionario f;
                    f = new TFuncionario(login);
                    f.setVisible(true);
                    dispose();
                }else if(r.getString("tipo").equals("gerente")){
                    Gerente g;
                    g = new Gerente(login);
                    g.setVisible(true);
                    dispose();
                }else if(r.getString("tipo").equals("dono")){
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

   /* public void cadastrarPedido(){
        String csql = "Insert into \"Pedido\"(status,loginfunc,idcliente) values('Pendente',?,?)";
        try{
            s = c.prepareStatement(csql);
            s.setString(1, txtlogin.getText());
            s.setInt(2, Integer.parseInt(txtcliente.getText()));
            s.executeUpdate();
            login = txtlogin.getText();
            csql = "Select * from \"Pedido\" where loginfunc = ? and idcliente = ? and status = 'Pendente'";
            s = c.prepareStatement(csql);
            s.setString(1, txtlogin.getText());
            s.setInt(2, Integer.parseInt(txtcliente.getText()));
            r = s.executeQuery();
           
           
            
           
            if(r.next()){
               preco=0;
            if(flag==0){
                preco=r.getInt("preco"); 
                idc=r.getInt("idcliente");
                flag=1;
            }  
            csql = "Insert into \"PedidoProduto\"(idproduto,idpedido,quantidade) values(?,?,?)";
            s = c.prepareStatement(csql);
            s.setString(1, txtproduto.getText());
            s.setInt(2,r.getInt("id"));
            s.setInt(3, Integer.parseInt(txtquant.getText()));
            s.executeUpdate();
            csql = "Select * from \"Pedido\" p, \"PedidoProduto\" q, \"Produto\" t"
                    + " where p.idcliente = ?"
                    + "and p.id = q.idpedido and t.codigo = q.idproduto and p.id = ? and p.status = 'Pendente'";
            s = c.prepareStatement(csql);
            s.setInt(1, Integer.parseInt(txtcliente.getText()));
            s.setInt(2,r.getInt("id"));
            r = s.executeQuery();
            if(r.next()){
                csql = "Update \"Cliente\" set saldo = ? where id = ?";
                s = c.prepareStatement(csql);
                if(flag==1 && idc==Integer.parseInt(txtcliente.getText()));
                s.setInt(1,(r.getInt("preco")+ preco) * Integer.parseInt(txtquant.getText()));
                flag=0;
                }else {
                s.setInt(1,(r.getInt("preco")) * Integer.parseInt(txtquant.getText()));
                } 
                s.setInt(2,Integer.parseInt(txtcliente.getText()));
                s.executeUpdate();
                JOptionPane.showMessageDialog(null, "Cadastro realizado", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
            
            }else{
                JOptionPane.showMessageDialog(null, "Cadastro nao realizado");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }*/
     
     public void cadastrarPedido(){
        String csql = "Insert into \"Pedido\"(status,loginfunc,idcliente) values('Pendente',?,?)";
        int aux;
        try{
            s = c.prepareStatement(csql);
            s.setString(1, txtlogin.getText());
            s.setInt(2, Integer.parseInt(txtcliente.getText()));
            s.executeUpdate();
            login = txtlogin.getText();
            csql = "Select * from \"Pedido\" where loginfunc = ? and idcliente = ? and status = 'Pendente'";
            s = c.prepareStatement(csql);
            s.setString(1, txtlogin.getText());
            s.setInt(2, Integer.parseInt(txtcliente.getText()));
            r = s.executeQuery();
            if(r.next()){
                aux = r.getInt("id");
                csql = "Select * from \"Produto\" where nome = ?";
                s = c.prepareStatement(csql);
                s.setString(1, txtproduto.getText());
                r = s.executeQuery();
                if(r.next()){
                    csql = "Insert into \"PedidoProduto\"(idproduto,idpedido,quantidade) values(?,?,?)";
                    s = c.prepareStatement(csql);
                    s.setString(1, r.getString("codigo"));
                    s.setInt(2,aux);
                    s.setInt(3, Integer.parseInt(txtquant.getText()));
                    s.executeUpdate();
                    csql = "Select * from \"Pedido\" p, \"PedidoProduto\" q, \"Produto\" t"
                            + " where p.idcliente = ?"
                            + "and p.id = q.idpedido and t.codigo = q.idproduto and p.id = ? and p.status = 'Pendente'";
                    s = c.prepareStatement(csql);
                    s.setInt(1, Integer.parseInt(txtcliente.getText()));
                    s.setInt(2,aux);
                    r = s.executeQuery();
                    if(r.next()){
                        csql = "Update \"Cliente\" set saldo = ? where id = ?";
                        s = c.prepareStatement(csql);
                        s.setInt(1,r.getInt("preco") * Integer.parseInt(txtquant.getText()));
                        s.setInt(2,Integer.parseInt(txtcliente.getText()));
                        s.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Cadastro realizado", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null, "Nao realizou atualizacao de valor");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Cadastro nao realizado");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Produto nao encontrado");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
     
     /*public void cadastrarPedido(){
        String csql = "Insert into \"Pedido\"(status,loginfunc,idcliente) values('Pendente',?,?)";
        int aux;
        try{
            s = c.prepareStatement(csql);
            s.setString(1, txtlogin.getText());
            s.setInt(2, Integer.parseInt(txtcliente.getText()));
            s.executeUpdate();
            login = txtlogin.getText();
            csql = "Select * from \"Pedido\" where loginfunc = ? and idcliente = ? and status = 'Pendente'";
            s = c.prepareStatement(csql);
            s.setString(1, txtlogin.getText());
            s.setInt(2, Integer.parseInt(txtcliente.getText()));
            r = s.executeQuery();
            if(r.next()){
                aux = r.getInt("id");
                csql = "Select * from \"Produto\" where nome = ?";
                s = c.prepareStatement(csql);
                s.setString(1, txtproduto.getText());
                r = s.executeQuery();
                if(r.next()){
                    csql = "Insert into \"PedidoProduto\"(idproduto,idpedido,quantidade) values(?,?,?)";
                    s = c.prepareStatement(csql);
                    s.setString(1, r.getString("codigo"));
                    s.setInt(2,aux);
                    s.setInt(3, Integer.parseInt(txtquant.getText()));
                    s.executeUpdate();
                    csql = "Select * from \"Pedido\" p, \"PedidoProduto\" q, \"Produto\" t, \"Cliente\" c"
                            + " where p.idcliente = ? and c.id = p.cliente"
                            + "and p.id = q.idpedido and t.codigo = q.idproduto and p.id = ? and p.status = 'Pendente'";
                    
                    
                   csql = "Select * from \"Pedido\" p, \"PedidoProduto\" q, \"Produto\" t"
                    + " where p.idcliente = ?"
                    + "and p.id = q.idpedido and t.codigo = q.idproduto and p.id = ? and p.status = 'Pendente'";
            
                    s = c.prepareStatement(csql);
                    s.setInt(1, Integer.parseInt(txtcliente.getText()));
                    s.setInt(2,aux);
                    r = s.executeQuery();
                    if(r.next()){
                        csql = "Update \"Cliente\" set saldo = ? where id = ?";
                        s = c.prepareStatement(csql);
                        s.setInt(1,r.getInt("saldo") + r.getInt("preco") * Integer.parseInt(txtquant.getText()));
                        s.setInt(2,Integer.parseInt(txtcliente.getText()));
                        s.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Cadastro realizado", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null, "Nao realizou atualizacao de valor");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Cadastro nao realizado");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Produto nao encontrado");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }*/
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtcliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtproduto = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtlogin = new javax.swing.JTextField();
        salvaredicao = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtquant = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Cadastro de Pedidos");

        jLabel2.setText("Cliente:");

        txtcliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtclienteActionPerformed(evt);
            }
        });

        jLabel3.setText("Produto:");

        txtproduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtprodutoActionPerformed(evt);
            }
        });

        jButton1.setText("Cadastrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Sair");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Meu login:");

        salvaredicao.setText("Salvar edição");

        jLabel5.setText("Quantidade:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(salvaredicao, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtquant))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtproduto)
                                    .addComponent(txtcliente)
                                    .addComponent(txtlogin, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtproduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtquant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtlogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(salvaredicao))
                .addGap(34, 34, 34))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtclienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtclienteActionPerformed

    private void txtprodutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtprodutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtprodutoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cadastrarPedido();
        try {
            telaRetorno();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TCadPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            telaRetorno();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TCadPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(TCadPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TCadPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TCadPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TCadPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TCadPedido().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton salvaredicao;
    protected javax.swing.JTextField txtcliente;
    protected javax.swing.JTextField txtlogin;
    protected javax.swing.JTextField txtproduto;
    private javax.swing.JTextField txtquant;
    // End of variables declaration//GEN-END:variables
}
