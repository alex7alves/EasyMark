package esasymark;

import java.util.Date;
import java.text.DateFormat;
import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class TPonto extends javax.swing.JFrame {

    Connection c;
    PreparedStatement p;
    ResultSet r;
    String login;
    double InicoPonto;
    int hora,minuto,segundo;
    
    public TPonto(String log) throws ClassNotFoundException {
        initComponents();
        c = ConectaBd.Conectabd();
        this.setLocationRelativeTo(null);
        login = log;
    }

    TPonto() throws ClassNotFoundException {
    initComponents();
        c = ConectaBd.Conectabd();
        this.setLocationRelativeTo(null);        
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void telaRetorno() throws SQLException, ClassNotFoundException{
            String sql = "Select * from \"Funcionario\" where login = ?";
            p = c.prepareStatement(sql);
            p.setString(1, login);
            r = p.executeQuery();
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

    public void realizarPonto() throws SQLException, ClassNotFoundException{
        String sql = "Select * from \"Funcionario\" where login = ? and senha = ?";
        p = c.prepareStatement(sql);
        p.setString(1, txtlogin.getText());
        p.setString(2, txtsenha.getText());
        r = p.executeQuery();
        if(r.next()){
            sql = "Insert into \"Ponto\"(daata,horainic,horaterm,cpffunc,duracao) values(?,?,null,?,null)";
            String daata = DateFormat.getDateInstance().format(new Date());
          //  String time = DateFormat.getTimeInstance().format(new Date());
          Calendar calendar = new GregorianCalendar();
          Date trialTime = new Date();
          calendar.setTime(trialTime);
          hora = calendar.get(Calendar.HOUR_OF_DAY);
          minuto = calendar.get(Calendar.MINUTE);
          segundo = calendar.get(Calendar.SECOND);
          String time =hora+":"+minuto+":"+segundo;
          TPontoFinal tf = new TPontoFinal();
          tf.dd=hora;
            p = c.prepareStatement(sql);
            p.setString(1, daata);
            p.setString(2, time);
            p.setString(3, txtlogin.getText());
            int i = p.executeUpdate();
           // InicoPonto = Double.parseDouble(time);
            if(i == 1){
                JOptionPane.showMessageDialog(null, "Operacao realizada com sucesso");
            }else{
                JOptionPane.showMessageDialog(null, "Nao foi possivel realizar a operacao");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Funcionario nao encontrado");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtlogin = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtsenha = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Deseja fazer o ponto?");

        jButton1.setText("Sim");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Não");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Se sim, para validar a escolha, digite seus login e senha.");

        jLabel3.setText("Login:");

        txtlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtloginActionPerformed(evt);
            }
        });

        jLabel4.setText("Senha:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setText("Ponto para entrada");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtsenha)
                                    .addComponent(txtlogin))))
                        .addGap(0, 71, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(95, 95, 95))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtlogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtsenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            telaRetorno();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TPonto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            realizarPonto();
            telaRetorno();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TPonto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtloginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtloginActionPerformed

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
            java.util.logging.Logger.getLogger(TPonto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TPonto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TPonto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TPonto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TPonto().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TPonto.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JTextField txtlogin;
    private javax.swing.JPasswordField txtsenha;
    // End of variables declaration//GEN-END:variables
}
