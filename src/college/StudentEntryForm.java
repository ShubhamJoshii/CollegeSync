package college;

import collegemanagement.DBConnection;
import java.awt.CardLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;

public class StudentEntryForm extends javax.swing.JPanel {

    MainFrame main;
    private String user, mail, contact, father, mother, fatherOccu, motherOccu, address;
    Date birthDate;

    public StudentEntryForm(MainFrame main) {
        initComponents();
        this.main = main;
    }

    public void updateDetails() {
        detailsHeader.remove(designationHeader);
        detailsHeader.add(courseHeader);
        applicationNo.setText(UserSession.getApplicationNumber());
        uniqueNo.setText(UserSession.getRollNumber());
        fullName.setText(UserSession.getUsername());
        emailId.setText(UserSession.getEmail());
        course.setText(UserSession.getCourseName());
        emailId.setText(UserSession.getEmail());
        contactNo.setText(UserSession.getContactNumber());
        heading.setText(UserSession.getUserRole() + " Detail Registration");
        fullName.setEnabled(false);
        emailId.setEnabled(false);
        if ("admin".equalsIgnoreCase(UserSession.getUserRole())) {
            detailsPanel.remove(personalDetails);
        } else {
            fatherName.setText(UserSession.getFatherName());
            motherName.setText(UserSession.getMotherName());
            fatherOccupaton.setText(UserSession.getFatherOccu());
            motherOccupation.setText(UserSession.getMotherOccu());
        }
        if ("faculty".equalsIgnoreCase(UserSession.getUserRole())) {
            uniqueNoField.setText("Registration Number");
            detailsHeader.remove(courseHeader);
            detailsHeader.add(designationHeader);
            course.setVisible(false);
        }

    }

    private String checkValidation() {
        char[] pass = password.getPassword();
        char[] confirmPass = confirmPassword.getPassword();

        user = fullName.getText().trim();
        mail = emailId.getText().trim();
        contact = contactNo.getText().trim();
        birthDate = DOB.getDate();
        address = addressField.getText().trim();

        if (user.isEmpty() || mail.isEmpty() || address.isEmpty() || contact.isEmpty()) {
            return "All fields are required.";
        }
        // Contact number must contain digits only
        if (!contact.matches("\\d+")) {
            contactNo.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            return "Contact number must contain only digits.";
        }

        // Contact length
        if (contact.length() != 10) {
            contactNo.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            return "Contact number must be exactly 10 digits.";
        }
        contactNo.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        // Password length
        if (pass.length < 6) {
            password.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            confirmPassword.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            return "Password must be at least 6 characters long.";
        }

        // Password match
        if (!Arrays.equals(pass, confirmPass)) {
            password.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            confirmPassword.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            return "Passwords do not match!";
        }

        // Reset borders if OK
        password.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        confirmPassword.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        if ("student".equalsIgnoreCase(UserSession.getUserRole())) {
            return checkStudentValidation();
        } else if ("admin".equalsIgnoreCase(UserSession.getUserRole())) {

        } else {
        }

        return null;
    }

    private String checkStudentValidation() {
        father = fatherName.getText().trim();
        mother = motherName.getText().trim();
        fatherOccu = fatherOccupaton.getText().trim();
        motherOccu = motherOccupation.getText().trim();

        // Required fields
        if (father.isEmpty() || mother.isEmpty() || motherOccu.isEmpty() || fatherOccu.isEmpty()) {
            return "All fields are required.";
        }

        return null;
    }

    private String updateBackendDetails() {
        char[] pass = password.getPassword();
        String hashedPassword = BCrypt.hashpw(new String(pass), BCrypt.gensalt(12));
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        String rollNo;
        try {
            Connection conn = DBConnection.getConnection();
            String sql;
            PreparedStatement pst;
            int status = 0;
            if ("student".equalsIgnoreCase(UserSession.getUserRole())) {
                sql = "UPDATE students SET dob=?, father_name=?, mother_name=?, father_occu=?, mother_occu=?, rollNo=?, address=?  WHERE studentId=?";
                pst = conn.prepareStatement(sql);
                pst.setDate(1, new java.sql.Date(birthDate.getTime()));
                pst.setString(2, father);
                pst.setString(3, mother);
                pst.setString(4, fatherOccu);
                pst.setString(5, motherOccu);
                rollNo = UserSession.getCourseName() + String.format("%0" + 4 + "d", UserSession.getUserID()) + currentYear;
                pst.setString(6, rollNo);
                pst.setString(7, address);
                pst.setInt(8, UserSession.getUserID());
                status = pst.executeUpdate();

            } else if ("admin".equalsIgnoreCase(UserSession.getUserRole())) {
                sql = "UPDATE admins SET dob=?, address=?, adminId=?  WHERE userId=?";
                rollNo = "ADMIN" + String.format("%0" + 4 + "d", UserSession.getUserID()) + currentYear;
                
                pst = conn.prepareStatement(sql);
                pst.setDate(1, new java.sql.Date(birthDate.getTime()));
                pst.setString(2, address);
                pst.setString(3, rollNo);
                pst.setInt(4, UserSession.getUserID());
                status = pst.executeUpdate();

            } else {
                sql = "UPDATE teachers SET dob=?, father_name=?, mother_name=?, father_occu=?, mother_occu=?, address=?, employeeId=?, designation=?, joiningDate=?  WHERE userId=?";
                pst = conn.prepareStatement(sql);
                pst.setDate(1, new java.sql.Date(birthDate.getTime()));
                pst.setString(2, fatherName.getText());
                pst.setString(3, motherName.getText());
                pst.setString(4, fatherOccupaton.getText());
                pst.setString(5, motherOccupation.getText());
                pst.setString(6, address);
                rollNo = "FACULTY" + String.format("%0" + 4 + "d", UserSession.getUserID()) + currentYear;
                pst.setString(7, rollNo);
                pst.setDate(1, new java.sql.Date(birthDate.getTime()));
                pst.setString(8, designationField.getText());
                pst.setDate(9, new java.sql.Date(System.currentTimeMillis()));
                pst.setInt(10, UserSession.getUserID());

                status = pst.executeUpdate();
            }

            if (status > 0) {
                System.out.println("Success Students");
                sql = "UPDATE users SET password=?, contact=?, status=? WHERE userId=?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, hashedPassword);
                pst.setString(2, contact);
                pst.setString(3, "Complete");
                pst.setInt(4, UserSession.getUserID());

                status = pst.executeUpdate();
                if (status > 0) {
                    return "Student Details Updated!";
                }
            } else {
                System.out.println("Un-Success Students");
                return "Student Details Updation Failed!";
            }
            UserSession.setSession(UserSession.getUserID(), UserSession.getUserRole());
            pst.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error in Student Entry Form: " + e.getMessage());
            return "Updation Failed: " + e.getMessage();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel26 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        photoDP = new java.awt.Panel();
        Savebtn = new javax.swing.JButton();
        photoBtn = new javax.swing.JButton();
        heading = new javax.swing.JLabel();
        detailsPanel = new javax.swing.JPanel();
        generalDetails = new javax.swing.JPanel();
        fullName = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        emailId = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        contactNo = new javax.swing.JTextField();
        DOB = new com.toedter.calendar.JDateChooser();
        personalDetails = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        fatherName = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        motherOccupation = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        motherName = new javax.swing.JTextField();
        fatherOccupaton = new javax.swing.JTextField();
        addressPasswordDetails = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        addressField = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        confirmPassword = new javax.swing.JPasswordField();
        jLabel34 = new javax.swing.JLabel();
        detailsHeader = new javax.swing.JPanel();
        applicationNoHeader = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        applicationNo = new javax.swing.JLabel();
        uniqueNoHeader = new javax.swing.JPanel();
        uniqueNoField = new javax.swing.JLabel();
        uniqueNo = new javax.swing.JLabel();
        courseHeader = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        course = new javax.swing.JLabel();
        designationHeader = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        designationField = new javax.swing.JTextField();

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("© 2025 EduManage. All rights reserved.");

        setBackground(new java.awt.Color(248, 251, 255));
        setPreferredSize(new java.awt.Dimension(1170, 800));
        setLayout(null);

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

        add(photoDP);
        photoDP.setBounds(910, 180, 150, 176);

        Savebtn.setBackground(new java.awt.Color(0, 102, 102));
        Savebtn.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Savebtn.setForeground(new java.awt.Color(255, 255, 255));
        Savebtn.setText("SAVE");
        Savebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SavebtnActionPerformed(evt);
            }
        });
        add(Savebtn);
        Savebtn.setBounds(440, 640, 250, 40);

        photoBtn.setText("Select Photo");
        add(photoBtn);
        photoBtn.setBounds(930, 370, 110, 23);

        heading.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        heading.setText("Student Detail Registration");
        add(heading);
        heading.setBounds(40, 50, 350, 40);

        detailsPanel.setOpaque(false);

        generalDetails.setOpaque(false);

        fullName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fullNameActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setText("Full Name");

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel36.setText("D.O.B");

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel38.setText("Email ID:");

        emailId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailIdActionPerformed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel39.setText("Phone No.");

        contactNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactNoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout generalDetailsLayout = new javax.swing.GroupLayout(generalDetails);
        generalDetails.setLayout(generalDetailsLayout);
        generalDetailsLayout.setHorizontalGroup(
            generalDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(generalDetailsLayout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(fullName, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(emailId, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(generalDetailsLayout.createSequentialGroup()
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(DOB, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(contactNo, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        generalDetailsLayout.setVerticalGroup(
            generalDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDetailsLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(generalDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel38)
                    .addComponent(emailId, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fullName, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addGap(19, 19, 19)
                .addGroup(generalDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(DOB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36)
                    .addComponent(jLabel39)
                    .addComponent(contactNo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        personalDetails.setOpaque(false);

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel28.setText("Father's Name");

        fatherName.setText("L D Joshi");
        fatherName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fatherNameActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel30.setText("Occupation");

        motherOccupation.setText("House Wife");
        motherOccupation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                motherOccupationActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setText("Occupation");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel32.setText("Mother's Name");

        motherName.setText("Deepa Joshi");
        motherName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                motherNameActionPerformed(evt);
            }
        });

        fatherOccupaton.setText("Government Job");
        fatherOccupaton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fatherOccupatonActionPerformed(evt);
            }
        });

        addressPasswordDetails.setOpaque(false);
        addressPasswordDetails.setPreferredSize(new java.awt.Dimension(129, 127));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Update Password");

        addressField.setText("New Delhi");
        addressField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressFieldActionPerformed(evt);
            }
        });

        password.setText("Joshi@123");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Password");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("Confirm Password");

        confirmPassword.setText("Joshi@123");
        confirmPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmPasswordActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel34.setText("Address");

        javax.swing.GroupLayout addressPasswordDetailsLayout = new javax.swing.GroupLayout(addressPasswordDetails);
        addressPasswordDetails.setLayout(addressPasswordDetailsLayout);
        addressPasswordDetailsLayout.setHorizontalGroup(
            addressPasswordDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addressPasswordDetailsLayout.createSequentialGroup()
                .addGroup(addressPasswordDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addressPasswordDetailsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(addressPasswordDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(120, 120, 120)
                        .addGroup(addressPasswordDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(confirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(addressPasswordDetailsLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addressPasswordDetailsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(addressField, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        addressPasswordDetailsLayout.setVerticalGroup(
            addressPasswordDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addressPasswordDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addressPasswordDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(addressField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(10, 10, 10)
                .addGroup(addressPasswordDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(3, 3, 3)
                .addGroup(addressPasswordDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout personalDetailsLayout = new javax.swing.GroupLayout(personalDetails);
        personalDetails.setLayout(personalDetailsLayout);
        personalDetailsLayout.setHorizontalGroup(
            personalDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(personalDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(personalDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(personalDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(personalDetailsLayout.createSequentialGroup()
                        .addComponent(fatherName, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(motherName, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(personalDetailsLayout.createSequentialGroup()
                        .addComponent(fatherOccupaton, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel30)
                        .addGap(44, 44, 44)
                        .addComponent(motherOccupation, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(personalDetailsLayout.createSequentialGroup()
                .addComponent(addressPasswordDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 825, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        personalDetailsLayout.setVerticalGroup(
            personalDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(personalDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(personalDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel28)
                    .addComponent(fatherName, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(motherName, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(personalDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel31)
                    .addComponent(fatherOccupaton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(motherOccupation, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(addressPasswordDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout detailsPanelLayout = new javax.swing.GroupLayout(detailsPanel);
        detailsPanel.setLayout(detailsPanelLayout);
        detailsPanelLayout.setHorizontalGroup(
            detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(generalDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(personalDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        detailsPanelLayout.setVerticalGroup(
            detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(generalDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(personalDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(133, 133, 133))
        );

        add(detailsPanel);
        detailsPanel.setBounds(40, 160, 840, 400);

        detailsHeader.setOpaque(false);
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 5);
        flowLayout1.setAlignOnBaseline(true);
        detailsHeader.setLayout(flowLayout1);

        applicationNoHeader.setOpaque(false);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Application Number");

        applicationNo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        applicationNo.setText("MCA0445488");
        applicationNo.setPreferredSize(new java.awt.Dimension(120, 20));

        javax.swing.GroupLayout applicationNoHeaderLayout = new javax.swing.GroupLayout(applicationNoHeader);
        applicationNoHeader.setLayout(applicationNoHeaderLayout);
        applicationNoHeaderLayout.setHorizontalGroup(
            applicationNoHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(applicationNoHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(applicationNoHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(applicationNoHeaderLayout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(applicationNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        applicationNoHeaderLayout.setVerticalGroup(
            applicationNoHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, applicationNoHeaderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(applicationNoHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(applicationNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap())
        );

        detailsHeader.add(applicationNoHeader);

        uniqueNoHeader.setOpaque(false);

        uniqueNoField.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        uniqueNoField.setText("Roll Number");

        uniqueNo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uniqueNo.setText("MCA0445488");

        javax.swing.GroupLayout uniqueNoHeaderLayout = new javax.swing.GroupLayout(uniqueNoHeader);
        uniqueNoHeader.setLayout(uniqueNoHeaderLayout);
        uniqueNoHeaderLayout.setHorizontalGroup(
            uniqueNoHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(uniqueNoHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(uniqueNoField, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(uniqueNo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        uniqueNoHeaderLayout.setVerticalGroup(
            uniqueNoHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, uniqueNoHeaderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(uniqueNoHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(uniqueNo)
                    .addComponent(uniqueNoField))
                .addContainerGap())
        );

        detailsHeader.add(uniqueNoHeader);

        courseHeader.setOpaque(false);
        courseHeader.setPreferredSize(new java.awt.Dimension(150, 32));

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel40.setText("Course");

        course.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        course.setText("MCA");

        javax.swing.GroupLayout courseHeaderLayout = new javax.swing.GroupLayout(courseHeader);
        courseHeader.setLayout(courseHeaderLayout);
        courseHeaderLayout.setHorizontalGroup(
            courseHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(courseHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(course, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        courseHeaderLayout.setVerticalGroup(
            courseHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(courseHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(courseHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(course))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        detailsHeader.add(courseHeader);

        designationHeader.setOpaque(false);

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel33.setText("Designation");

        designationField.setText("Assistant Professor");
        designationField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                designationFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout designationHeaderLayout = new javax.swing.GroupLayout(designationHeader);
        designationHeader.setLayout(designationHeaderLayout);
        designationHeaderLayout.setHorizontalGroup(
            designationHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, designationHeaderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(designationField, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        designationHeaderLayout.setVerticalGroup(
            designationHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(designationHeaderLayout.createSequentialGroup()
                .addGroup(designationHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(designationField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        detailsHeader.add(designationHeader);

        add(detailsHeader);
        detailsHeader.setBounds(40, 100, 1020, 40);
    }// </editor-fold>//GEN-END:initComponents

    private void fullNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fullNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fullNameActionPerformed

    private void fatherNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fatherNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fatherNameActionPerformed

    private void SavebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SavebtnActionPerformed
        String result = checkValidation();
        if (result == null) {
            result = updateBackendDetails();
            JOptionPane.showMessageDialog(this,
                    result,
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );
            CardLayout cl = (CardLayout) main.mainPanel.getLayout();
            cl.show(main.mainPanel, "home");
            main.updateButtonVisibility();

            main.showHeader(true);
        } else {
            JOptionPane.showMessageDialog(this,
                    result,
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_SavebtnActionPerformed

    private void motherOccupationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_motherOccupationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_motherOccupationActionPerformed

    private void addressFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addressFieldActionPerformed

    private void motherNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_motherNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_motherNameActionPerformed

    private void emailIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailIdActionPerformed

    private void contactNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contactNoActionPerformed

    private void fatherOccupatonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fatherOccupatonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fatherOccupatonActionPerformed

    private void confirmPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_confirmPasswordActionPerformed

    private void designationFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_designationFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_designationFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DOB;
    private javax.swing.JButton Savebtn;
    private javax.swing.JTextField addressField;
    private javax.swing.JPanel addressPasswordDetails;
    private javax.swing.JLabel applicationNo;
    private javax.swing.JPanel applicationNoHeader;
    private javax.swing.JPasswordField confirmPassword;
    private javax.swing.JTextField contactNo;
    private javax.swing.JLabel course;
    private javax.swing.JPanel courseHeader;
    private javax.swing.JTextField designationField;
    private javax.swing.JPanel designationHeader;
    private javax.swing.JPanel detailsHeader;
    private javax.swing.JPanel detailsPanel;
    private javax.swing.JTextField emailId;
    private javax.swing.JTextField fatherName;
    private javax.swing.JTextField fatherOccupaton;
    private javax.swing.JTextField fullName;
    private javax.swing.JPanel generalDetails;
    private javax.swing.JLabel heading;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextField motherName;
    private javax.swing.JTextField motherOccupation;
    private javax.swing.JPasswordField password;
    private javax.swing.JPanel personalDetails;
    private javax.swing.JButton photoBtn;
    private java.awt.Panel photoDP;
    private javax.swing.JLabel uniqueNo;
    private javax.swing.JLabel uniqueNoField;
    private javax.swing.JPanel uniqueNoHeader;
    // End of variables declaration//GEN-END:variables
}
