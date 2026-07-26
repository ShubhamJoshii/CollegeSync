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

//class TeacherInfo {
//
//    String shortName;
//    String Name;
//    java.awt.Color color;
//
//    public TeacherInfo(String Name, String shortName, java.awt.Color color) {
//        this.Name = Name;
//        this.shortName = shortName;
//        this.color = color;
//    }
//}

public final class TimeTableAdminTeacher extends javax.swing.JPanel {

    MainFrame main;
    DefaultTableModel model;

    public TimeTableAdminTeacher(MainFrame main) {
        initComponents();
        this.main = main;
        this.model = (DefaultTableModel) timeTable.getModel();

        timeTable.setShowVerticalLines(false);
        // --------------------------
        generateNewTimeTable.setVisible(true);
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

        fetchCourses();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        selectCourse = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        selectSemster = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        timetableContainer = new javax.swing.JPanel();
        noTimeTable = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        generateNewTimeTable = new javax.swing.JButton();
        timeTableFound = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        timeTable = new javax.swing.JTable();
        coursesPanel = new javax.swing.JPanel();
        teachersPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        jMenuItem1.setText("jMenuItem1");

        setBackground(new java.awt.Color(248, 251, 255));
        setPreferredSize(new java.awt.Dimension(1300, 800));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Time Table");

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

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Select Cource");

        selectSemster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectSemsterActionPerformed(evt);
            }
        });
        selectSemster.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                selectSemsterPropertyChange(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Select Semster");

        timetableContainer.setOpaque(false);

        noTimeTable.setBackground(new java.awt.Color(0, 153, 0));
        noTimeTable.setOpaque(false);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("No Time Table found, Contact Admin");

        generateNewTimeTable.setBackground(new java.awt.Color(0, 102, 102));
        generateNewTimeTable.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        generateNewTimeTable.setForeground(new java.awt.Color(255, 255, 255));
        generateNewTimeTable.setText("GENERATE NEW TIME TABLE");
        generateNewTimeTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateNewTimeTableActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout noTimeTableLayout = new javax.swing.GroupLayout(noTimeTable);
        noTimeTable.setLayout(noTimeTableLayout);
        noTimeTableLayout.setHorizontalGroup(
            noTimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, noTimeTableLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(generateNewTimeTable)
                .addGap(24, 24, 24))
            .addGroup(noTimeTableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        noTimeTableLayout.setVerticalGroup(
            noTimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(noTimeTableLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(generateNewTimeTable)
                .addContainerGap())
        );

        timeTableFound.setOpaque(false);

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

        coursesPanel.setBackground(new java.awt.Color(248, 251, 255));
        coursesPanel.setOpaque(false);
        coursesPanel.setLayout(new javax.swing.BoxLayout(coursesPanel, javax.swing.BoxLayout.LINE_AXIS));

        teachersPanel.setBackground(new java.awt.Color(248, 251, 255));
        teachersPanel.setOpaque(false);
        teachersPanel.setLayout(new javax.swing.BoxLayout(teachersPanel, javax.swing.BoxLayout.LINE_AXIS));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Courses");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Teachers");

        javax.swing.GroupLayout timeTableFoundLayout = new javax.swing.GroupLayout(timeTableFound);
        timeTableFound.setLayout(timeTableFoundLayout);
        timeTableFoundLayout.setHorizontalGroup(
            timeTableFoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timeTableFoundLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(timeTableFoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(timeTableFoundLayout.createSequentialGroup()
                        .addComponent(coursesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(teachersPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(timeTableFoundLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(334, 334, 334)))
                .addGap(56, 56, 56))
            .addGroup(timeTableFoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(timeTableFoundLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2)
                    .addContainerGap()))
        );
        timeTableFoundLayout.setVerticalGroup(
            timeTableFoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, timeTableFoundLayout.createSequentialGroup()
                .addContainerGap(446, Short.MAX_VALUE)
                .addGroup(timeTableFoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(timeTableFoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(coursesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(teachersPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
            .addGroup(timeTableFoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(timeTableFoundLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(201, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout timetableContainerLayout = new javax.swing.GroupLayout(timetableContainer);
        timetableContainer.setLayout(timetableContainerLayout);
        timetableContainerLayout.setHorizontalGroup(
            timetableContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(noTimeTable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(timetableContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(timeTableFound, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        timetableContainerLayout.setVerticalGroup(
            timetableContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, timetableContainerLayout.createSequentialGroup()
                .addComponent(noTimeTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(timeTableFound, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(432, 432, 432))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 431, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(selectCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(selectSemster, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(timetableContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(selectCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(selectSemster, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(timetableContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
    }// </editor-fold>//GEN-END:initComponents

    private String getInitials(String name) {
        return java.util.Arrays.stream(name.split("\\s+"))
                .map(s -> String.valueOf(s.charAt(0)))
                .collect(java.util.stream.Collectors.joining())
                .toUpperCase();
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

    public void fetchSemsters(String courseCode) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT c.semesters FROM courses c where courseCode = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, courseCode);
            ResultSet rs = ps.executeQuery();

            selectSemster.removeAllItems();

            while (rs.next()) {
                int semesters = rs.getInt("semesters");
                for (int i = 1; i <= semesters; i++) {
                    selectSemster.addItem("" + i);
                }
//                String courseCode = rs.getString("courseCode");
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
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

    public void fetchSubjectCourse(String courseCode, int courseSemester) {
        generateNewTimeTable.setVisible(false);
        if ("Admin".equalsIgnoreCase(UserSession.getUserRole())) {
            generateNewTimeTable.setVisible(true);
        }
        String subjectName, subjectCode, userName, classType, shortName;
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        List<Subject> subjectList = new ArrayList<Subject>();
        List<TeacherInfo> teachersList = new ArrayList<>();
        Map<String, Color> colorMap = new HashMap<>();
        List<String> condition = new ArrayList<String>();
        int count = 0, courseId = 0;
        int week_total_classes, week_taken_classes;
        boolean scheduleFound = false;

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
        try {
            model.setRowCount(0);
            Connection conn;
            PreparedStatement pst;
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            sql = "SELECT * from courses where courseCode = ? ;";
            PreparedStatement pstGetId = conn.prepareStatement(sql);
            pstGetId.setString(1, courseCode);
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
                    + "WHERE c.courseCode = ?  AND s.semester = ?;";

            pst = conn.prepareStatement(sql);

            pst.setString(1, courseCode);
            pst.setInt(2, courseSemester);

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
            pst.setInt(2, courseSemester);
            pst.setString(3, "A");
            int a = 0;
            try (ResultSet res = pst.executeQuery()) {
                boolean hasResults = false;
                while (res.next()) {
                    scheduleFound = true;
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

        if (scheduleFound) {
            noTimeTable.setVisible(false);
            timeTableFound.setVisible(true);
        } else {
            noTimeTable.setVisible(true);
            timeTableFound.setVisible(false);

//            model.setRowCount(0);
        }

        // Force update
        timetableContainer.revalidate();
        timetableContainer.repaint();
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

            // --- New Added Colors ---
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

    private void selectCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectCourseActionPerformed
        Object selected = selectCourse.getSelectedItem();
        if (selected != null) {
            String courseCode = selected.toString().split("-")[0].trim();
            fetchSemsters(courseCode);
        }
    }//GEN-LAST:event_selectCourseActionPerformed

    private void selectCoursePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_selectCoursePropertyChange

    }//GEN-LAST:event_selectCoursePropertyChange

    private void selectSemsterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectSemsterActionPerformed
        Object selected = selectSemster.getSelectedItem();
        if (selected != null) {
            String courseCode = selectCourse.getSelectedItem().toString().split("-")[0].trim();
            int courseSemester = Integer.parseInt(selected.toString().split("-")[0].trim());
            fetchSubjectCourse(courseCode, courseSemester);
        }
    }//GEN-LAST:event_selectSemsterActionPerformed

    private void selectSemsterPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_selectSemsterPropertyChange

    }//GEN-LAST:event_selectSemsterPropertyChange

    private void generateNewTimeTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateNewTimeTableActionPerformed
        GenerateTimeTable generateTimeTable;
        generateTimeTable = new GenerateTimeTable(null, true);
        generateTimeTable.setVisible(true);
    }//GEN-LAST:event_generateNewTimeTableActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel coursesPanel;
    private javax.swing.JButton generateNewTimeTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel noTimeTable;
    private javax.swing.JComboBox<String> selectCourse;
    private javax.swing.JComboBox<String> selectSemster;
    private javax.swing.JPanel teachersPanel;
    private javax.swing.JTable timeTable;
    private javax.swing.JPanel timeTableFound;
    private javax.swing.JPanel timetableContainer;
    // End of variables declaration//GEN-END:variables
}
