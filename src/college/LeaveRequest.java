package college;

import collegemanagement.DBConnection;
import java.awt.CardLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
/**
 *
 * @author Hp
 */
public class LeaveRequest extends javax.swing.JPanel {

    MainFrame main;

    public LeaveRequest(MainFrame main) {
        initComponents();
        this.main = main;
    }

    public final void updateButtonVisibility() {
        if (UserSession.isLoggedIn()) {
            System.out.println("Runinafdasdfasd");
            uniqueNo.setText(UserSession.getRollNumber());
            fullName.setText(UserSession.getUsername());
            email.setText(UserSession.getEmail());
            course.setText(UserSession.getCourseName());

            if ("student".equalsIgnoreCase(UserSession.getUserRole())) {

            } else {
                course.setVisible(false);
                courseLabel.setVisible(false);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        leaveSelect = new javax.swing.ButtonGroup();
        sendApproval = new javax.swing.JButton();
        heading = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        requestNo = new javax.swing.JLabel();
        courseLabel = new javax.swing.JLabel();
        course = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        uniqueNo = new javax.swing.JLabel();
        uniqueNoField = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        fullName = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        Duty = new javax.swing.JRadioButton();
        Medical = new javax.swing.JRadioButton();
        Casual = new javax.swing.JRadioButton();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        leaveReason = new javax.swing.JTextArea();
        leaveStartDate = new com.toedter.calendar.JDateChooser();
        jLabel30 = new javax.swing.JLabel();
        leaveEndDate = new com.toedter.calendar.JDateChooser();
        jLabel31 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        uniqueNo3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(248, 251, 255));
        setPreferredSize(new java.awt.Dimension(1170, 800));

        sendApproval.setBackground(new java.awt.Color(0, 102, 102));
        sendApproval.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        sendApproval.setForeground(new java.awt.Color(255, 255, 255));
        sendApproval.setText("SEND FOR APPROVAL");
        sendApproval.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendApprovalActionPerformed(evt);
            }
        });

        heading.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        heading.setText("Leave Application Form");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Request Number");

        requestNo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        requestNo.setText("-");
        requestNo.setPreferredSize(new java.awt.Dimension(120, 20));

        courseLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        courseLabel.setText("Course");

        course.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        course.setText("MCA");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setText("Full Name");

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel38.setText("Email ID:");

        uniqueNo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uniqueNo.setText("adsf");

        uniqueNoField.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        uniqueNoField.setText("Roll Number");

        email.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        email.setText("asfd");

        fullName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        fullName.setText("adfas");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel28.setText("Type of Leave");

        leaveSelect.add(Duty);
        Duty.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        Duty.setText("Duty");

        leaveSelect.add(Medical);
        Medical.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        Medical.setText("Medical ");

        leaveSelect.add(Casual);
        Casual.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        Casual.setText("Casual");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel29.setText("Details / Reason for the Leave");

        leaveReason.setColumns(20);
        leaveReason.setRows(5);
        jScrollPane1.setViewportView(leaveReason);

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel30.setText("Date from:");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setText("To");

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel39.setText("Remaining Leaves");

        uniqueNo3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uniqueNo3.setText("10");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(heading, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(uniqueNoField, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(leaveStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(74, 74, 74)
                                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(leaveEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(Medical)
                                                .addGap(18, 18, 18)
                                                .addComponent(Duty)
                                                .addGap(18, 18, 18)
                                                .addComponent(Casual)
                                                .addGap(130, 130, 130))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(fullName, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(171, 171, 171)
                                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(uniqueNo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(171, 171, 171)
                                        .addComponent(jLabel39)
                                        .addGap(46, 46, 46)
                                        .addComponent(uniqueNo3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(21, 21, 21)
                                .addComponent(requestNo, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(119, 119, 119)
                                .addComponent(courseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(course, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1191, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(400, 538, Short.MAX_VALUE)
                    .addComponent(sendApproval, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 560, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(heading, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5)
                    .addComponent(requestNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(courseLabel)
                    .addComponent(course))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(uniqueNo)
                    .addComponent(uniqueNoField)
                    .addComponent(jLabel39)
                    .addComponent(uniqueNo3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel38)
                    .addComponent(jLabel27)
                    .addComponent(email)
                    .addComponent(fullName))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel28)
                    .addComponent(Medical)
                    .addComponent(Duty)
                    .addComponent(Casual))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(leaveStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(leaveEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addGap(65, 65, 65)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(262, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 723, Short.MAX_VALUE)
                    .addComponent(sendApproval, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 37, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void sendApprovalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendApprovalActionPerformed
//sendApproval
        Date startDate = leaveStartDate.getDate();
        Date lastDate = leaveEndDate.getDate();

        if (leaveSelect.equals("Hw")) {
            System.out.println("Hello World");
        }
        
        String selectedLeaveType = "";
        if (Medical.isSelected()) {
            selectedLeaveType = "Medical";
        } else if (Casual.isSelected()) {
            selectedLeaveType = "Casual";
        } else if (Duty.isSelected()) {
            selectedLeaveType = "Duty";
        }else{
            JOptionPane.showMessageDialog(this, "Please select a Leave Type!");
            return;
        }
        try {
            Connection conn = DBConnection.getConnection();

            String sql = "INSERT INTO leave_applications (userId, leave_type, date_from, date_to, reason) VALUES (?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);

            
            pst.setInt(1, UserSession.getUserID());
            pst.setString(2, selectedLeaveType);
            pst.setDate(3, new java.sql.Date(startDate.getTime()));
            pst.setDate(4, new java.sql.Date(lastDate.getTime()));
            pst.setString(5, leaveReason.getText());

            int status = pst.executeUpdate();

            if (status > 0) {
                System.out.println("Success");
                JOptionPane.showMessageDialog(this,
                        "Leave Approval request sended!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
                main.profile.fetchLeavesDetails(UserSession.getUserID());
                CardLayout cl = (CardLayout) main.mainPanel.getLayout();
                cl.show(main.mainPanel, "home");
            } else {
                System.out.println("Un-Success");
                JOptionPane.showMessageDialog(this,
                        "Leave Approval request Fail!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

            pst.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error in Leave Request: " + e.getMessage());

        }
    }//GEN-LAST:event_sendApprovalActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton Casual;
    private javax.swing.JRadioButton Duty;
    private javax.swing.JRadioButton Medical;
    private javax.swing.JLabel course;
    private javax.swing.JLabel courseLabel;
    private javax.swing.JLabel email;
    private javax.swing.JLabel fullName;
    private javax.swing.JLabel heading;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser leaveEndDate;
    private javax.swing.JTextArea leaveReason;
    private javax.swing.ButtonGroup leaveSelect;
    private com.toedter.calendar.JDateChooser leaveStartDate;
    private javax.swing.JLabel requestNo;
    private javax.swing.JButton sendApproval;
    private javax.swing.JLabel uniqueNo;
    private javax.swing.JLabel uniqueNo3;
    private javax.swing.JLabel uniqueNoField;
    // End of variables declaration//GEN-END:variables
}
