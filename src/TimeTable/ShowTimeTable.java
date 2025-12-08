package TimeTable;

import collegemanagement.DBConnection;
import collegemanagement.Subject;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

class TeacherInfo {

    public boolean[][] freeSlots;
    String shortName;
    String Name;
    java.awt.Color color;

    public TeacherInfo(String Name, String shortName, java.awt.Color color) {
        this.Name = Name;
        this.shortName = shortName;
        this.color = color;
        this.freeSlots = new boolean[7][10];
        for (boolean[] row : this.freeSlots) {
            Arrays.fill(row, true);
        }
    }

    public void setOccupied(int dayIndex, int slotIndex) {
        if (dayIndex >= 0 && dayIndex < 7 && slotIndex >= 0 && slotIndex < 10) {
            freeSlots[dayIndex][slotIndex] = false;
        } else {
            System.err.println("Invalid slot attempted: Day " + dayIndex + ", Slot " + slotIndex);
        }
    }

    public boolean checkOccupied(int dayIndex, int slotIndex){
        if (dayIndex >= 0 && dayIndex < 7 && slotIndex >= 0 && slotIndex < 10) {
            return freeSlots[dayIndex][slotIndex];
        }
        return true;
    }
    
    public static int getDayIndex(String dayName) {
        if (dayName == null) {
            return -1;
        }
        return switch (dayName.trim().toLowerCase()) {
            case "monday" ->
                0;
            case "tuesday" ->
                1;
            case "wednesday" ->
                2;
            case "thursday" ->
                3;
            case "friday" ->
                4;
            case "saturday" ->
                5;
            case "sunday" ->
                6;
            default ->
                -1;
        };
    }

    public void laterDelete() {
        for (int i = 0; i < 7; i++) {
            System.out.print("Day " + (i + 1) + ": ");
            for (int j = 0; j < 10; j++) {
                System.out.print((freeSlots[i][j] ? "T" : "F") + " ");
            }
            System.out.println();
        }
    }
}

public final class ShowTimeTable extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ShowTimeTable.class.getName());

    DefaultTableModel model;

    public ShowTimeTable() {
        initComponents();
    }

    public ShowTimeTable(String courseCode, int courseSemester) {
        initComponents();
        selectedCourse.setText(courseCode);
        selectedSemster.setText("" + courseSemester);

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
        fetchSubjectCourse(courseCode.split("-")[0].trim(), courseSemester);
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

    public void fetchSubjectCourse(String courseCode, int courseSemester) {
        String subjectName, subjectCode, userName, classType, shortName;
        List<Subject> subjectList = new ArrayList<Subject>();
        List<TeacherInfo> teachersList = new ArrayList<>();
        Map<String, Color> colorMap = new HashMap<>();

        List<String> condition = new ArrayList<String>();
        int count = 0;
        int week_total_classes, week_taken_classes;

        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
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
        String slotType;
        Set<String> processedSubjects = new HashSet<>();

        try {
            model.setRowCount(0);
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT s.subjectName, s.subjectCode, s.type, u.userName, s.shortName, scr.week_total_classes, scr.week_taken_classes, ts.* "
                    + "FROM course_subjects s "
                    + "JOIN teachers t ON t.employeeId = s.teachesBy "
                    + "JOIN teacher_schedule ts on ts.employeeId = t.employeeId "
                    + "JOIN courses c ON c.courseId = s.courseId "
                    + "JOIN users u ON u.userId = t.userId "
                    + "JOIN subjectclassrecord scr ON scr.subjectId = s.subjectId "
                    + "WHERE c.courseCode = ?  AND s.semester = ?;";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, courseCode);
            pst.setInt(2, courseSemester);
            try (ResultSet res = pst.executeQuery()) {
                boolean hasResults = false;

                while (res.next()) {
                    hasResults = true;
                    shortName = res.getString("shortName");
                    subjectCode = res.getString("subjectCode");
                    String uniqueKey = subjectCode + res.getString("type");
                    String day = res.getString("day_name");
                    int slot = res.getInt("slot_number");
                    userName = res.getString("userName");
                    for (TeacherInfo teacher : teachersList) {
                        if (teacher.shortName.equals(getInitials(userName))) {
                            System.out.println("ABCDEFGHIJKLMNOP");
                            teacher.setOccupied(TeacherInfo.getDayIndex(day), slot);
                        }
                    }
                    if (!processedSubjects.contains(uniqueKey)) {
                        processedSubjects.add(uniqueKey);
                        subjectName = res.getString("subjectName");
                        classType = res.getString("type");
                        week_total_classes = res.getInt("week_total_classes");
                        week_taken_classes = res.getInt("week_taken_classes");

                        Color specificColor = getSubjectColor(count);

                        colorMap.put(shortName.toUpperCase(), specificColor);
                        subjectList.add(new Subject(subjectName, shortName.toUpperCase(), classType.toLowerCase(), week_total_classes, specificColor));

                        if (classType.equalsIgnoreCase("class")) {
                            teachersList.add(new TeacherInfo(userName, getInitials(userName), specificColor));
                        }

                        // Only increment count when a NEW subject is found
                        count++;
                    }
                }

                if (!hasResults) {
                    System.out.println("No subjects found for this course and semester.");
                } else {
                    System.out.println("Subjects loaded successfully.");
                }
            }
            pst.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error in Time Table: " + e.getMessage());
        }
//        List<Subject> copySubjectList = subjectList;
        for (int i = 0; i < 6; i++) {
            List<Subject> labSubjects = subjectList.stream()
                    .filter(s -> s.classType.equalsIgnoreCase("lab") && s.remainingClasses > 0)
                    .collect(Collectors.toList());
            for (int j = 0; j < data[i].length - 1; j++) {
                Boolean foundOccupied = false;
                for (TeacherInfo teacher : teachersList) {
                    foundOccupied = teacher.checkOccupied(i,j);
                }
//                if(foundOccupied){
//                    subjectList.remove(j);
//                }
                
                if (data[i][j] != null) {
                    continue;
                }
                slotType = j > 6 && j < 9 && !labSubjects.isEmpty() ? "lab" : "normal";
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                if ("lab".equals(slotType) && data[i][j + 1] != null) {
                    continue;
                }
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                data[i][j] = subjectClass(subjectList, slotType);
                if ("lab".equals(slotType)) {
                    data[i][j + 1] = data[i][j];
                    j++;
                }
            }
        }

        for (Object[] data1 : data) {
            model.addRow(data1);
        }

        for (Subject s : subjectList) {
            if (s.remainingClasses > 0) {
                System.out.println(s.getS_name() + " " + s.remainingClasses);
            }
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

    private void saveTimeTableToDatabase() {
        String rawCourse = selectedCourse.getText();
        String courseCode = rawCourse.split("-")[0].trim();
        int courseId = 0;
        int semester = Integer.parseInt(selectedSemster.getText().trim());
        Connection conn = null;
        PreparedStatement pstDelete = null;
        PreparedStatement pstInsert = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            String sql = "SELECT * from courses where courseCode = ? ;";
            PreparedStatement pstGetId = conn.prepareStatement(sql);
            pstGetId.setString(1, courseCode);
            ResultSet rs = pstGetId.executeQuery();

            if (rs.next()) {
                courseId = rs.getInt("courseId");
            }
            // 1. DELETE existing data for this Course + Semester + Section            
            String deleteSQL = "DELETE FROM timetable_schedule WHERE courseId = ? AND semester = ? AND section = ?";
            pstDelete = conn.prepareStatement(deleteSQL);
            pstDelete.setInt(1, courseId);
            pstDelete.setInt(2, semester);
            pstDelete.setString(3, "A");
            pstDelete.executeUpdate();

            // 2. INSERT new data including Section
            String insertSQL = "INSERT INTO timetable_schedule "
                    + "(courseId, semester, section, day_name, slot_1, slot_2, slot_3, slot_4, slot_5, slot_6, slot_7, slot_8, slot_9) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            pstInsert = conn.prepareStatement(insertSQL);

            for (int i = 0; i < model.getRowCount(); i++) {
                pstInsert.setInt(1, courseId);
                pstInsert.setInt(2, semester);
                pstInsert.setString(3, "A");

                pstInsert.setString(4, model.getValueAt(i, 0).toString());

                // Slots 1-9
                for (int j = 1; j <= 9; j++) {
                    Object val = model.getValueAt(i, j);
                    pstInsert.setString(4 + j, (val != null) ? val.toString() : "");
                }

                pstInsert.addBatch();
            }

            pstInsert.executeBatch();
            conn.commit();

            javax.swing.JOptionPane.showMessageDialog(this, "Time Table for Section " + "A " + " Approved!");
            this.dispose();
        } catch (SQLException | ClassNotFoundException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
            }
            javax.swing.JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        } finally {
            try {
                if (pstDelete != null) {
                    pstDelete.close();
                }
                if (pstInsert != null) {
                    pstInsert.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
            }
        }
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        timeTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        selectedCourse = new javax.swing.JLabel();
        selectedSemster = new javax.swing.JLabel();
        teachersPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        coursesPanel = new javax.swing.JPanel();
        approve = new javax.swing.JButton();
        refresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Time Table");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Course");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Select Semster");

        selectedCourse.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        selectedCourse.setText("jLabel6");

        selectedSemster.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        selectedSemster.setText("jLabel6");

        teachersPanel.setBackground(new java.awt.Color(248, 251, 255));
        teachersPanel.setOpaque(false);
        teachersPanel.setLayout(new javax.swing.BoxLayout(teachersPanel, javax.swing.BoxLayout.LINE_AXIS));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Teachers");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Courses");

        coursesPanel.setBackground(new java.awt.Color(248, 251, 255));
        coursesPanel.setOpaque(false);
        coursesPanel.setLayout(new javax.swing.BoxLayout(coursesPanel, javax.swing.BoxLayout.LINE_AXIS));

        approve.setBackground(new java.awt.Color(0, 153, 204));
        approve.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        approve.setForeground(new java.awt.Color(255, 255, 255));
        approve.setText("APPROVE");
        approve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                approveActionPerformed(evt);
            }
        });

        refresh.setBackground(new java.awt.Color(0, 204, 0));
        refresh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        refresh.setForeground(new java.awt.Color(255, 255, 255));
        refresh.setText("REFRESH");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel5)
                                .addGap(341, 341, 341))
                            .addComponent(coursesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(284, 284, 284)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addGap(331, 331, 331))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(teachersPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)))
                        .addGap(93, 93, 93))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(refresh)
                        .addGap(18, 18, 18)
                        .addComponent(approve)
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(selectedSemster))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(selectedCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1317, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectedCourse)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectedSemster)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 454, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(approve)
                    .addComponent(refresh))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(coursesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(teachersPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(85, 85, 85)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(261, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        fetchSubjectCourse(selectedCourse.getText().split("-")[0].trim(), Integer.parseInt(selectedSemster.getText()));
    }//GEN-LAST:event_refreshActionPerformed

    private void approveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_approveActionPerformed
        // TODO add your handling code here:
        saveTimeTableToDatabase();
    }//GEN-LAST:event_approveActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new ShowTimeTable().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton approve;
    private javax.swing.JPanel coursesPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton refresh;
    private javax.swing.JLabel selectedCourse;
    private javax.swing.JLabel selectedSemster;
    private javax.swing.JPanel teachersPanel;
    private javax.swing.JTable timeTable;
    // End of variables declaration//GEN-END:variables
}
