package college;

import CollegeSync.DBConnection;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

class StatusColorRenderer extends DefaultTableCellRenderer {

    // Add this constructor to Center the text
    public StatusColorRenderer() {
        setHorizontalAlignment(JLabel.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        String status = (String) value;

        c.setFont(c.getFont().deriveFont(Font.BOLD));

        // Reset color to black by default before checking status
        c.setForeground(Color.BLACK);

        if (status != null) {
            switch (status) {
                case "APPROVED" ->
                    c.setForeground(new Color(0, 153, 51)); // Dark Green
                case "REJECTED" ->
                    c.setForeground(Color.RED);
                case "PENDING" ->
                    c.setForeground(new Color(255, 153, 0)); // Dark Orange
                default ->
                    c.setForeground(Color.BLACK);
            }
        }

        if (isSelected) {
            c.setForeground(table.getSelectionForeground());
            c.setBackground(table.getSelectionBackground());
        }
        return c;
    }
}

public class Profile extends javax.swing.JPanel {

    MainFrame main;
    DefaultTableModel model;

    public Profile(MainFrame main) {
        initComponents();
        this.main = main;
        model = (DefaultTableModel) leaveRequestTable.getModel();

        leaveRequestTable.setRowHeight(23);
        leaveRequestTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < leaveRequestTable.getColumnCount(); i++) {
            leaveRequestTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        leaveRequestTable.getColumnModel().getColumn(4).setCellRenderer(new StatusColorRenderer());
    }

    public final void updateButtonVisibility() {
        if (UserSession.isLoggedIn()) {
            fetchLeavesDetails(UserSession.getUserID());
            details.setText(UserSession.getUserRole() + " Detail");
            rollNo.setText(UserSession.getRollNumber());
            applicationNo.setText(UserSession.getApplicationNumber());
            fullName.setText(UserSession.getUsername());
            email.setText(UserSession.getEmail());
            dob.setText(UserSession.getBirthDate());
            contactNo.setText(UserSession.getContactNumber());
            address.setText(UserSession.getAddress());
            course.setText(UserSession.getCourseName());
            if ("admin".equalsIgnoreCase(UserSession.getUserRole())) {
                detailsPanel.remove(personalDetails);
                rollNoField.setText("Registration Number");
                courseLabel.setVisible(false);
            } else {
                fatherName.setText(UserSession.getFatherName());
                motherName.setText(UserSession.getMotherName());
                fatherOccu.setText(UserSession.getFatherOccu());
                motherOccu.setText(UserSession.getMotherOccu());
                detailsPanel.add(personalDetails);
            }
        }
        detailsPanel.repaint();
        detailsPanel.revalidate();
    }

    public void fetchLeavesDetails(int userId) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql, leave_type, reason, startDate, lastDate, status;
            int count = 0;
            sql = "select la.* from leave_applications la "
                    + "where la.userId = ? "
                    + "ORDER BY la.date_from DESC;";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, "" + userId);
            ResultSet res = pst.executeQuery();

            model.setRowCount(0);

            while (res.next()) {
                leave_type = res.getString("leave_type");
                reason = res.getString("reason");
                startDate = res.getString("date_from");
                lastDate = res.getString("date_to");
                status = res.getString("status");
                System.out.println(startDate + " to " + lastDate);
                model.addRow(new Object[]{++count, leave_type, reason, startDate + " - " + lastDate, status.toUpperCase()});
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error in register User Session: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        applicationNo = new javax.swing.JLabel();
        rollNoField = new javax.swing.JLabel();
        rollNo = new javax.swing.JLabel();
        details = new javax.swing.JLabel();
        course = new javax.swing.JLabel();
        courseLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        photoDP = new java.awt.Panel();
        detailsPanel = new javax.swing.JPanel();
        generalDetails = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        fullName = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        dob = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        contactNo = new javax.swing.JLabel();
        personalDetails = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        fatherName = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        fatherOccu = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        motherName = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        motherOccu = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        address = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        details1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        leaveRequestTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(248, 251, 255));
        setPreferredSize(new java.awt.Dimension(1400, 572));

        applicationNo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        applicationNo.setText("MCA0445488");

        rollNoField.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rollNoField.setText("Roll Number");

        rollNo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rollNo.setText("MCA0445488");

        details.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        details.setText("Detail");

        course.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        course.setText("MCA");

        courseLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        courseLabel.setText("Course");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Application Number");

        photoDP.setBackground(new java.awt.Color(102, 0, 0));

        javax.swing.GroupLayout photoDPLayout = new javax.swing.GroupLayout(photoDP);
        photoDP.setLayout(photoDPLayout);
        photoDPLayout.setHorizontalGroup(
            photoDPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        photoDPLayout.setVerticalGroup(
            photoDPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 176, Short.MAX_VALUE)
        );

        detailsPanel.setOpaque(false);

        generalDetails.setOpaque(false);

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setText("Full Name");

        fullName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        fullName.setText("Shubham Joshi");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel28.setText("DOB");

        dob.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        dob.setText("18 FEB 2004");

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel36.setText("Email ID");

        email.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        email.setText("shubhamjoshi@gmail.com");

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel37.setText("Contact No.");

        contactNo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        contactNo.setText("9891971080");

        javax.swing.GroupLayout generalDetailsLayout = new javax.swing.GroupLayout(generalDetails);
        generalDetails.setLayout(generalDetailsLayout);
        generalDetailsLayout.setHorizontalGroup(
            generalDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(generalDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dob, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fullName, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(generalDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(generalDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(contactNo, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        generalDetailsLayout.setVerticalGroup(
            generalDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(generalDetailsLayout.createSequentialGroup()
                        .addGroup(generalDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(fullName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(generalDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(dob)))
                    .addGroup(generalDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(generalDetailsLayout.createSequentialGroup()
                            .addComponent(jLabel36)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel37))
                        .addGroup(generalDetailsLayout.createSequentialGroup()
                            .addComponent(email)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(contactNo))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        personalDetails.setOpaque(false);

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel29.setText("Father's Name");

        fatherName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        fatherName.setText("L D Joshi");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel30.setText("Occupation");

        fatherOccu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        fatherOccu.setText("Government Job");

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel38.setText("Mother's Name");

        motherName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        motherName.setText("Deepa Joshi");

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel39.setText("Occupation");

        motherOccu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        motherOccu.setText("House Wife");

        javax.swing.GroupLayout personalDetailsLayout = new javax.swing.GroupLayout(personalDetails);
        personalDetails.setLayout(personalDetailsLayout);
        personalDetailsLayout.setHorizontalGroup(
            personalDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(personalDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(personalDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(personalDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fatherOccu, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fatherName, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(personalDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(personalDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(motherOccu, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(motherName, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        personalDetailsLayout.setVerticalGroup(
            personalDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(personalDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(personalDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(personalDetailsLayout.createSequentialGroup()
                        .addGroup(personalDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(fatherName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(personalDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(fatherOccu)))
                    .addGroup(personalDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(personalDetailsLayout.createSequentialGroup()
                            .addComponent(jLabel38)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel39))
                        .addGroup(personalDetailsLayout.createSequentialGroup()
                            .addComponent(motherName)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(motherOccu))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setText("Address");

        address.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        address.setText("Sarojini Nager");

        javax.swing.GroupLayout detailsPanelLayout = new javax.swing.GroupLayout(detailsPanel);
        detailsPanel.setLayout(detailsPanelLayout);
        detailsPanelLayout.setHorizontalGroup(
            detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(generalDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(personalDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(detailsPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        detailsPanelLayout.setVerticalGroup(
            detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(generalDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(personalDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(address))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel1.setOpaque(false);

        details1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        details1.setText("Leave Requests");

        leaveRequestTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S. No.", "Leave Type", "Reason", "Duration", "Status"
            }
        ));
        jScrollPane1.setViewportView(leaveRequestTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(92, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(details1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1225, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(83, 83, 83))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(details1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(details, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(379, 379, 379))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addComponent(applicationNo)))
                        .addGap(53, 53, 53)
                        .addComponent(rollNoField, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rollNo, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(courseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(course, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(detailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(photoDP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(details, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(photoDP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(applicationNo)
                            .addComponent(rollNoField)
                            .addComponent(courseLabel)
                            .addComponent(course)
                            .addComponent(rollNo))
                        .addGap(18, 18, 18)
                        .addComponent(detailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel address;
    private javax.swing.JLabel applicationNo;
    private javax.swing.JLabel contactNo;
    private javax.swing.JLabel course;
    private javax.swing.JLabel courseLabel;
    private javax.swing.JLabel details;
    private javax.swing.JLabel details1;
    private javax.swing.JPanel detailsPanel;
    private javax.swing.JLabel dob;
    private javax.swing.JLabel email;
    private javax.swing.JLabel fatherName;
    private javax.swing.JLabel fatherOccu;
    private javax.swing.JLabel fullName;
    private javax.swing.JPanel generalDetails;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable leaveRequestTable;
    private javax.swing.JLabel motherName;
    private javax.swing.JLabel motherOccu;
    private javax.swing.JPanel personalDetails;
    private java.awt.Panel photoDP;
    private javax.swing.JLabel rollNo;
    private javax.swing.JLabel rollNoField;
    // End of variables declaration//GEN-END:variables
}
