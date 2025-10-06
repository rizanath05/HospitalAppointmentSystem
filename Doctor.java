
package hospitalappointment.system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor {
    private Connection connection;

    // Constructor to initialize the database connection
    public Doctor(Connection connection) {
        this.connection = connection;
    }

    // Method to display all doctors in the database
    public void viewDoctors() {
        if (connection == null) {
            System.out.println(" Database connection not established!");
            return;
        }

        String query = "SELECT * FROM doctors";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("\n List of Doctors:");
            System.out.println("+------------+--------------------+------------------+");
            System.out.println("| Doctor ID  | Name               | Specialization   |");
            System.out.println("+------------+--------------------+------------------+");

            boolean hasDoctors = false;
            while (resultSet.next()) {
                hasDoctors = true;
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");
                System.out.printf("| %-10s | %-18s | %-16s |\n", id, name, specialization);
            }

            if (!hasDoctors) {
                System.out.println("|       No doctors found in the database.           |");
            }

            System.out.println("+------------+--------------------+------------------+");

            // Close resources
            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            System.out.println(" Error while fetching doctor data: " + e.getMessage());
        }
    }

    // Method to check if a doctor exists by ID
    public boolean getDoctorById(int id) {
        if (connection == null) {
            System.out.println(" Database connection not established!");
            return false;
        }

        String query = "SELECT * FROM doctors WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            boolean exists = resultSet.next();

            resultSet.close();
            preparedStatement.close();

            return exists;

        } catch (SQLException e) {
            System.out.println(" Error while searching for doctor: " + e.getMessage());
        }

        return false;
    }
}
