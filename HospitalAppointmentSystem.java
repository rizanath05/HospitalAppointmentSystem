import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class HospitalAppointmentSystem extends JFrame implements ActionListener {

    JMenuBar menuBar;
    JMenu patientMenu, doctorMenu, adminMenu;
    JMenuItem regPatient, bookAppt, viewAppt;
    JMenuItem docSchedule, docAvailability;
    JMenuItem addDoctor, managePatients, manageDoctors;

    // Constructor
    public HospitalAppointmentSystem() {
        setTitle("Hospital Appointment System");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // full screen

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Full screen background image
ImageIcon icon = new ImageIcon("hospital_bg.jpg"); // your image
Image img = icon.getImage();
Image scaledImg = img.getScaledInstance(
        Toolkit.getDefaultToolkit().getScreenSize().width,
        Toolkit.getDefaultToolkit().getScreenSize().height,
        Image.SCALE_SMOOTH);

JLabel background = new JLabel(new ImageIcon(scaledImg));
setContentPane(background);
background.setLayout(new BorderLayout());

// Styled Welcome Text (HTML for font + color + size)
JLabel title = new JLabel(
    "<html><div style='color:#003399; font-family:Arial, Helvetica, sans-serif; font-size:40px; font-weight:bold;'>"
    + "Welcome to<br/>Hospital Appointment<br/> System</div></html>"
);
title.setHorizontalAlignment(SwingConstants.LEFT);
title.setVerticalAlignment(SwingConstants.TOP);
title.setBorder(BorderFactory.createEmptyBorder(200, 80, 0, 0)); // position padding
background.add(title, BorderLayout.WEST);



      


        
        // Menu bar
        menuBar = new JMenuBar();
        patientMenu = new JMenu("Patient");
        doctorMenu = new JMenu("Doctor");
        adminMenu = new JMenu("Admin");

        // Patient menu items
        regPatient = new JMenuItem("Register Patient");
        bookAppt = new JMenuItem("Book Appointment");
        viewAppt = new JMenuItem("View Appointments");
        patientMenu.add(regPatient);
        patientMenu.add(bookAppt);
        patientMenu.add(viewAppt);

        // Doctor menu items
        docSchedule = new JMenuItem("View Schedule");
        docAvailability = new JMenuItem("Update Availability");
        doctorMenu.add(docSchedule);
        doctorMenu.add(docAvailability);

        // Admin menu items
        addDoctor = new JMenuItem("Add Doctor");
        managePatients = new JMenuItem("Manage Patients");
        manageDoctors = new JMenuItem("Manage Doctors");
        adminMenu.add(addDoctor);
        adminMenu.add(managePatients);
        adminMenu.add(manageDoctors);

        // Add menus
        menuBar.add(patientMenu);
        menuBar.add(doctorMenu);
        menuBar.add(adminMenu);
        setJMenuBar(menuBar);

        // Action listeners
        regPatient.addActionListener(this);
        bookAppt.addActionListener(this);
        viewAppt.addActionListener(this);
        docSchedule.addActionListener(this);
        docAvailability.addActionListener(this);
        addDoctor.addActionListener(this);
        managePatients.addActionListener(this);
        manageDoctors.addActionListener(this);

        setVisible(true);
    }

    // Handling menu clicks
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == regPatient) {
            showPatientRegistrationForm();
        } else if (e.getSource() == bookAppt) {
            showBookAppointmentForm();
        } else if (e.getSource() == addDoctor) {
            showAddDoctorForm();
        } else if (e.getSource() == viewAppt) {
            showAppointmentsTable();
        }
    }

    // ==== FORMS ====

    // Patient Registration

    private void showPatientRegistrationForm() {
    JFrame f = new JFrame("Patient Registration");
    f.setSize(450, 400);
    f.setLocationRelativeTo(null);
    f.setLayout(new BorderLayout(10, 10));

    // Title
    JLabel title = new JLabel("Patient Registration", SwingConstants.CENTER);
    title.setFont(new Font("Arial", Font.BOLD, 22));
    title.setForeground(new Color(0, 51, 153)); // dark blue
    f.add(title, BorderLayout.NORTH);

    // Form panel
    JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 15));
    formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

    formPanel.add(new JLabel("Name:"));
    JTextField name = new JTextField();
    formPanel.add(name);

    formPanel.add(new JLabel("Age:"));
    JTextField age = new JTextField();
    formPanel.add(age);

    formPanel.add(new JLabel("Gender:"));
    JComboBox<String> gender = new JComboBox<>(new String[]{"Male", "Female"});
    formPanel.add(gender);

    formPanel.add(new JLabel("Contact:"));
    JTextField contact = new JTextField();
    formPanel.add(contact);

    formPanel.add(new JLabel("Email:"));
    JTextField email = new JTextField();
    formPanel.add(email);

    f.add(formPanel, BorderLayout.CENTER);

    // Buttons panel
    JPanel buttonPanel = new JPanel();
    JButton registerBtn = new JButton("Register");
    registerBtn.setBackground(new Color(34, 167, 34)); // green
    registerBtn.setForeground(Color.WHITE);
    registerBtn.setFocusPainted(false);

    JButton cancelBtn = new JButton("Cancel");
    cancelBtn.setBackground(new Color(192, 57, 43)); // red
    cancelBtn.setForeground(Color.WHITE);
    cancelBtn.setFocusPainted(false);

    buttonPanel.add(registerBtn);
    buttonPanel.add(cancelBtn);

    f.add(buttonPanel, BorderLayout.SOUTH);

    f.setVisible(true);
}
   
// 📌 Appointment Booking Form
private void showBookAppointmentForm() {
    JFrame f = new JFrame("Book Appointment");
    f.setSize(400, 350);
    f.setLocationRelativeTo(null);
    f.setLayout(new GridLayout(6, 2, 10, 10));

    JLabel title = new JLabel("Book Appointment", SwingConstants.CENTER);
    title.setFont(new Font("Arial", Font.BOLD, 18));
    f.add(title);
    f.add(new JLabel("")); // Empty cell

    f.add(new JLabel("Patient Name:"));
    JTextField patientName = new JTextField();
    f.add(patientName);

    f.add(new JLabel("Doctor:"));
    JComboBox<String> doctorList = new JComboBox<>(new String[]{"Dr. Rahul - Cardiologist", "Dr. Harshit - Neurosurgeon"});
    f.add(doctorList);

    f.add(new JLabel("Date (YYYY-MM-DD):"));
    JTextField date = new JTextField();
    f.add(date);

    f.add(new JLabel("Time:"));
    JTextField time = new JTextField();
    f.add(time);

    JButton book = new JButton("Book");
    JButton cancel = new JButton("Cancel");
    f.add(book);
    f.add(cancel);

    f.setVisible(true);
}

// 📌 Add Doctor Form
private void showAddDoctorForm() {
    JFrame f = new JFrame("Add Doctor");
    f.setSize(400, 300);
    f.setLocationRelativeTo(null);
    f.setLayout(new GridLayout(5, 2, 10, 10));

    JLabel title = new JLabel("Add Doctor", SwingConstants.CENTER);
    title.setFont(new Font("Arial", Font.BOLD, 18));
    f.add(title);
    f.add(new JLabel("")); // Empty cell

    f.add(new JLabel("Doctor Name:"));
    JTextField doctorName = new JTextField();
    f.add(doctorName);

    f.add(new JLabel("Specialization:"));
    JTextField specialization = new JTextField();
    f.add(specialization);

    f.add(new JLabel("Contact:"));
    JTextField contact = new JTextField();
    f.add(contact);

    JButton add = new JButton("Add Doctor");
    JButton cancel = new JButton("Cancel");
    f.add(add);
    f.add(cancel);

    f.setVisible(true);
}

    // View Appointments Table
    private void showAppointmentsTable() {
        JFrame f = new JFrame("Appointments");
        f.setSize(600, 400);
        f.setLocationRelativeTo(null);

        String[] columns = {"Appt ID", "Patient ID", "Doctor", "Date", "Time", "Status"};
        String[][] data = {
            {"1", "P001", "Dr. Rahul", "2025-10-05", "10:00 AM", "Booked"},
            {"2", "P002", "Dr. Harshit", "2025-10-06", "11:00 AM", "Booked"}
        };

        JTable table = new JTable(new DefaultTableModel(data, columns));
        JScrollPane sp = new JScrollPane(table);
        f.add(sp);

        f.setVisible(true);
    }

    public static void main(String[] args) {
        new HospitalAppointmentSystem();
    }
}