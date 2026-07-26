package college.Admin;

import CollegeSync.DBConnection;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ManageLeaveCellRender extends DefaultCellEditor implements TableCellRenderer {

    protected JButton btn;
    private String label;
    private JTable table;
    private ManageLeaveRequests parentPanel; // Reference to refresh table after update

    public ManageLeaveCellRender(ManageLeaveRequests parent, JTable table) {
        super(new JCheckBox());
        this.parentPanel = parent;
        this.table = table;

        btn = new JButton();
        btn.setOpaque(true);
        btn.setBackground(new Color(0, 102, 102));
        btn.setForeground(Color.white);
        btn.addActionListener((ActionEvent e) -> {
            fireEditingStopped(); // Stop editing to capture the click
            showDecisionDialog();
        });
    }

    // --- RENDERER: Makes the cell look like a button ---
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        btn.setText("ACTION");
        return btn;
    }

    // --- EDITOR: Makes the button clickable ---
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        btn.setText("ACTION");
        return btn;
    }

    @Override
    public Object getCellEditorValue() {
        return "Action";
    }

    // --- LOGIC: The Dialog and DB Update ---
    private void showDecisionDialog() {
        int selectedRow = table.getSelectedRow();
        String request_number = table.getValueAt(selectedRow, 0).toString();
        String daysLeave = table.getValueAt(selectedRow, 4).toString();
        String reason = table.getValueAt(selectedRow, 2).toString();
//        System.out.println(idObj.toString());
//        String request_number = idObj.toString();

        Object[] options = {"Approve", "Reject", "Cancel"};
        int choice = JOptionPane.showOptionDialog(btn,
                "Leave Request ID: " + request_number + " \n Reason: " + reason + "\n Days Leave: " + daysLeave,
                "Process Leave Request",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, options, options[2]);

        if (choice == 0) {
            updateLeaveStatus(request_number, "Approved");
        } else if (choice == 1) {
            updateLeaveStatus(request_number, "Rejected");
        }
    }

    private void updateLeaveStatus(String id, String newStatus) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "UPDATE leave_applications SET status = ? WHERE request_number = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, newStatus);
            pst.setString(2, id);

            int rowAffected = pst.executeUpdate();
            if (rowAffected > 0) {
                JOptionPane.showMessageDialog(btn, "Request " + newStatus + " Successfully!");
                parentPanel.fetchLeaves();
            }
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(btn, "Error updating status: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
