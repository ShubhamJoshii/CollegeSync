package college;

import CollegeSync.DBConnection;
import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

public class Login extends javax.swing.JPanel {

    MainFrame main;
    private String selectedRole;

    public Login(MainFrame main) {
        initComponents();
        this.main = main;
        this.selectedRole = "Student";
        registerBtn.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        signinButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        studentButton1 = new javax.swing.JButton();
        facultyButton1 = new javax.swing.JButton();
        adminButton1 = new javax.swing.JButton();
        registerBtn = new javax.swing.JButton();
        password = new javax.swing.JPasswordField();
        jLabel12 = new javax.swing.JLabel();
        homeRedirectBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(248, 251, 255));
        setName("Login "); // NOI18N
        setPreferredSize(new java.awt.Dimension(1400, 950));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Logo.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("COLLEGE MANAGEMENT SYSTEM");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel6.setText("Login Panel");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Select your role and enter your credentials");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setText("Email");

        email.setText("admin@gmail.com");
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setText("Password");

        signinButton1.setBackground(new java.awt.Color(0, 0, 0));
        signinButton1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        signinButton1.setForeground(new java.awt.Color(255, 255, 255));
        signinButton1.setText("Sign in as Student");
        signinButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signinButton1ActionPerformed(evt);
            }
        });

        studentButton1.setBackground(new java.awt.Color(21, 93, 252));
        studentButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        studentButton1.setForeground(new java.awt.Color(255, 255, 255));
        studentButton1.setText("Student");
        studentButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentButton1ActionPerformed(evt);
            }
        });

        facultyButton1.setBackground(new java.awt.Color(242, 242, 242));
        facultyButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        facultyButton1.setText("Faculty");
        facultyButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facultyButton1ActionPerformed(evt);
            }
        });

        adminButton1.setBackground(new java.awt.Color(242, 242, 242));
        adminButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        adminButton1.setText("Admin");
        adminButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(studentButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(facultyButton1)
                .addGap(55, 55, 55)
                .addComponent(adminButton1)
                .addGap(22, 22, 22))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(studentButton1)
                    .addComponent(facultyButton1)
                    .addComponent(adminButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        registerBtn.setText("register");
        registerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerBtnActionPerformed(evt);
            }
        });

        password.setText("Joshi@123");
        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(registerBtn)
                    .addComponent(signinButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                    .addComponent(email)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(password))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addComponent(signinButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(registerBtn)
                .addGap(37, 37, 37))
        );

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("© 2025 EduManage. All rights reserved.");

        homeRedirectBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        homeRedirectBtn.setText("HOME");
        homeRedirectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeRedirectBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(homeRedirectBtn)
                .addGap(38, 38, 38))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1388, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(467, 467, 467)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(homeRedirectBtn)
                .addGap(64, 64, 64)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("Login");
    }// </editor-fold>//GEN-END:initComponents

    private String checkValidation(char[] pass) {
        // Email Validation
        if (!email.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            email.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            return "Please enter a valid email address.";
        }
        email.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        // Password Validation
        if (pass.length < 6) {
            password.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            return "Password must be at least 6 characters long.";
        }
        password.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        return null;
    }

    private void signinButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signinButton1ActionPerformed
        char[] pass = password.getPassword();
        String result = checkValidation(pass);
        if (result == null) {
            try {
                Connection conn = DBConnection.getConnection();

                String sql = "SELECT * FROM users WHERE email = ? AND role = ?";
                PreparedStatement pst = conn.prepareStatement(sql);

                pst.setString(1, email.getText());
                pst.setString(2, selectedRole);

                ResultSet res = pst.executeQuery();

                if (res.next()) {
                    String hashedPassword = res.getString("password");
                    if (BCrypt.checkpw(new String(pass), hashedPassword)) {
                        UserSession.setSession(res.getInt("userID"), res.getString("role"));
                        CardLayout cl = (CardLayout) main.mainPanel.getLayout();
                        if ("Complete".equalsIgnoreCase(res.getString("status"))) {
                            JOptionPane.showMessageDialog(this, "Login Successful!");
                            main.updateButtonVisibility();
                    
                            main.showHeader(true);
                            cl.show(main.mainPanel, "home");
                        } else {
                            JOptionPane.showMessageDialog(this, "Login Successful but Complete your Profile First!");
                            main.studentForm.updateDetails();
                            cl.show(main.mainPanel, "studentForm");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "Invalid password!",
                                "Login Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this,
                            "No User found in " + selectedRole,
                            "User Not Found",
                            JOptionPane.ERROR_MESSAGE);
                }

                pst.close();
                conn.close();

            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Error in Login: " + e.getMessage());
                JOptionPane.showMessageDialog(this,
                        "Registration UnSuccessful: " + e.getMessage(),
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    result,
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }


    }//GEN-LAST:event_signinButton1ActionPerformed

    private void studentButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentButton1ActionPerformed
        studentButton1.setBackground(new Color(66, 133, 244));
        studentButton1.setForeground(new java.awt.Color(255, 255, 255));

        facultyButton1.setBackground(new Color(242, 242, 242));
        facultyButton1.setForeground(new java.awt.Color(0, 0, 0));

        adminButton1.setBackground(new Color(242, 242, 242));
        adminButton1.setForeground(new java.awt.Color(0, 0, 0));
        selectedRole = "Student";
        signinButton1.setText("Sign in as Student");
    }//GEN-LAST:event_studentButton1ActionPerformed

    private void facultyButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facultyButton1ActionPerformed
        facultyButton1.setBackground(new Color(66, 133, 244));
        facultyButton1.setForeground(new java.awt.Color(255, 255, 255));

        studentButton1.setBackground(new Color(242, 242, 242));
        studentButton1.setForeground(new java.awt.Color(0, 0, 0));

        adminButton1.setBackground(new Color(242, 242, 242));
        adminButton1.setForeground(new java.awt.Color(0, 0, 0));

        selectedRole = "Faculty";
        signinButton1.setText("Sign in as Faculty");
    }//GEN-LAST:event_facultyButton1ActionPerformed

    private void adminButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminButton1ActionPerformed
        adminButton1.setBackground(new Color(66, 133, 244));
        adminButton1.setForeground(new java.awt.Color(255, 255, 255));

        facultyButton1.setBackground(new Color(242, 242, 242));
        facultyButton1.setForeground(new java.awt.Color(0, 0, 0));

        studentButton1.setBackground(new Color(242, 242, 242));
        studentButton1.setForeground(new java.awt.Color(0, 0, 0));

        selectedRole = "Admin";
        signinButton1.setText("Sign in as Admin");
    }//GEN-LAST:event_adminButton1ActionPerformed

    private void registerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerBtnActionPerformed
        CardLayout cl = (CardLayout) main.mainPanel.getLayout();
        cl.show(main.mainPanel, "register");
    }//GEN-LAST:event_registerBtnActionPerformed

    private void homeRedirectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeRedirectBtnActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) main.mainPanel.getLayout();
        cl.show(main.mainPanel, "home");
        main.showHeader(true);

    }//GEN-LAST:event_homeRedirectBtnActionPerformed

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adminButton1;
    private javax.swing.JTextField email;
    private javax.swing.JButton facultyButton1;
    private javax.swing.JButton homeRedirectBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPasswordField password;
    private javax.swing.JButton registerBtn;
    private javax.swing.JButton signinButton1;
    private javax.swing.JButton studentButton1;
    // End of variables declaration//GEN-END:variables
}
