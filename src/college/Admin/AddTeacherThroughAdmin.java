package college.Admin;

import CollegeSync.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.Statement;

public final class AddTeacherThroughAdmin extends javax.swing.JDialog {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AddTeacherThroughAdmin.class.getName());

    /**
     * Creates new form AddStudentThroughAdmin
     *
     * @param parent
     * @param modal
     */
    public AddTeacherThroughAdmin(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        fetchDepartments();
    }

    public void fetchDepartments() {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM departments";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            selectDepartment.removeAllItems();

            while (rs.next()) {
                String departmentId = rs.getString("departmentId");
                String departmentName = rs.getString("departmentName");
                selectDepartment.addItem(departmentId + " | " + departmentName);
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fullName = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        defaultPassword = new javax.swing.JTextField();
        saveBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        email = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        selectDepartment = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add New Teacher");
        setModal(true);
        setResizable(false);

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel27.setText("Full Name");

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel38.setText("Email ID");

        defaultPassword.setText("password");

        saveBtn.setBackground(new java.awt.Color(0, 102, 102));
        saveBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        saveBtn.setForeground(new java.awt.Color(255, 255, 255));
        saveBtn.setText("ADD");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        deleteBtn.setBackground(new java.awt.Color(204, 0, 51));
        deleteBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        deleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn.setText("CLOSE");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeAddUser(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel39.setText("Default Password");

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel40.setText("Department");

        selectDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectDepartmentActionPerformed(evt);
            }
        });
        selectDepartment.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                selectDepartmentPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addGap(18, 18, 18)
                        .addComponent(selectDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel38)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(email)
                            .addComponent(fullName, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel39)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(defaultPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(fullName, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel38)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(selectDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel39)
                    .addComponent(defaultPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(84, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        try {
            String hashedPassword = BCrypt.hashpw(defaultPassword.getText(), BCrypt.gensalt(12));
            Connection conn = DBConnection.getConnection();

            String sql = "INSERT INTO users (username, email, password, role) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, fullName.getText());     // username
            pst.setString(2, email.getText());        // email
            pst.setString(3, hashedPassword);         // hashed password
            pst.setString(4, "Faculty");              // role

            int status = pst.executeUpdate();

            if (status > 0) {

                // Retrieve generated key
                ResultSet rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    long adminId = rs.getLong(1);
//                    System.out.println("Inserted ID: " + adminId);
                    String departmentId = selectDepartment.getSelectedItem().toString().split("\\|")[0].trim();
//                    System.out.println(departmentId);
                    sql = "INSERT INTO teachers (userId, departmentId) VALUES (?, ?)";
           
                    pst = conn.prepareStatement(sql);
                    pst.setLong(1, adminId);
                    pst.setString(2, departmentId);
//
                    status = pst.executeUpdate();
                    if (status > 0) {
                        JOptionPane.showMessageDialog(this,
                                "New Faculty Added Successfully!",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        this.dispose();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "Failed to Add New Faculty!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

            pst.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error in Faculty register: " + e.getMessage());
            JOptionPane.showMessageDialog(this,
                    "Registration Unsuccessful: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

    }//GEN-LAST:event_saveBtnActionPerformed

    private void closeAddUser(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeAddUser
        this.dispose();
    }//GEN-LAST:event_closeAddUser

    private void selectDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectDepartmentActionPerformed

    }//GEN-LAST:event_selectDepartmentActionPerformed

    private void selectDepartmentPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_selectDepartmentPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_selectDepartmentPropertyChange

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                AddTeacherThroughAdmin dialog = new AddTeacherThroughAdmin(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField defaultPassword;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JTextField email;
    private javax.swing.JTextField fullName;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JButton saveBtn;
    private javax.swing.JComboBox<String> selectDepartment;
    // End of variables declaration//GEN-END:variables
}
