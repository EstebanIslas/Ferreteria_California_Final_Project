/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Oscar
 */
public class Login extends javax.swing.JFrame {
    private final String bd = "ferre_cali";
    private final String user = "root";
    private final String password = "";
    private final String url = "jdbc:mysql://localhost:3306/" + bd;
    PreparedStatement ps;
    ResultSet rs;
    
    
    private Connection con = null;
    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        Conexion con = new Conexion();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtf_usuario = new javax.swing.JTextField();
        jtf_password = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jb_iniciar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setForeground(new java.awt.Color(255, 102, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo_cali_opt.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Usuario");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Contrasaseña");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, -1, -1));
        jPanel1.add(jtf_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, 119, -1));
        jPanel1.add(jtf_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 320, 119, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/usuario.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 130, 130));

        jb_iniciar.setBackground(new java.awt.Color(255, 102, 0));
        jb_iniciar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jb_iniciar.setForeground(new java.awt.Color(255, 255, 255));
        jb_iniciar.setText("INICIAR");
        jb_iniciar.setContentAreaFilled(false);
        jb_iniciar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_iniciar.setOpaque(true);
        jb_iniciar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_iniciarMouseClicked(evt);
            }
        });
        jb_iniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_iniciarActionPerformed(evt);
            }
        });
        jPanel1.add(jb_iniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 380, 190, 33));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jb_iniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_iniciarActionPerformed

 
    }//GEN-LAST:event_jb_iniciarActionPerformed
    public Connection getConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 000 " + ex.getMessage());
        }
        return con;
    }
    
    
    private void jb_iniciarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_iniciarMouseClicked
        String usuario = "admin";
        String password = "admin";
        String usuario2 = "user";
        String password2 = "user";
        
        //String username = jtf_usuario.getText();
        //String contrasena = jtf_password.getText();
        
        String pass = new String(jtf_password.getPassword());
        String pass2 = new String(jtf_password.getPassword());
        //con = getConexion();
        
        
        //try {
            //ps = con.prepareStatement("SELECT username, contrasena FROM usuarios");
            
            //rs = ps.executeQuery();
            
            //String user = rs.getString("username");
            //String pas = rs.getString("contrasena");
              
            if (jtf_usuario.getText().equals(usuario) && pass.equals(password)){
   
                Inicio ini = new Inicio(1);
                ini.setVisible(true);
                dispose();
                

            }else if(jtf_usuario.getText().equals(usuario2) && pass.equals(password2)){
                Inicio ini = new Inicio(2);
                ini.setVisible(true);
                dispose();
                
                
            }else{
                JOptionPane.showMessageDialog(this,"Usuario o contraseña incorectos");
            }   
        /* 
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }*/
                
       
        
        
        
      
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jb_iniciarMouseClicked

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jb_iniciar;
    private javax.swing.JPasswordField jtf_password;
    private javax.swing.JTextField jtf_usuario;
    // End of variables declaration//GEN-END:variables
}