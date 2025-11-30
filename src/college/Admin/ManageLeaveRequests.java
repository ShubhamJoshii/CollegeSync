package college.Admin;

import college.*;
import collegemanagement.DBConnection;
import java.awt.CardLayout;
import java.util.Date;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ManageLeaveRequests extends javax.swing.JPanel {

    MainFrame main;
    DefaultTableModel model;

    public ManageLeaveRequests(MainFrame main) {
        initComponents();
        this.main = main;
        model = (DefaultTableModel) leaveRequestTable.getModel();

        leaveRequestTable.setRowHeight(30);
        leaveRequestTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < leaveRequestTable.getColumnCount(); i++) {
            leaveRequestTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        ManageLeaveCellRender actionEditor = new ManageLeaveCellRender(this, leaveRequestTable);
        leaveRequestTable.getColumnModel().getColumn(8).setCellRenderer(actionEditor);
        leaveRequestTable.getColumnModel().getColumn(8).setCellEditor(actionEditor);
        setTableWidths();
    }

    private void setTableWidths() {
        leaveRequestTable.getColumnModel().getColumn(0).setPreferredWidth(60);
        leaveRequestTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        leaveRequestTable.getColumnModel().getColumn(2).setPreferredWidth(250);
        leaveRequestTable.getColumnModel().getColumn(5).setPreferredWidth(150);
        leaveRequestTable.getColumnModel().getColumn(7).setPreferredWidth(80);
        leaveRequestTable.getColumnModel().getColumn(8).setPreferredWidth(200);

    }

    public final void fetchLeaves() {
        try {
            Connection conn = DBConnection.getConnection();
            String sql, leave_type, reason, startDate, lastDate, status, userName;
            int count = 0, total_leaves_allotted, request_number;
            sql = "SELECT u.userName, ls.* FROM leave_applications ls "
                    + "JOIN users u ON u.userId = ls.userId "
                    + "ORDER BY ls.date_from DESC; ";

            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet res = pst.executeQuery();

            model.setRowCount(0);

            while (res.next()) {
                request_number = res.getInt("request_number");
                leave_type = res.getString("leave_type");
                total_leaves_allotted = res.getInt("total_leaves_allotted");
                userName = res.getString("userName");
                reason = res.getString("reason");
                startDate = res.getString("date_from");
                lastDate = res.getString("date_to");
                status = res.getString("status");
                System.out.println(startDate + " to " + lastDate);
                LocalDate startObj = LocalDate.parse(startDate);
                LocalDate endObj = LocalDate.parse(lastDate);

                // 3. Calculate the days (inclusive)
                long daysCount = ChronoUnit.DAYS.between(startObj, endObj) + 1;

                model.addRow(new Object[]{request_number, userName, reason, total_leaves_allotted, daysCount, startDate + " - " + lastDate, leave_type, status, request_number});
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error in register User Session: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        leaveSelect = new javax.swing.ButtonGroup();
        heading = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        leaveRequestTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(248, 251, 255));
        setPreferredSize(new java.awt.Dimension(1170, 800));

        heading.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        heading.setText("Leave Application Form");

        jScrollPane1.setOpaque(false);

        leaveRequestTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Request Number", "Name", "Reason", "Remaining Leaves", "Required Leaves", "Duration", "Leave Type", "Status", "Update Status"
            }
        ));
        leaveRequestTable.setOpaque(false);
        jScrollPane1.setViewportView(leaveRequestTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(heading, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1225, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(heading, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
                .addGap(130, 130, 130))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel heading;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable leaveRequestTable;
    private javax.swing.ButtonGroup leaveSelect;
    // End of variables declaration//GEN-END:variables
}
