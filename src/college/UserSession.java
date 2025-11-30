package college;

import collegemanagement.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserSession {

    private static int userId;
    private static String applicationNo;
    private static String rollNo;
    private static String userName;
    private static String email;
    private static String userRole;
    private static String birthDate;
    private static String fatherName;
    private static String motherName;
    private static String fatherOccu;
    private static String motherOccu;
    private static String address;
    private static int semester = 0;
    
    private static long contactNo;

    private static String courseName = "";
    private static boolean isLoggedIn = false;

    public static void setSession(int id, String role) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql;
            if ("student".equalsIgnoreCase(role)) {
                sql = "select u.userId, u.userName, u.contact, u.role, s.currSemester, u.email, s.rollNo as uniqueNo, s.dob, s.father_name, s.mother_name, s.father_occu, s.mother_occu, s.address, c.courseName from users u "
                        + "join students s on s.studentId = u.userId "
                        + "join courses c on c.courseId = s.courseId "
                        + "where u.userId = ?;";
            } else if ("admin".equalsIgnoreCase(role)) {
                System.out.println("Admin Running");
                sql = "select u.userId, u.userName, u.contact, u.role, u.email, u.contact, a.adminId as uniqueNo, a.dob, a.address from users u "
                        + "join admins a on a.userId = u.userId "
                        + "where u.userId = ?;";

            } else {
                sql = "select u.userId, u.userName, u.contact, u.role, u.email, u.contact, t.employeeId as uniqueNo, t.dob, t.father_name, t.mother_name, t.father_occu, t.mother_occu, t.address, d.departmentName from users u "
                        + "join teachers t on t.userId = u.userId "
                        + "join departments d on d.departmentId = t.departmentId "
                        + "where u.userId = ?;";
            }
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, "" + id);
            ResultSet res = pst.executeQuery();

            if (res.next()) {
                System.out.println(res.getInt("userID") + " " + res.getString("userName") + " " + res.getString("email") + " " + res.getString("contact"));
                LocalDate currentDate = LocalDate.now();
                int currentYear = currentDate.getYear();
                userId = res.getInt("userID");
                userName = res.getString("userName");
                email = res.getString("email");
                contactNo = res.getLong("contact");
                userRole = res.getString("role");
//                birthDate = res.getDate("dob").toString();

                java.sql.Date dateFromDB = res.getDate("dob");
                birthDate = (dateFromDB != null) ? res.getDate("dob").toString() : "N/A";

                address = res.getString("address");
                isLoggedIn = true;
                rollNo = res.getString("uniqueNo");
                if ("student".equalsIgnoreCase(role) || "faculty".equalsIgnoreCase(role)) {
                    semester = res.getInt("currSemester");
                    fatherName = res.getString("father_name");
                    motherName = res.getString("mother_name");
                    fatherOccu = res.getString("father_occu");
                    motherOccu = res.getString("mother_occu");
                    if ("student".equalsIgnoreCase(role)) {
                        courseName = res.getString("courseName");
                        applicationNo = res.getString("courseName") + String.format("%0" + 4 + "d", res.getInt("userID"));
//                        rollNo = res.getString("courseName") + String.format("%0" + 4 + "d", res.getInt("userID")) + currentYear;
                    } else {
                        applicationNo = "FACULTY" + String.format("%0" + 4 + "d", res.getInt("userID"));
//                        rollNo = "FACULTY" + String.format("%0" + 4 + "d", res.getInt("userID")) + currentYear;
                    }

                } else if ("admin".equalsIgnoreCase(role)) {
                    applicationNo = "ADMIN" + String.format("%0" + 4 + "d", res.getInt("userID"));
//                    rollNo = "ADMIN" + String.format("%0" + 4 + "d", res.getInt("userID")) + currentYear;
                } else {
                    applicationNo = "FACULTY" + String.format("%0" + 4 + "d", res.getInt("userID"));
//                    rollNo = "FACULTY" + String.format("%0" + 4 + "d", res.getInt("userID")) + currentYear;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error in register User Session: " + e.getMessage());
        }
    }

    public static void setSession(int id, String name, String emailId, String role) {
        userId = id;
        userName = name;
        email = emailId;
        userRole = role;
        isLoggedIn = true;
        System.out.println("User ID: " + id + " UserName: " + name + "User Role: " + role);
    }

    public static void cleanSession() {
        userId = 0;
        applicationNo = null;
        rollNo = null;
        userName = null;
        email = null;
        userRole = null;
        birthDate = null;
        fatherName = null;
        motherName = null;
        fatherOccu = null;
        motherOccu = null;
        address = null;
        contactNo = 0;
        courseName = "";
        isLoggedIn = false;
    }

    public static int getUserID() {
        return userId;
    }
    
    public static int getSemester() {
        return semester;
    }

    public static String getContactNumber() {
        return "" + contactNo;
    }

    public static String getBirthDate() {
        return birthDate;
    }

    public static String getFatherName() {
        return fatherName;
    }

    public static String getMotherName() {
        return motherName;
    }

    public static String getFatherOccu() {
        return fatherOccu;
    }

    public static String getMotherOccu() {
        return motherOccu;
    }

    public static String getCourseName() {
        return courseName;
    }

    public static String getAddress() {
        return address;
    }

    public static String getApplicationNumber() {
        return applicationNo;
    }

    public static String getRollNumber() {
        return rollNo;
    }

    public static String getUsername() {
        return userName;
    }

    public static String getEmail() {
        return email;
    }

    public static String getUserRole() {
        return userRole;
    }

    public static boolean isLoggedIn() {
        return isLoggedIn;
    }
}
