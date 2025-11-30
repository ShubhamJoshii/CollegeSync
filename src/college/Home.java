package college;

import java.awt.CardLayout;

public class Home extends javax.swing.JPanel {

    MainFrame main;

    public Home(MainFrame main) {
        initComponents();
        this.main = main;
        updateButtonVisibility();
    }

    public final void updateButtonVisibility() {
        if (UserSession.isLoggedIn()) {
            buttonContainer.add(profileBtn);
//            buttonContainer.add(timetable);
            if ("faculty".equalsIgnoreCase(UserSession.getUserRole())) {
//                buttonContainer.add(studentBtn);
                buttonContainer.add(leaveRequestBtn);
                buttonContainer.add(studentBtn);
                buttonContainer.add(attendenceBtn);
                buttonContainer.add(manageBtn);
            } else if ("admin".equalsIgnoreCase(UserSession.getUserRole())) {
                buttonContainer.add(teachersBtn);
                buttonContainer.add(studentBtn);
                buttonContainer.add(attendenceBtn);
                buttonContainer.add(manageBtn);
                buttonContainer.add(manageLeaveRequest);
            } else {
                buttonContainer.add(leaveRequestBtn);
            }
        } else {
            buttonContainer.remove(profileBtn);
            buttonContainer.remove(manageLeaveRequest);
            buttonContainer.remove(teachersBtn);
            buttonContainer.remove(studentBtn);
            buttonContainer.remove(attendenceBtn);
            buttonContainer.remove(leaveRequestBtn);
            buttonContainer.remove(manageBtn);
//          buttonContainer.remove(timetable);

        }
        buttonContainer.revalidate();
        buttonContainer.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonContainer = new javax.swing.JPanel();
        profileBtn = new javax.swing.JButton();
        announcement = new javax.swing.JButton();
        timetable = new javax.swing.JButton();
        courses = new javax.swing.JButton();
        attendenceBtn = new javax.swing.JButton();
        leaveRequestBtn = new javax.swing.JButton();
        feesBtn = new javax.swing.JButton();
        studentBtn = new javax.swing.JButton();
        teachersBtn = new javax.swing.JButton();
        manageBtn = new javax.swing.JButton();
        manageLeaveRequest = new javax.swing.JButton();

        setBackground(new java.awt.Color(248, 251, 255));
        setMinimumSize(new java.awt.Dimension(1400, 800));
        setPreferredSize(new java.awt.Dimension(1400, 800));
        setLayout(null);

        buttonContainer.setOpaque(false);
        buttonContainer.setLayout(new java.awt.GridLayout(2, 0, 20, 20));

        profileBtn.setBackground(new java.awt.Color(0, 153, 204));
        profileBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        profileBtn.setForeground(new java.awt.Color(255, 255, 255));
        profileBtn.setText("PROFILE");
        profileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileBtnActionPerformed(evt);
            }
        });
        buttonContainer.add(profileBtn);

        announcement.setBackground(new java.awt.Color(0, 153, 204));
        announcement.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        announcement.setForeground(new java.awt.Color(255, 255, 255));
        announcement.setText("ANNOUNCEMENT");
        announcement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                announcementActionPerformed(evt);
            }
        });
        buttonContainer.add(announcement);

        timetable.setBackground(new java.awt.Color(0, 153, 204));
        timetable.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        timetable.setForeground(new java.awt.Color(255, 255, 255));
        timetable.setText("TIMETABLE");
        timetable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timetableActionPerformed(evt);
            }
        });
        buttonContainer.add(timetable);

        courses.setBackground(new java.awt.Color(0, 153, 204));
        courses.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        courses.setForeground(new java.awt.Color(255, 255, 255));
        courses.setText("COURSES");
        courses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coursesActionPerformed(evt);
            }
        });
        buttonContainer.add(courses);

        attendenceBtn.setBackground(new java.awt.Color(0, 153, 204));
        attendenceBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        attendenceBtn.setForeground(new java.awt.Color(255, 255, 255));
        attendenceBtn.setText("ATTENDENCE");
        attendenceBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attendenceBtnActionPerformed(evt);
            }
        });
        buttonContainer.add(attendenceBtn);

        leaveRequestBtn.setBackground(new java.awt.Color(0, 153, 204));
        leaveRequestBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        leaveRequestBtn.setForeground(new java.awt.Color(255, 255, 255));
        leaveRequestBtn.setText("LEAVE REQUEST");
        leaveRequestBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaveRequestBtnActionPerformed(evt);
            }
        });
        buttonContainer.add(leaveRequestBtn);

        feesBtn.setBackground(new java.awt.Color(0, 153, 204));
        feesBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        feesBtn.setForeground(new java.awt.Color(255, 255, 255));
        feesBtn.setText("FEES");
        feesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                feesBtnActionPerformed(evt);
            }
        });
        buttonContainer.add(feesBtn);

        studentBtn.setBackground(new java.awt.Color(0, 153, 204));
        studentBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        studentBtn.setForeground(new java.awt.Color(255, 255, 255));
        studentBtn.setText("STUDENTS");
        studentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentBtnActionPerformed(evt);
            }
        });
        buttonContainer.add(studentBtn);

        teachersBtn.setBackground(new java.awt.Color(0, 153, 204));
        teachersBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        teachersBtn.setForeground(new java.awt.Color(255, 255, 255));
        teachersBtn.setText("TEACHERS");
        teachersBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teachersBtnActionPerformed(evt);
            }
        });
        buttonContainer.add(teachersBtn);

        manageBtn.setBackground(new java.awt.Color(0, 153, 204));
        manageBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        manageBtn.setForeground(new java.awt.Color(255, 255, 255));
        manageBtn.setText("MANAGE");
        manageBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageBtnActionPerformed(evt);
            }
        });
        buttonContainer.add(manageBtn);

        manageLeaveRequest.setBackground(new java.awt.Color(0, 153, 204));
        manageLeaveRequest.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        manageLeaveRequest.setForeground(new java.awt.Color(255, 255, 255));
        manageLeaveRequest.setText("MANAGE LEAVE REQUESTS");
        manageLeaveRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageLeaveRequestActionPerformed(evt);
            }
        });
        buttonContainer.add(manageLeaveRequest);

        add(buttonContainer);
        buttonContainer.setBounds(50, 110, 1260, 260);
    }// </editor-fold>//GEN-END:initComponents

    private void studentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentBtnActionPerformed
        CardLayout cl = (CardLayout) main.mainPanel.getLayout();
        cl.show(main.mainPanel, "students");
    }//GEN-LAST:event_studentBtnActionPerformed

    private void announcementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_announcementActionPerformed
        CardLayout cl = (CardLayout) main.mainPanel.getLayout();
        cl.show(main.mainPanel, "announcement");
    }//GEN-LAST:event_announcementActionPerformed

    private void timetableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timetableActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) main.mainPanel.getLayout();
        if ("student".equalsIgnoreCase(UserSession.getUserRole())) {
            cl.show(main.mainPanel, "timetableStudent");
        } else {
            cl.show(main.mainPanel, "timetable");
        }
    }//GEN-LAST:event_timetableActionPerformed

    private void coursesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coursesActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) main.mainPanel.getLayout();
        cl.show(main.mainPanel, "course");
    }//GEN-LAST:event_coursesActionPerformed

    private void attendenceBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attendenceBtnActionPerformed
        CardLayout cl = (CardLayout) main.mainPanel.getLayout();
        cl.show(main.mainPanel, "attendence");
    }//GEN-LAST:event_attendenceBtnActionPerformed

    private void leaveRequestBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaveRequestBtnActionPerformed
        CardLayout cl = (CardLayout) main.mainPanel.getLayout();
        cl.show(main.mainPanel, "leaverequest");
    }//GEN-LAST:event_leaveRequestBtnActionPerformed

    private void feesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_feesBtnActionPerformed
        CardLayout cl = (CardLayout) main.mainPanel.getLayout();
        cl.show(main.mainPanel, "fees");
    }//GEN-LAST:event_feesBtnActionPerformed

    private void manageBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageBtnActionPerformed
        CardLayout cl = (CardLayout) main.mainPanel.getLayout();
        cl.show(main.mainPanel, "manage");
    }//GEN-LAST:event_manageBtnActionPerformed

    private void profileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileBtnActionPerformed
        CardLayout cl = (CardLayout) main.mainPanel.getLayout();
        cl.show(main.mainPanel, "profile");
    }//GEN-LAST:event_profileBtnActionPerformed

    private void teachersBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teachersBtnActionPerformed
        CardLayout cl = (CardLayout) main.mainPanel.getLayout();
        cl.show(main.mainPanel, "teachers");
    }//GEN-LAST:event_teachersBtnActionPerformed

    private void manageLeaveRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageLeaveRequestActionPerformed
        CardLayout cl = (CardLayout) main.mainPanel.getLayout();
        cl.show(main.mainPanel, "manageleaverequest");
    }//GEN-LAST:event_manageLeaveRequestActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton announcement;
    private javax.swing.JButton attendenceBtn;
    private javax.swing.JPanel buttonContainer;
    private javax.swing.JButton courses;
    private javax.swing.JButton feesBtn;
    private javax.swing.JButton leaveRequestBtn;
    private javax.swing.JButton manageBtn;
    private javax.swing.JButton manageLeaveRequest;
    private javax.swing.JButton profileBtn;
    private javax.swing.JButton studentBtn;
    private javax.swing.JButton teachersBtn;
    private javax.swing.JButton timetable;
    // End of variables declaration//GEN-END:variables
}
