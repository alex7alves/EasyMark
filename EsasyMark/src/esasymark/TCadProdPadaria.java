package esasymark;


import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class TCadProdPadaria extends javax.swing.JFrame {
    
    Connection c = null;
    PreparedStatement s = null;
    ResultSet r;
    String login;

    public TCadProdPadaria(String log) throws ClassNotFoundException {
        initComponents();
        this.setLocationRelativeTo(null);
        c = ConectaBd.Conectabd();
        salvaredicao.setEnabled(false);
        login = log;
    }

    private TCadProdPadaria() throws ClassNotFoundException {
       initComponents();
        this.setLocationRelativeTo(null);
        c = ConectaBd.Conectabd();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    public void cadastrarProduto(){
        String csql = "Insert into \"Produto\"(codigo,nome,preco) values(?,?,?)";
       
        try{
            s = c.prepareStatement(csql);
            s.setString(1, txtcod.getText());
            s.setString(2, txtnome.getText());
            s.setInt(3, Integer.parseInt(txtpreco.getText()));
            s.executeUpdate();
            csql = "Insert into \"Padaria\"(idproduto,quantidade,validade,fornecedor) values(?,?,?,?)";
            s = c.prepareStatement(csql);
            s.setString(1, txtcod.getText());
            s.setInt(2, Integer.parseInt(txtquant.getText()));
            s.setString(3, txtval.getText());
            s.setString(4, txtforn.getText());
            s.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastro realizado", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        
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
    
   public void EditarcadastrarProduto(){
        String csql = "Update \"Produto\" set nome=?,preco=? where codigo=?";
       
        try{
            s = c.prepareStatement(csql);
            
            s.setString(1, txtnome.getText());
            s.setInt(2, Integer.parseInt(txtpreco.getText()));
             s.setString(3, txtcod.getText());
            s.executeUpdate();
            csql = "Update \"Padaria\" set quantidade=?,validade=?,fornecedor=? where idproduto=?";
            s = c.prepareStatement(csql);
            s.setString(4, txtcod.getText());
            s.setInt(1, Integer.parseInt(txtquant.getText()));
            s.setString(2, txtval.getText());
            s.setString(3, txtforn.getText());
            s.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastro realizado", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
public TCadProdPadaria(int ev, String log) throws ClassNotFoundException{
    initComponents();
    c = ConectaBd.Conectabd();
    login = log;
    if(ev==2){
        salvaredicao.setEnabled(true);
    }

}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtcod = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtnome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtpreco = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtquant = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtval = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        txtforn = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        salvaredicao = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Cadastro de Produtos de Padaria");

        jLabel2.setText("Código:");

        jLabel3.setText("Nome:");

        jLabel4.setText("Preço:");

        jLabel5.setText("Quantidade:");

        txtquant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtquantActionPerformed(evt);
            }
        });

        jLabel6.setText("Validade:");

        try {
            txtval.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel7.setText("Fornecedor:");

        txtforn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfornActionPerformed(evt);
            }
        });

        jButton1.setText("Cadastrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        salvaredicao.setText("Salvar edição");
        salvaredicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvaredicaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnome, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtpreco, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtquant)
                            .addComponent(txtval)
                            .addComponent(txtforn)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jButton1)
                        .addGap(42, 42, 42)
                        .addComponent(jButton2)
                        .addGap(27, 27, 27)
                        .addComponent(salvaredicao, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtnome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtpreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtquant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtforn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(salvaredicao))
                .addGap(41, 41, 41))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtquantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtquantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtquantActionPerformed

    private void txtfornActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfornActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfornActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       cadastrarProduto();
        try {
            telaRetorno();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TCadProdPadaria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            telaRetorno();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TCadProdPadaria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void salvaredicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvaredicaoActionPerformed
        // salvar edição
        EditarcadastrarProduto();
    }//GEN-LAST:event_salvaredicaoActionPerformed

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
            java.util.logging.Logger.getLogger(TCadProdPadaria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TCadProdPadaria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TCadProdPadaria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TCadProdPadaria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TCadProdPadaria().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TCadProdPadaria.class.getName()).log(Level.SEVERE, null, ex);
                }
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JButton salvaredicao;
    protected javax.swing.JTextField txtcod;
    protected javax.swing.JTextField txtforn;
    protected javax.swing.JTextField txtnome;
    protected javax.swing.JTextField txtpreco;
    protected javax.swing.JTextField txtquant;
    protected javax.swing.JFormattedTextField txtval;
    // End of variables declaration//GEN-END:variables
}
