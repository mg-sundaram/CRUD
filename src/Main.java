import java.util.*;
public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        DatabaseConnector dbConnector = new DatabaseConnector();
        Services services = new Services(dbConnector);

        boolean exit = false;
        while (!exit) {
            System.out.println("Teacher Management System");
            System.out.println("1. Show all teachers");
            System.out.println("2. Add a teacher");
            System.out.println("3. Filter teachers based on criteria");
            System.out.println("4. Search for a teacher");
            System.out.println("5. Update a teacher's record");
            System.out.println("6. Delete a teacher");
            System.out.println("7. Average Classes");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    services.showAllTeachers();
                    System.out.println("=========================================================");
                    break;
                case 2:
                    services.addTeacher(scanner);
                    System.out.println("=========================================================");
                    break;
                case 3:
                	System.out.println("1. Filter by Age");
                	System.out.println("2 Filter by Number of classes he/she taken");
                	System.out.println("Enter your choice : ");
                	int n =scanner.nextInt();
                	scanner.nextLine();
                	switch(n) {
	                	case 1:
	                		services.age(scanner);
	                        System.out.println("=========================================================");
	                		break;
	                	case 2:
	                		services.class_taken(scanner);
	                        System.out.println("=========================================================");
	                		break;
	                	}
                	break;
                case 4:
                	System.out.println("1. Search by Name: ");
                	System.out.println("2. Search by ID :");
                	int m = scanner.nextInt();
                	scanner.nextLine();
                	switch(m) {
                		case 1:
                			services.searchByName(scanner);
                            System.out.println("=========================================================");
                			break;
                		case 2:
                			services.searchById(scanner);
                            System.out.println("=========================================================");
                			break; 
                		default:
                            System.out.println("Invalid choice. Please enter a number.");
                            System.out.println("=========================================================");
                            break;
                	}	
                	break;
                case 5:
                	System.out.println("1. Update by Name: ");
                	System.out.println("2. Updaye by Id: ");
                	int k = scanner.nextInt();
                	scanner.nextLine();
                	switch(k) {
                		case 1:
                			services.updateteacherbyname(scanner);
                            System.out.println("=========================================================");
                			break;
                		case 2:
                			services.updateteacherbyid(scanner);
                            System.out.println("=========================================================");
                			break;
                		default:
                            System.out.println("Invalid choice. Please enter a number.");
                            System.out.println("=========================================================");
                            break;
                	}
                	break;
                case 6:
                	System.out.println("1. Delete by Name: ");
                	System.out.println("2. Delete by Id: ");
                	int l = scanner.nextInt();
                	scanner.nextLine();
                	switch(l) {
                		case 1:
                			services.deletebyname(scanner);
                            System.out.println("=========================================================");
                			break;
                		case 2:
                			services.deletebyid(scanner);
                            System.out.println("=========================================================");
                			break;
                		default:
                            System.out.println("Invalid choice. Please enter a number.");
                            break;
                	}
                	break;
                case 7:
                	services.displayAverageClasses();
                	break;
                case 8:
                    System.out.println("Thank you! Have a great day :-)!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                    System.out.println("=========================================================");
                    break;
            }
        }
        dbConnector.closeConnection();
        scanner.close();
    }
}
