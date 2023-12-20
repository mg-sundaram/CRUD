import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Services {
    private final DatabaseConnector dbConnector;

    public Services(DatabaseConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public void showAllTeachers() {
        try {
            Connection connection = dbConnector.getConnection();
            String query = "SELECT * FROM teachers";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Process and display each teacher's information from the ResultSet
                System.out.println("Teacher ID: " + resultSet.getInt("teacher_id"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Age: " + resultSet.getInt("age"));
                System.out.println("Date of Birth: " + resultSet.getDate("dob"));
                System.out.println("Number of classes: " + resultSet.getInt("num_classes"));
                System.out.println("Average Class Taken: " + resultSet.getDouble("average_class"));
                // Display other relevant information
                System.out.println();
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     public void addTeacher(Scanner scanner) {
        try {
            Connection connection = dbConnector.getConnection();

            System.out.println("Enter teacher's full name:");
            String fullName = scanner.nextLine();

            System.out.println("Enter teacher's age:");
            int age = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.println("Enter teacher's date of birth (YYYY-MM-DD):");
            String dobString = scanner.nextLine();
            Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(dobString);

            System.out.println("Enter the number of classes the teacher teaches:");
            int numClasses = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            String query = "INSERT INTO teachers (name, age, dob, num_classes) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, fullName);
            preparedStatement.setInt(2, age);
            preparedStatement.setDate(3, new java.sql.Date(dob.getTime()));
            preparedStatement.setInt(4, numClasses);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Teacher added successfully!");
            } else {
                System.out.println("Failed to add teacher.");
            }

            preparedStatement.close();
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
    }
    public void age(Scanner scanner) {
        try {
            Connection connection = dbConnector.getConnection();
            System.out.println("Enter Age: ");
            int age = scanner.nextInt();

            String query = "SELECT * FROM teachers WHERE age = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, age);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                // If result set is empty, no records were found
                System.out.println("No records found for the entered age. Please enter a valid age.");
            } else {
                while (resultSet.next()) {
                    // Process and display each teacher's information from the ResultSet
                    System.out.println("Teacher ID: " + resultSet.getInt("teacher_id"));
                    System.out.println("Name: " + resultSet.getString("name"));
                    System.out.println("Age: " + resultSet.getInt("age"));
                    System.out.println("Date of Birth: " + resultSet.getDate("dob"));
                    System.out.println("Number of classes: " + resultSet.getInt("num_classes"));
                    // Display other relevant information
                    System.out.println();
                }
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void class_taken(Scanner scanner) {
        try {
            Connection connection = dbConnector.getConnection();
            System.out.println("Enter classes taken: ");
            int classesTaken = scanner.nextInt();

            String query = "SELECT * FROM teachers WHERE num_classes = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, classesTaken);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                // If result set is empty, no records were found
                System.out.println("No records found for the entered number of classes. Please enter a valid number.");
            } else {
                while (resultSet.next()) {
                    // Process and display each teacher's information from the ResultSet
                    System.out.println("Teacher ID: " + resultSet.getInt("teacher_id"));
                    System.out.println("Name: " + resultSet.getString("name"));
                    System.out.println("Age: " + resultSet.getInt("age"));
                    System.out.println("Date of Birth: " + resultSet.getDate("dob"));
                    System.out.println("Number of classes: " + resultSet.getInt("num_classes"));
                    // Display other relevant information
                    System.out.println();
                }
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchById(Scanner scanner) {
        try {
            Connection connection = dbConnector.getConnection();
            System.out.println("Enter valid teacher's ID: ");
            int id = scanner.nextInt();

            String query = "SELECT * FROM teachers WHERE teacher_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                // If result set is empty, no records were found
                System.out.println("No records found for the entered ID. Please enter a valid ID.");
            } else {
                while (resultSet.next()) {
                    // Process and display each teacher's information from the ResultSet
                    System.out.println("Teacher ID: " + resultSet.getInt("teacher_id"));
                    System.out.println("Name: " + resultSet.getString("name"));
                    System.out.println("Age: " + resultSet.getInt("age"));
                    System.out.println("Date of Birth: " + resultSet.getDate("dob"));
                    System.out.println("Number of classes: " + resultSet.getInt("num_classes"));
                    // Display other relevant information
                    System.out.println();
                }
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchByName(Scanner scanner) {
        try {
            Connection connection = dbConnector.getConnection();
            System.out.println("Enter Teacher's name: ");
            String name = scanner.nextLine();

            String query = "SELECT * FROM teachers WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                // If result set is empty, no records were found
                System.out.println("No records found for the entered name. Please enter a valid name.");
            } else {
                while (resultSet.next()) {
                    // Process and display each teacher's information from the ResultSet
                    System.out.println("Teacher ID: " + resultSet.getInt("teacher_id"));
                    System.out.println("Name: " + resultSet.getString("name"));
                    System.out.println("Age: " + resultSet.getInt("age"));
                    System.out.println("Date of Birth: " + resultSet.getDate("dob"));
                    System.out.println("Number of classes: " + resultSet.getInt("num_classes"));
                    // Display other relevant information
                    System.out.println();
                }
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateteacherbyname(Scanner scanner) {
        try {
            Connection connection = dbConnector.getConnection();
            System.out.println("Enter Teacher's name to update: ");
            String name = scanner.nextLine();

            String query = "SELECT * FROM teachers WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("No records found for the entered name. Please enter a valid name.");
            } else {
                while (resultSet.next()) {
                    int teacherId = resultSet.getInt("teacher_id");

                    // Display the existing details for reference
                    System.out.println("Current Details:");
                    System.out.println("Teacher ID: " + resultSet.getInt("teacher_id"));
                    System.out.println("Name: " + resultSet.getString("name"));
                    System.out.println("Age: " + resultSet.getInt("age"));
                    System.out.println("Date of Birth: " + resultSet.getDate("dob"));
                    System.out.println("Number of classes: " + resultSet.getInt("num_classes"));
                    System.out.println();

                    // Prompt user for updated values
                    System.out.println("Enter new age: ");
                    int newAge = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.println("Enter new date of birth (YYYY-MM-DD): ");
                    String newDOB = scanner.nextLine();

                    // Update query
                    String updateQuery = "UPDATE teachers SET age = ?, dob = ? WHERE teacher_id = ?";
                    PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                    updateStatement.setInt(1, newAge);
                    updateStatement.setString(2, newDOB);
                    updateStatement.setInt(3, teacherId);

                    int rowsAffected = updateStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Teacher's details updated successfully!");

                        // Show updated details
                        String updatedQuery = "SELECT * FROM teachers WHERE teacher_id = ?";
                        PreparedStatement updatedStatement = connection.prepareStatement(updatedQuery);
                        updatedStatement.setInt(1, teacherId);
                        ResultSet updatedResult = updatedStatement.executeQuery();

                        if (updatedResult.next()) {
                            System.out.println("Updated Details:");
                            System.out.println("Teacher ID: " + updatedResult.getInt("teacher_id"));
                            System.out.println("Name: " + updatedResult.getString("name"));
                            System.out.println("Age: " + updatedResult.getInt("age"));
                            System.out.println("Date of Birth: " + updatedResult.getDate("dob"));
                            System.out.println("Number of classes: " + updatedResult.getInt("num_classes"));
                        } else {
                            System.out.println("Failed to retrieve updated details.");
                        }

                        updatedResult.close();
                        updatedStatement.close();
                    } else {
                        System.out.println("Failed to update teacher's details.");
                    }

                    updateStatement.close();
                }
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateteacherbyid(Scanner scanner) {
        try {
            Connection connection = dbConnector.getConnection();
            System.out.println("Enter Teacher's ID to update: ");
            int teacherId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            String query = "SELECT * FROM teachers WHERE teacher_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, teacherId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("No records found for the entered ID. Please enter a valid ID.");
            } else {
                while (resultSet.next()) {
                    // Display the existing details for reference
                    System.out.println("Current Details:");
                    System.out.println("Teacher ID: " + resultSet.getInt("teacher_id"));
                    System.out.println("Name: " + resultSet.getString("name"));
                    System.out.println("Age: " + resultSet.getInt("age"));
                    System.out.println("Date of Birth: " + resultSet.getDate("dob"));
                    System.out.println("Number of classes: " + resultSet.getInt("num_classes"));
                    System.out.println();

                    // Prompt user for updated values
                    System.out.println("Enter new age: ");
                    int newAge = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.println("Enter new date of birth (YYYY-MM-DD): ");
                    String newDOB = scanner.nextLine();

                    // Update query
                    String updateQuery = "UPDATE teachers SET age = ?, dob = ? WHERE teacher_id = ?";
                    PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                    updateStatement.setInt(1, newAge);
                    updateStatement.setString(2, newDOB);
                    updateStatement.setInt(3, teacherId);

                    int rowsAffected = updateStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Teacher's details updated successfully!");

                        // Show updated details
                        String updatedQuery = "SELECT * FROM teachers WHERE teacher_id = ?";
                        PreparedStatement updatedStatement = connection.prepareStatement(updatedQuery);
                        updatedStatement.setInt(1, teacherId);
                        ResultSet updatedResult = updatedStatement.executeQuery();

                        if (updatedResult.next()) {
                            System.out.println("Updated Details:");
                            System.out.println("Teacher ID: " + updatedResult.getInt("teacher_id"));
                            System.out.println("Name: " + updatedResult.getString("name"));
                            System.out.println("Age: " + updatedResult.getInt("age"));
                            System.out.println("Date of Birth: " + updatedResult.getDate("dob"));
                            System.out.println("Number of classes: " + updatedResult.getInt("num_classes"));
                        } else {
                            System.out.println("Failed to retrieve updated details.");
                        }

                        updatedResult.close();
                        updatedStatement.close();
                    } else {
                        System.out.println("Failed to update teacher's details.");
                    }

                    updateStatement.close();
                }
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     public void deletebyname(Scanner scanner) {
        try {
            Connection connection = dbConnector.getConnection();
            System.out.println("Enter Teacher's name to delete: ");
            String name = scanner.nextLine();

            String query = "SELECT * FROM teachers WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("No records found for the entered name. Please enter a valid name.");
            } else {
                while (resultSet.next()) {
                    int teacherId = resultSet.getInt("teacher_id");

                    // Display the existing details for confirmation
                    System.out.println("Teacher ID: " + resultSet.getInt("teacher_id"));
                    System.out.println("Name: " + resultSet.getString("name"));
                    System.out.println("Age: " + resultSet.getInt("age"));
                    System.out.println("Date of Birth: " + resultSet.getDate("dob"));
                    System.out.println("Number of classes: " + resultSet.getInt("num_classes"));
                    System.out.println();

                    // Confirmation message
                    System.out.println("Are you sure you want to delete this teacher? (Y/N)");
                    String confirmDelete = scanner.nextLine();

                    if (confirmDelete.equalsIgnoreCase("Y")) {
                        // Another confirmation
                        System.out.println("Please confirm the deletion by typing 'DELETE': ");
                        String confirmText = scanner.nextLine();

                        if (confirmText.equalsIgnoreCase("DELETE")) {
                            // Delete query
                            String deleteQuery = "DELETE FROM teachers WHERE teacher_id = ?";
                            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
                            deleteStatement.setInt(1, teacherId);

                            int rowsAffected = deleteStatement.executeUpdate();

                            if (rowsAffected > 0) {
                                System.out.println("Teacher's details deleted successfully!");
                            } else {
                                System.out.println("Failed to delete teacher's details.");
                            }

                            deleteStatement.close();
                        } else {
                            System.out.println("Deletion canceled. Incorrect confirmation text.");
                        }
                    } else {
                        System.out.println("Deletion canceled.");
                    }
                }
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deletebyid(Scanner scanner) {
        try {
            Connection connection = dbConnector.getConnection();
            System.out.println("Enter Teacher's ID to delete: ");
            int teacherId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            String query = "SELECT * FROM teachers WHERE teacher_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, teacherId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("No records found for the entered ID. Please enter a valid ID.");
            } else {
                while (resultSet.next()) {
                    // Display the existing details for confirmation
                    System.out.println("Teacher ID: " + resultSet.getInt("teacher_id"));
                    System.out.println("Name: " + resultSet.getString("name"));
                    System.out.println("Age: " + resultSet.getInt("age"));
                    System.out.println("Date of Birth: " + resultSet.getDate("dob"));
                    System.out.println("Number of classes: " + resultSet.getInt("num_classes"));
                    System.out.println();

                    // Confirmation message
                    System.out.println("Are you sure you want to delete this teacher? (Y/N)");
                    String confirmDelete = scanner.nextLine();

                    if (confirmDelete.equalsIgnoreCase("Y")) {
                        // Another confirmation
                        System.out.println("Please confirm the deletion by typing 'DELETE': ");
                        String confirmText = scanner.nextLine();

                        if (confirmText.equalsIgnoreCase("DELETE")) {
                            // Delete query
                            String deleteQuery = "DELETE FROM teachers WHERE teacher_id = ?";
                            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
                            deleteStatement.setInt(1, teacherId);

                            int rowsAffected = deleteStatement.executeUpdate();

                            if (rowsAffected > 0) {
                                System.out.println("Teacher's details deleted successfully!");
                            } else {
                                System.out.println("Failed to delete teacher's details.");
                            }

                            deleteStatement.close();
                        } else {
                            System.out.println("Deletion canceled. Incorrect confirmation text.");
                        }
                    } else {
                        System.out.println("Deletion canceled.");
                    }
                }
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     public void displayAverageClasses() {
        try {
            Connection connection = dbConnector.getConnection();
            String query = "SELECT AVG(num_classes) AS average_classes FROM teachers";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                double averageClasses = resultSet.getDouble("average_classes");
                System.out.println("Average number of classes taken by teachers: " + averageClasses);
            } else {
                System.out.println("No records found.");
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
