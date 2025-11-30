package college.Admin;

import college.MainFrame;
import collegemanagement.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public final class StudentsAdmin extends javax.swing.JPanel {

    MainFrame main;
    DefaultTableModel model;

    public StudentsAdmin(MainFrame main) {
        initComponents();
        this.main = main;
        model = (DefaultTableModel) studentTable.getModel();

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < studentTable.getColumnCount(); i++) {
            studentTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        fetchCourses();
    }

    public void fetchCourses() {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM courses";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            selectCourse.removeAllItems();

            while (rs.next()) {
                String courseName = rs.getString("description");
                String courseCode = rs.getString("courseCode");
                selectCourse.addItem(courseCode + " - " + courseName);
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
    }

    public void fetchStudents(String courseName) {
        try {
            Connection conn = DBConnection.getConnection();
            int count = 0;
            String sql
                    = "SELECT u.userId, u.userName, u.email, u.contact, c.courseName, u.status "
                    + "FROM users u "
                    + "JOIN students sc ON u.userId = sc.studentId "
                    + "JOIN courses c ON sc.courseId = c.courseId "
                    + "WHERE c.courseCode = ? AND u.role = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, courseName);
            ps.setString(2, "Student");
            
            ResultSet rs = ps.executeQuery();
            model.setRowCount(0);
            while (rs.next()) {
                int id = rs.getInt("userId");
                String userName = rs.getString("userName");
                String email = rs.getString("email");
                String contactNumber = rs.getString("contact");       
                String userStatus = rs.getString("status");
                model.addRow(new Object[]{++count, id, userName, email, courseName, contactNumber, userStatus});
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error in register: " + e.getMessage());
        }
    }
    
    public void fetchStudents(String courseName, String Name) {
        try {
            Connection conn = DBConnection.getConnection();
            int count = 0;
            String sql
                    = "SELECT u.userId, u.userName, u.email, u.contact, c.courseName "
                    + "FROM users u "
                    + "JOIN students sc ON u.userId = sc.studentId "
                    + "JOIN courses c ON sc.courseId = c.courseId "
                    + "WHERE c.courseCode = ? AND (u.userName LIKE ? OR u.userId LIKE ?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, courseName);
            ps.setString(2, "%" + Name + "%");
            ps.setString(3, "%" + Name + "%");
            
            ResultSet rs = ps.executeQuery();

            model.setRowCount(0);

            while (rs.next()) {
                int id = rs.getInt("userId");
                String userName = rs.getString("userName");
                String email = rs.getString("email");
                String contactNumber = rs.getString("contact");
                model.addRow(new Object[]{++count, id, userName, email, courseName, contactNumber});
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error in register: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        selectCourse = new javax.swing.JComboBox<>();
        searchText = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        searchTextBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        studentTable = new javax.swing.JTable();
        Savebtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(248, 251, 255));
        setPreferredSize(new java.awt.Dimension(1320, 800));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Students");

        selectCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectCourseActionPerformed(evt);
            }
        });
        selectCourse.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                selectCoursePropertyChange(evt);
            }
        });

        searchText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Select Cource");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel3.setText("SEARCH BY STUDENT ");

        searchTextBtn.setBackground(new java.awt.Color(0, 102, 102));
        searchTextBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        searchTextBtn.setForeground(new java.awt.Color(255, 255, 255));
        searchTextBtn.setText("SEARCH");
        searchTextBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextBtnActionPerformed(evt);
            }
        });

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

        studentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S. No.", "Roll No.", "Full Name", "Email", "Contact Number", "Course", "Profile Complete"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        studentTable.setRowHeight(27);
        jScrollPane2.setViewportView(studentTable);

        Savebtn.setBackground(new java.awt.Color(0, 102, 102));
        Savebtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Savebtn.setForeground(new java.awt.Color(255, 255, 255));
        Savebtn.setText("ADD NEW");
        Savebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SavebtnActionPerformed(evt);
            }
        });

        jLabel4.setText("ROLL NUMBER OR NAME");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchTextBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(selectCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(395, 395, 395)
                                .addComponent(Savebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1241, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(searchTextBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(selectCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(33, 33, 33)
                        .addComponent(Savebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(225, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void selectCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectCourseActionPerformed
        Object selected = selectCourse.getSelectedItem();
        if (selected != null) {
            String courseName = selected.toString().split("-")[0].trim();
            fetchStudents(courseName);
        }
//        SELECT u.userId, u.userName, u.email, u.contact, c.courseName
//        FROM users u
//        JOIN students sc ON u.userId = sc.studentId 
//        JOIN courses c ON sc.courseId = c.courseId
//        WHERE c.courseName = 'MCA';
    }//GEN-LAST:event_selectCourseActionPerformed

    private void selectCoursePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_selectCoursePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_selectCoursePropertyChange

    private void searchTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTextActionPerformed

    private void searchTextBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextBtnActionPerformed
        Object selected = selectCourse.getSelectedItem();
        if (selected != null) {
            String courseName = selected.toString().split("-")[0].trim();
            fetchStudents(courseName, searchText.getText());
        }
    }//GEN-LAST:event_searchTextBtnActionPerformed

    private void SavebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SavebtnActionPerformed
        AddStudentThroughAdmin addStudentThroughAdmin;
        addStudentThroughAdmin = new AddStudentThroughAdmin(null, true);
        addStudentThroughAdmin.setVisible(true);
    }//GEN-LAST:event_SavebtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Savebtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField searchText;
    private javax.swing.JButton searchTextBtn;
    private javax.swing.JComboBox<String> selectCourse;
    private javax.swing.JTable studentTable;
    // End of variables declaration//GEN-END:variables
}
