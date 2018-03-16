package esasymark;


import java.sql.*;
import javax.swing.JOptionPane;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TCadFuncionario extends javax.swing.JFrame {
    
    Connection c = null;
    PreparedStatement s = null;
    ResultSet r;
    String login;
    
    public TCadFuncionario(String log) throws ClassNotFoundException {
        initComponents();
        this.setLocationRelativeTo(null);
        c =ConectaBd.Conectabd();
        salvaredicao.setEnabled(false);
        login = log;
    }
    
    public TCadFuncionario(int ev,String log) throws ClassNotFoundException {
        initComponents();
        this.setLocationRelativeTo(null);
        c =ConectaBd.Conectabd();
        login = log;
        if(ev==1){
            salvaredicao.setEnabled(true);
        }
    }

    private TCadFuncionario(int ev) throws ClassNotFoundException {
        initComponents();
        this.setLocationRelativeTo(null);
        c =ConectaBd.Conectabd();
       // login = log;
        if(ev==1){
            salvaredicao.setEnabled(true);
        }        

//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private TCadFuncionario() {
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
    
    public void cadastrarFuncionario(){
        String csql = "Insert into \"Funcionario\"(login,nome,rua,numero,bairro,"
                + "cidade,estado,celular,cep,telefone,datanasc,funcao,salario,email,dataent,horario,"
                + "cpf,senha,tipo) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            s = c.prepareStatement(csql);
            s.setString(1, txtlog.getText());
            s.setString(2, txtnome.getText());
            s.setString(3, txtrua.getText());
            s.setString(4, txtnum.getText());
            s.setString(5, txtbairro.getText());
            s.setString(6, txtcid.getText());
            s.setString(7, txtest.getText());
            s.setString(8, txtcel.getText());
            s.setString(9, txtcep.getText());
            s.setString(10, txttel.getText());
            s.setString(11, txtdatnasc.getText());
            s.setString(12, txtfunc.getText());
            s.setInt(13, Integer.parseInt(txtsal.getText()));
            s.setString(14, txtemail.getText());
            s.setString(15, txtdatent.getText());
            s.setString(16, txthor.getText());
            s.setString(17, txtcpf.getText());
            s.setString(18, txtsenha.getText());
            s.setString(19, "funcionario");
            s.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastro realizado", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }   
    }
public void EditarFuncionario() {
     String csql = "Update \"Funcionario\" set nome=?,rua=?,numero=?,bairro=?,"
                + "cidade=?,estado=?,celular=?,cep=?,telefone=?,datanasc=?,funcao=?,salario=?,email=?,dataent=?,horario=?,"
                + "cpf=?,senha=?,tipo=? where login=?";
        try{
            s = c.prepareStatement(csql);
            s.setString(19, txtlog.getText());
            s.setString(1, txtnome.getText());
            s.setString(2, txtrua.getText());
            s.setString(3, txtnum.getText());
            s.setString(4, txtbairro.getText());
            s.setString(5, txtcid.getText());
            s.setString(6, txtest.getText());
            s.setString(7, txtcel.getText());
            s.setString(8, txtcep.getText());
            s.setString(9, txttel.getText());
            s.setString(10, txtdatnasc.getText());
            s.setString(11, txtfunc.getText());
            s.setInt(12, Integer.parseInt(txtsal.getText()));
            s.setString(13, txtemail.getText());
            s.setString(14, txtdatent.getText());
            s.setString(15, txthor.getText());
            s.setString(16, txtcpf.getText());
            s.setString(17, txtsenha.getText());
            s.setString(18, "funcionario");
            s.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastro realizado", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }   
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtnome = new javax.swing.JTextField();
        txtcpf = new javax.swing.JFormattedTextField();
        txtdatnasc = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        txtrua = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtbairro = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtnum = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtcid = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtest = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtcep = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        txttel = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        txtcel = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtdatent = new javax.swing.JFormattedTextField();
        jLabel15 = new javax.swing.JLabel();
        txtfunc = new javax.swing.JTextField();
        txthor = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtsal = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtlog = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtsenha = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        salvaredicao = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Cadastro de Funcionários");

        jLabel2.setText("Nome:");

        jLabel3.setText("CPF:");

        jLabel4.setText("Data de Nascimento:");

        try {
            txtcpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtdatnasc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel5.setText("Rua:");

        jLabel6.setText("Bairro:");

        txtbairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbairroActionPerformed(evt);
            }
        });

        jLabel7.setText("Número:");

        txtnum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnumActionPerformed(evt);
            }
        });

        jLabel8.setText("Cidade:");

        jLabel9.setText("Estado:");

        jLabel10.setText("CEP:");

        try {
            txtcep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtcep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcepActionPerformed(evt);
            }
        });

        jLabel11.setText("Telefone:");

        try {
            txttel.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel12.setText("Celular:");

        try {
            txtcel.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel13.setText("Email:");

        jLabel14.setText("Data de Entrada:");

        try {
            txtdatent.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel15.setText("Função:");

        txtfunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfuncActionPerformed(evt);
            }
        });

        jLabel17.setText("Horários:");

        jLabel18.setText("Salário:");

        jLabel19.setText("Login:");

        jLabel20.setText("Senha:");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jButton1)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(txtnome)
                            .addComponent(txtcpf, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(txtdatnasc)
                            .addComponent(txtrua)
                            .addComponent(txtbairro)
                            .addComponent(txtnum)
                            .addComponent(txtcid)
                            .addComponent(txtest)
                            .addComponent(txtcep)
                            .addComponent(txttel)
                            .addComponent(txtcel)
                            .addComponent(txtemail)
                            .addComponent(txtdatent)
                            .addComponent(txtfunc)
                            .addComponent(txthor)
                            .addComponent(txtsal)
                            .addComponent(txtlog)
                            .addComponent(txtsenha))
                        .addGap(35, 35, 35))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(salvaredicao, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(93, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtnome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtcpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtdatnasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtrua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtbairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtnum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtcid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtcep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txttel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtcel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtdatent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtfunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtsal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtlog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtsenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(salvaredicao))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtbairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbairroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbairroActionPerformed

    private void txtnumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnumActionPerformed

    private void txtcepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcepActionPerformed

    private void txtfuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfuncActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfuncActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cadastrarFuncionario();
        try {
            telaRetorno();
        } catch (SQLException ex) {
            Logger.getLogger(TCadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TCadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            telaRetorno();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TCadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void salvaredicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvaredicaoActionPerformed
        // Salvar edição
        EditarFuncionario();
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
            java.util.logging.Logger.getLogger(TCadFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TCadFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TCadFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TCadFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                    new TCadFuncionario().setVisible(true);                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton salvaredicao;
    protected javax.swing.JTextField txtbairro;
    protected javax.swing.JFormattedTextField txtcel;
    protected javax.swing.JFormattedTextField txtcep;
    protected javax.swing.JTextField txtcid;
    protected javax.swing.JFormattedTextField txtcpf;
    protected javax.swing.JFormattedTextField txtdatent;
    protected javax.swing.JFormattedTextField txtdatnasc;
    protected javax.swing.JTextField txtemail;
    protected javax.swing.JTextField txtest;
    protected javax.swing.JTextField txtfunc;
    protected javax.swing.JTextField txthor;
    protected javax.swing.JTextField txtlog;
    protected javax.swing.JTextField txtnome;
    protected javax.swing.JTextField txtnum;
    protected javax.swing.JTextField txtrua;
    protected javax.swing.JTextField txtsal;
    protected javax.swing.JPasswordField txtsenha;
    protected javax.swing.JFormattedTextField txttel;
    // End of variables declaration//GEN-END:variables
}
