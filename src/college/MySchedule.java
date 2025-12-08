/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package college;

import TimeTable.TimetableCellRenderer;
import collegemanagement.DBConnection;
import java.awt.CardLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.mindrot.jbcrypt.BCrypt;

public class MySchedule extends javax.swing.JPanel {

    MainFrame main;
    DefaultTableModel model;

    /**
     * Creates new form MySchedule
     *
     * @param main
     */
    public MySchedule(MainFrame main) {
        initComponents();
        this.main = main;
        this.model = (DefaultTableModel) schedule.getModel();
        schedule.setShowVerticalLines(false);
        // --------------------------

        // Also good to remove spacing so merged cells touch perfectly
        schedule.setIntercellSpacing(new java.awt.Dimension(0, 0));
        TimetableCellRenderer renderer = new TimetableCellRenderer(new HashMap<>());
        for (int i = 0; i < schedule.getColumnCount(); i++) {
            schedule.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        javax.swing.table.JTableHeader header = schedule.getTableHeader();
        header.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        header.setBackground(new java.awt.Color(252, 233, 218));
        header.setReorderingAllowed(false);
        header.setPreferredSize(new java.awt.Dimension(header.getWidth(), 60));
        schedule.setRowHeight(50);
    }

    public void fetchSchedule() {
        model.setRowCount(0);
        try {
            Connection conn = DBConnection.getConnection();

            String sql = "select  u.userName, u.userId, ts.* from teacher_schedule ts "
                    + "JOIN teachers t on t.employeeId = ts.employeeId "
                    + "JOIN users u on u.userId = t.userId "
                    + "where u.userId = ? ";
//                    + "ORDER BY FIELD(day_name, 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday')";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, UserSession.getUserID());

            ResultSet res = pst.executeQuery();

            while (res.next()) {
                System.out.println("Hello " + res.getString("userName") + " "
                        + res.getInt("userId") + " "
                        + res.getString("employeeId") + " "
                        + res.getString("day_name") + " "
                        + res.getInt("slot_number") + " "
                        + res.getString("section"));
                model.addRow(new Object[]{
                    res.getString("day_name"),
                    res.getInt("slot_number"),
                    res.getString("section")
                });
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
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scheduleScroll = new javax.swing.JScrollPane();
        schedule = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        scheduleScroll.setBackground(new java.awt.Color(248, 251, 255));

        schedule.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Day/Time", "9:00 AM to 10:00 AM", "10:00 AM to 11:00 AM", "15 Min.", "11:15 AM to 12:15 PM", "12:15 PM to 01:15 PM", "45 Min.", "02:00 PM to 03:00 PM", "03:00 PM to 04:00 PM", "04:00 PM to 05:00 PM"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        schedule.setRowHeight(27);
        scheduleScroll.setViewportView(schedule);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("My Schedule");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel2)
                .addContainerGap(1137, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scheduleScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 1311, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(570, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(82, 82, 82)
                    .addComponent(scheduleScroll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(133, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTable schedule;
    private javax.swing.JScrollPane scheduleScroll;
    // End of variables declaration//GEN-END:variables
}
