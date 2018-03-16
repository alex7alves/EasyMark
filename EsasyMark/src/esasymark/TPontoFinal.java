package esasymark;

import java.sql.*;
import java.util.Date;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class TPontoFinal extends javax.swing.JFrame {
    
    Connection c;
    PreparedStatement p,p2;
    ResultSet r,r2;
    String login,hi,hi2[];
    int InicioPonto;
    int FinalizarPonto;
    int Duracao,dd;
    public TPontoFinal(String log) throws ClassNotFoundException {
        initComponents();
        c = ConectaBd.Conectabd();
        login = log;
    }

    TPontoFinal() throws ClassNotFoundException {
    initComponents();
        c = ConectaBd.Conectabd();    
        System.out.println("haaaa  "+dd);
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
    
    public void finalizarPonto() throws SQLException, ClassNotFoundException{
        String sql = "Select * from \"Funcionario\" where login = ? and senha = ?";
        p = c.prepareStatement(sql);
        p.setString(1, txtlogin.getText());
        p.setString(2, txtsenha.getText());
        r = p.executeQuery();
        
        if(r.next()){
			String   sql2 = "Select horainic from \"Ponto\" where  cpffunc = ? and horaterm is null";
			p2 = c.prepareStatement(sql2);
			p2.setString(1, txtlogin.getText());
			r2 = p2.executeQuery();
		 
			if(r2.next()) {
				 hi = r2.getString("horainic");
				
			}
			hi2=hi.split(":");
		  
			String hd =hi2[0];
			int hd2 = Integer.parseInt(hd);
			String md =hi2[1];
			int md2 = Integer.parseInt(md);
			String sd =hi2[2];
			int sd2 = Integer.parseInt(sd);
			 
		  
			sql = "Update \"Ponto\" set horaterm = ?, duracao=? where daata = ? and cpffunc = ? and horaterm is null";
			String daata = DateFormat.getDateInstance().format(new Date());
			String time = DateFormat.getTimeInstance().format(new Date());
			Calendar calendar = new GregorianCalendar();
			Date trialTime = new Date();
			calendar.setTime(trialTime);
			int horaf = calendar.get(Calendar.HOUR_OF_DAY);
			int minutof = calendar.get(Calendar.MINUTE);
			int segundof = calendar.get(Calendar.SECOND);
			int temp1,temp2,temp3;
			temp1=(horaf*3600)+(minutof*60)+segundof;
			temp2=(hd2*3600)+(md2*60)+sd2;
			temp3=temp1-temp2;
			  
			 int hora2,segundo2,minuto2;
			 segundo2=temp3;
			 hora2= segundo2/3600;
			 segundo2=segundo2%3600;
			 minuto2=segundo2/60;
			 segundo2=segundo2%60;
           
             String time2 =(hora2)+":"+(minuto2)+":"+(segundo2);
         
            p = c.prepareStatement(sql);
            p.setString(1, time);
            p.setString(3, daata);
            p.setString(2, time2);
            p.setString(4, txtlogin.getText());
            int i = p.executeUpdate();
          
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtlogin = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtsenha = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Ponto para saida");

        jLabel2.setText("Confirme seus login e senha:");

        jLabel3.setText("Login:");

        txtlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtloginActionPerformed(evt);
            }
        });

        jLabel4.setText("Senha:");

        jButton1.setText("Realizar");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(115, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(112, 112, 112))
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(13, 13, 13)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                                .addComponent(jButton2))
                            .addComponent(txtlogin, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtsenha))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtlogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtsenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(52, 52, 52))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtloginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtloginActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            finalizarPonto();
            telaRetorno();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TPontoFinal.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            telaRetorno();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TPontoFinal.class.getName()).log(Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(TPontoFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TPontoFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TPontoFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TPontoFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TPontoFinal().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TPontoFinal.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JTextField txtlogin;
    private javax.swing.JPasswordField txtsenha;
    // End of variables declaration//GEN-END:variables
}
