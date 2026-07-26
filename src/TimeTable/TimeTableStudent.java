package TimeTable;

import college.MainFrame;
import college.UserSession;
import java.util.*;
import java.util.stream.Collectors;

import CollegeSync.DBConnection;
import CollegeSync.Subject;
import CollegeSync.TeacherInfo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public final class TimeTableStudent extends javax.swing.JPanel {

    MainFrame main;
    DefaultTableModel model;

    public TimeTableStudent(MainFrame main) {
        initComponents();
        this.main = main;
        this.model = (DefaultTableModel) timeTable.getModel();

        timeTable.setShowVerticalLines(false);
        // --------------------------

        // Also good to remove spacing so merged cells touch perfectly
        timeTable.setIntercellSpacing(new java.awt.Dimension(0, 0));
        TimetableCellRenderer renderer = new TimetableCellRenderer(new HashMap<>());
        for (int i = 0; i < timeTable.getColumnCount(); i++) {
            timeTable.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        javax.swing.table.JTableHeader header = timeTable.getTableHeader();
        header.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        header.setBackground(new java.awt.Color(252, 233, 218));
        header.setReorderingAllowed(false);
        header.setPreferredSize(new java.awt.Dimension(header.getWidth(), 60));
        timeTable.setRowHeight(50);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        timeTable = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        coursesPanel = new javax.swing.JPanel();
        teachersPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        course = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        semester = new javax.swing.JLabel();

        setBackground(new java.awt.Color(248, 251, 255));
        setPreferredSize(new java.awt.Dimension(1300, 800));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Student Time Table");

        jScrollPane2.setBackground(new java.awt.Color(248, 251, 255));

        timeTable.setModel(new javax.swing.table.DefaultTableModel(
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
        timeTable.setRowHeight(27);
        jScrollPane2.setViewportView(timeTable);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Teachers");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Courses");

        coursesPanel.setBackground(new java.awt.Color(248, 251, 255));
        coursesPanel.setOpaque(false);
        coursesPanel.setLayout(new javax.swing.BoxLayout(coursesPanel, javax.swing.BoxLayout.LINE_AXIS));

        teachersPanel.setBackground(new java.awt.Color(248, 251, 255));
        teachersPanel.setOpaque(false);
        teachersPanel.setLayout(new javax.swing.BoxLayout(teachersPanel, javax.swing.BoxLayout.LINE_AXIS));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Course");

        course.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        course.setText("jLabel6");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Select Semster");

        semester.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        semester.setText("jLabel6");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 700, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(395, 395, 395))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(semester))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(course, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(coursesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(teachersPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(course)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(semester)
                            .addComponent(jLabel3))))
                .addGap(47, 47, 47)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(teachersPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                        .addGap(26, 26, 26))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(coursesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(53, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    public final void updateButtonVisibility() {
        semester.setText("" + UserSession.getSemester());
        course.setText(UserSession.getCourseName());
        fetchSubjectCourse();

    }

    private String getInitials(String name) {
        return java.util.Arrays.stream(name.split("\\s+"))
                .map(s -> String.valueOf(s.charAt(0)))
                .collect(java.util.stream.Collectors.joining())
                .toUpperCase();
    }

    public static String selectSubject(List<Subject> subjectList, int totalHour) {
        if (subjectList.isEmpty()) {
            return "Free";
        }

        Subject best = subjectList.get(0);
        for (Subject sub : subjectList) {
            if (sub.remainingClasses > 0 && sub.takenClasses < best.takenClasses) {
                best = sub;
            }
        }

        boolean wasFull = best.remainingClasses <= 0;
        best.updateRemainingClasses(totalHour);

        return wasFull ? best.shortName + " (Extra)" : best.shortName;
    }

    public String subjectClass(List<Subject> subjectList, String slotType) {
        switch (slotType) {
            case "normal" -> {
                List<Subject> classSubjects = subjectList.stream()
                        .filter(s -> s.classType.equalsIgnoreCase("class") && s.remainingClasses > 0)
                        .collect(Collectors.toList());
                if (classSubjects.isEmpty()) {
                    return "Free";
                }
                Collections.shuffle(classSubjects);
                return selectSubject(classSubjects, 1);
            }
            case "lab" -> {
                List<Subject> labSubjects = subjectList.stream()
                        .filter(s -> s.classType.equalsIgnoreCase("lab") && s.remainingClasses > 0)
                        .collect(Collectors.toList());
                if (labSubjects.isEmpty()) {
                    List<Subject> classSubjects = subjectList.stream()
                            .filter(s -> s.classType.equalsIgnoreCase("class") && s.remainingClasses > 0)
                            .collect(Collectors.toList());
                    if (classSubjects.isEmpty()) {
                        return "Free";
                    } else {
                        Collections.shuffle(classSubjects);
                        return selectSubject(classSubjects, 1);
                    }
//                    return "Free";
                }
                Collections.shuffle(labSubjects);
                return selectSubject(labSubjects, 2);
            }
            case "break" -> {
                return slotType;
            }
            default -> {
            }
        }
        return "";
    }

    public void fetchSubjectCourse() {
//        String courseName = course.getText();
        String subjectName, subjectCode, userName, classType, shortName;
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        List<Subject> subjectList = new ArrayList<Subject>();
        List<TeacherInfo> teachersList = new ArrayList<>();
        Map<String, Color> colorMap = new HashMap<>();

        List<String> condition = new ArrayList<String>();
        int count = 0;
        int week_total_classes, week_taken_classes;

        Object[][] data = new Object[days.length][10];

        for (int i = 0; i < days.length; i++) {
            data[i][0] = days[i];
            if (i == 6) {
                for (int j = 1; j < 10; j++) {
                    data[i][j] = "H O L I D A Y";
                }
            } else {
                data[i][3] = "T E A   B R E A K";
                data[i][6] = "L U N C H   B R E A K";
            }
        }
        String slotType, sql;
        int courseId = 0;
        try {
            model.setRowCount(0);
            Connection conn;
            PreparedStatement pst;
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            sql = "SELECT * from courses where courseName = ? ;";
            PreparedStatement pstGetId = conn.prepareStatement(sql);
            pstGetId.setString(1, UserSession.getCourseName());
            ResultSet rs = pstGetId.executeQuery();

            if (rs.next()) {
                courseId = rs.getInt("courseId");
            }

            sql = "SELECT s.subjectName, s.subjectCode, s.type, u.userName, s.shortName, scr.week_total_classes, scr.week_taken_classes "
                    + "FROM course_subjects s "
                    + "JOIN teachers t ON t.employeeId = s.teachesBy "
                    + "JOIN courses c ON c.courseId = s.courseId "
                    + "JOIN users u ON u.userId = t.userId "
                    + "JOIN subjectclassrecord scr ON scr.subjectId = s.subjectId "
                    + "WHERE c.courseId = ?  AND s.semester = ?;";

            pst = conn.prepareStatement(sql);

            pst.setInt(1, courseId);
            pst.setInt(2, UserSession.getSemester());

            try (ResultSet res = pst.executeQuery()) {
                boolean hasResults = false;
                while (res.next()) {
                    hasResults = true;

                    subjectName = res.getString("subjectName");
                    subjectCode = res.getString("subjectCode");
                    userName = res.getString("userName");
                    classType = res.getString("type");
                    shortName = res.getString("shortName");
                    week_total_classes = res.getInt("week_total_classes");
                    week_taken_classes = res.getInt("week_taken_classes");

                    Color specificColor = getSubjectColor(count);
                    colorMap.put(shortName.toUpperCase(), specificColor);
                    subjectList.add(new Subject(subjectName, shortName.toUpperCase(), classType.toLowerCase(), week_total_classes, getSubjectColor(count)));
                    if (classType.equalsIgnoreCase("class")) {
                        teachersList.add(new TeacherInfo(userName, getInitials(userName), getSubjectColor(count)));
                    }
                    count++;
                }
            }

            sql = "SELECT * FROM timetable_schedule "
                    + "WHERE courseId = ? AND semester = ? AND section = ? "
                    + "ORDER BY FIELD(day_name, 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday')";

            pst = conn.prepareStatement(sql);
            pst.setInt(1, courseId);
            pst.setInt(2, UserSession.getSemester());
            pst.setString(3, "A");
            int a = 0;
            try (ResultSet res = pst.executeQuery()) {
                boolean hasResults = false;
                while (res.next()) {
                    hasResults = true;
                    for (int j = 1; j < data[a].length - 1; j++) {
                        data[a][j] = res.getString("slot_" + j);
                    }
                    a++;
                }
            }
            pst.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error in Time Table: " + e.getMessage());
        }

        for (Object[] data1 : data) {
            model.addRow(data1);
        }

        updateCourseLegend(subjectList);
        updateTeachersLegend(teachersList);

        TimetableCellRenderer renderer = new TimetableCellRenderer(colorMap);

        for (int i = 0; i < timeTable.getColumnCount(); i++) {
            timeTable.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        // Force update
        timeTable.repaint();
    }

    private void updateTeachersLegend(List<TeacherInfo> teachersList) {
        teachersPanel.removeAll();
        teachersPanel.setLayout(new javax.swing.BoxLayout(teachersPanel, javax.swing.BoxLayout.Y_AXIS));

        for (TeacherInfo t : teachersList) {
            JPanel row = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0)); // reduced gaps
            row.setBackground(Color.WHITE);

            // IMPORTANT: Fix alignment for BoxLayout
            row.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

            // Use preferred size, not just maximum
            row.setPreferredSize(new Dimension(400, 30));
            row.setMaximumSize(new Dimension(1000, 30));

            javax.swing.JLabel lblAbbr = new javax.swing.JLabel(t.shortName);
            lblAbbr.setFont(new Font("Segoe UI", Font.BOLD, 12));
            lblAbbr.setForeground(t.color);
            // Remove setPreferredSize on label to let it grow naturally, or keep small
            lblAbbr.setPreferredSize(new Dimension(50, 20));

            javax.swing.JLabel lblSep = new javax.swing.JLabel(" : ");
            lblSep.setForeground(Color.GRAY);

            javax.swing.JLabel lblName = new javax.swing.JLabel(t.Name);
            lblName.setFont(new Font("Segoe UI", Font.BOLD, 12));
            lblName.setForeground(t.color);

            row.setOpaque(false);
            row.add(lblAbbr);
            row.add(lblSep);
            row.add(lblName);

            teachersPanel.add(row);
        }

        // Refresh the container logic
        teachersPanel.revalidate();
        teachersPanel.repaint();
    }

    private void updateCourseLegend(List<Subject> subjects) {
        coursesPanel.removeAll();
        coursesPanel.setLayout(new javax.swing.BoxLayout(coursesPanel, javax.swing.BoxLayout.Y_AXIS));

        for (Subject s : subjects) {
            if (s.classType.equalsIgnoreCase("class")) {
                JPanel row = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));
                row.setBackground(Color.WHITE);

                // IMPORTANT: Fix alignment
                row.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

                row.setPreferredSize(new Dimension(400, 30));
                row.setMaximumSize(new Dimension(1000, 30));

                javax.swing.JLabel lblAbbr = new javax.swing.JLabel(s.shortName);
                lblAbbr.setFont(new Font("Segoe UI", Font.BOLD, 12));
                lblAbbr.setForeground(s.color);
                lblAbbr.setPreferredSize(new Dimension(80, 20)); // Adjusted width

                javax.swing.JLabel lblSep = new javax.swing.JLabel(" : ");
                lblSep.setForeground(Color.GRAY);

                javax.swing.JLabel lblName = new javax.swing.JLabel(s.getS_name());
                lblName.setFont(new Font("Segoe UI", Font.BOLD, 12));
                lblName.setForeground(s.color);

                row.setOpaque(false);
                row.add(lblAbbr);
                row.add(lblSep);
                row.add(lblName);

                coursesPanel.add(row);
            }
        }
        coursesPanel.revalidate();
        coursesPanel.repaint();
    }

    private java.awt.Color getSubjectColor(int count) {
        return switch (count) {
            case 1 ->
                new java.awt.Color(218, 165, 32);  // Goldenrod
            case 2 ->
                new java.awt.Color(220, 20, 60);   // Crimson (Replaced Black)
            case 3 ->
                new java.awt.Color(60, 179, 113);  // Medium Sea Green
            case 4 ->
                new java.awt.Color(139, 69, 19);   // Saddle Brown
            case 5 ->
                new java.awt.Color(0, 0, 139);     // Dark Blue
            case 6 ->
                new java.awt.Color(178, 34, 34);   // Firebrick (Dark Red)
            case 7 ->
                new java.awt.Color(128, 0, 128);   // Purple
            case 8 ->
                new java.awt.Color(0, 128, 128);   // Teal
            case 9 ->
                new java.awt.Color(199, 21, 133);  // Medium Violet Red (Deep Pink)
            case 10 ->
                new java.awt.Color(255, 69, 0);   // Red-Orange
            case 11 ->
                new java.awt.Color(47, 79, 79);   // Dark Slate Gray
            case 12 ->
                new java.awt.Color(75, 0, 130);   // Indigo

            default ->
                java.awt.Color.DARK_GRAY;
        };
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel course;
    private javax.swing.JPanel coursesPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel semester;
    private javax.swing.JPanel teachersPanel;
    private javax.swing.JTable timeTable;
    // End of variables declaration//GEN-END:variables
}
