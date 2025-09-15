import java.awt.*;
import javax.swing.*;
import java.time.LocalDate;

public class HospitalAppointmentSystem {
    JFrame frame = new JFrame("🏥 Hospital Appointment System");

    // Labels
    JLabel titleLabel = new JLabel("Hospital Appointment Booking", SwingConstants.CENTER);
    JLabel idLabel = new JLabel("Patient ID:");
    JLabel nameLabel = new JLabel("Patient Name:");
    JLabel phoneLabel = new JLabel("Phone Number:");
    JLabel doctorLabel = new JLabel("Select Doctor:");
    JLabel dateLabel = new JLabel("Appointment Date:");
    JLabel timeLabel = new JLabel("Appointment Time:");

    // Input fields
    JTextField idField = new JTextField(15);
    JTextField nameField = new JTextField(15);
    JTextField phoneField = new JTextField(15);

    // Doctor selection
    String[] doctors = {
        "Dr. Smith - Cardiologist",
        "Dr. John - Dentist",
        "Dr. Maria - Neurologist",
        "Dr. Lee - Pediatrician"
    };
    JComboBox<String> doctorDropdown = new JComboBox<>(doctors);

    // Date spinners
    SpinnerNumberModel dayModel = new SpinnerNumberModel(1, 1, 31, 1);
    SpinnerNumberModel monthModel = new SpinnerNumberModel(1, 1, 12, 1);
    SpinnerNumberModel yearModel = new SpinnerNumberModel(LocalDate.now().getYear(), 2025, 2100, 1);

    JSpinner daySpinner = new JSpinner(dayModel);
    JSpinner monthSpinner = new JSpinner(monthModel);
    JSpinner yearSpinner = new JSpinner(yearModel);

    // Time spinners
    SpinnerNumberModel hourModel = new SpinnerNumberModel(9, 0, 23, 1);
    SpinnerNumberModel minuteModel = new SpinnerNumberModel(0, 0, 59, 5);

    JSpinner hourSpinner = new JSpinner(hourModel);
    JSpinner minuteSpinner = new JSpinner(minuteModel);

    // Buttons
    JButton confirmButton = new JButton("Confirm Appointment");
    JButton resetButton = new JButton("New Patient");

    // Patient ID counter
    private static int patientCounter = 1000;

    public HospitalAppointmentSystem() {
        // Frame setup
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(15, 15));

        // Title customization
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 51, 102));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(224, 247, 250)); // soft light blue
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 16);
        Font inputFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 16);

        // Set label fonts
        idLabel.setFont(labelFont);
        nameLabel.setFont(labelFont);
        phoneLabel.setFont(labelFont);
        doctorLabel.setFont(labelFont);
        dateLabel.setFont(labelFont);
        timeLabel.setFont(labelFont);

        // Set input fonts
        idField.setFont(inputFont);
        nameField.setFont(inputFont);
        phoneField.setFont(inputFont);
        doctorDropdown.setFont(inputFont);
        daySpinner.setFont(inputFont);
        monthSpinner.setFont(inputFont);
        yearSpinner.setFont(inputFont);
        hourSpinner.setFont(inputFont);
        minuteSpinner.setFont(inputFont);

        // Patient ID
        idField.setEditable(false);
        idField.setText(generatePatientID());
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(idLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(idField, gbc);

        // Patient Name
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        // Phone Number
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(phoneLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(phoneField, gbc);

        // Doctor selection
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(doctorLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(doctorDropdown, gbc);

        // Appointment Date
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(dateLabel, gbc);
        gbc.gridx = 1;
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        datePanel.setBackground(new Color(224, 247, 250));
        datePanel.add(daySpinner);
        datePanel.add(new JLabel("/"));
        datePanel.add(monthSpinner);
        datePanel.add(new JLabel("/"));
        datePanel.add(yearSpinner);
        formPanel.add(datePanel, gbc);

        // Appointment Time
        gbc.gridx = 0; gbc.gridy = 5;
        formPanel.add(timeLabel, gbc);
        gbc.gridx = 1;
        JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        timePanel.setBackground(new Color(224, 247, 250));
        timePanel.add(hourSpinner);
        timePanel.add(new JLabel(":"));
        timePanel.add(minuteSpinner);
        formPanel.add(timePanel, gbc);

        // Buttons
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(224, 247, 250));
        confirmButton.setBackground(new Color(0, 153, 76));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFont(buttonFont);
        confirmButton.setFocusPainted(false);

        resetButton.setBackground(new Color(204, 51, 51));
        resetButton.setForeground(Color.WHITE);
        resetButton.setFont(buttonFont);
        resetButton.setFocusPainted(false);

        buttonPanel.add(confirmButton);
        buttonPanel.add(resetButton);
        formPanel.add(buttonPanel, gbc);

        frame.add(formPanel, BorderLayout.CENTER);

        // Confirm button action
        confirmButton.addActionListener(e -> {
            String patientID = idField.getText().trim();
            String patientName = nameField.getText().trim();
            String phoneNumber = phoneField.getText().trim();
            String doctor = (String) doctorDropdown.getSelectedItem();

            int day = (Integer) daySpinner.getValue();
            int month = (Integer) monthSpinner.getValue();
            int year = (Integer) yearSpinner.getValue();
            int hour = (Integer) hourSpinner.getValue();
            int minute = (Integer) minuteSpinner.getValue();

            // Validation
            if (patientName.isEmpty() || !patientName.matches("[a-zA-Z ]+")) {
                JOptionPane.showMessageDialog(frame, "⚠️ Enter a valid Patient Name (only letters)!");
                return;
            }
            if (!phoneNumber.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(frame, "⚠️ Phone Number must be exactly 10 digits!");
                return;
            }

            String date = String.format("%02d-%02d-%04d", day, month, year);
            String time = String.format("%02d:%02d", hour, minute);

            JOptionPane.showMessageDialog(frame,
                    "✅ Appointment Confirmed!\n\n" +
                            "Patient ID: " + patientID + "\n" +
                            "Patient Name: " + patientName + "\n" +
                            "Phone: " + phoneNumber + "\n" +
                            "Doctor: " + doctor + "\n" +
                            "Date: " + date + "\n" +
                            "Time: " + time,
                    "Confirmation",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        // Reset button
        resetButton.addActionListener(e -> {
            idField.setText(generatePatientID());
            nameField.setText("");
            phoneField.setText("");
            doctorDropdown.setSelectedIndex(0);
            daySpinner.setValue(1);
            monthSpinner.setValue(1);
            yearSpinner.setValue(LocalDate.now().getYear());
            hourSpinner.setValue(9);
            minuteSpinner.setValue(0);
        });

        frame.setVisible(true);
    }

    private static String generatePatientID() {
        patientCounter++;
        return "P" + patientCounter;
    }

    public static void main(String[] args) {
        new HospitalAppointmentSystem();
    }
}
