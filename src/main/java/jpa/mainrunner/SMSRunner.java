package jpa.mainrunner;

import java.util.List;
import java.util.Scanner;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.service.CourseService;
import jpa.service.StudentService;

public class SMSRunner {
	// runner fields
	private CourseService courseService;
	private StudentService studentService;
	private Student currentStudent;
	// runner constructor
	public SMSRunner() {
		courseService = new CourseService();
		studentService = new StudentService();
	}

	public static void main(String[] args) {
		SMSRunner sms = new SMSRunner();
		sms.menu();
	}
	
	// main menu function 
	public void menu() {
		System.out.println("Are you a:\n\n 1. Student\n 2. Quit");
		int input = getIntInput(1,2);
		switch(input) {
		case 1: 
			if(studentLogin()) {
				displayCourses();
				registerForCourse();
				System.out.println("Goodbye");
			} 
			break;
		case 2:
			System.out.println("Goodbye");
			break;		
		}	
	}

	// checks student credentials, if valid sets current student and returns true
	private boolean studentLogin() {
		boolean flag =false;
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter your credentials to log in\n\nEmail:");
		String email = sc.nextLine();
		System.out.println("Password:");
		String pass = sc.nextLine();
		 if(studentService.validateStudent(email, pass)) {
			 System.out.println("Credentials valid, you are logged in.");
			 currentStudent = studentService.getStudentByEmail(email);
			 flag=true;
		 }
		 else {
			 System.out.println("Credentials invalid, please try again");
			 menu();
		 }
		return flag;
	}
	// prints courses current student is registered for
	private void displayCourses() {
		System.out.println("\nHere are your current courses:");
		System.out.printf("%-5s %-25s %-20s %n","Id",	"Course Name:" ,"Instructor:");
		for(Course c : studentService.getStudentCourses(currentStudent.getsEmail())) {
			System.out.printf("%-5d %-25s %-20s %n", c.getcId(), c.getcName(), c.getcInstuctorName());
		}
		
	}
	// gives option to register, displays all courses and registers course if not already registered
	private void registerForCourse() {
		System.out.println("\n1. Register for Course \n2. Quit");
		int input = getIntInput(1,2);
		switch(input) {
		case 1:
			List<Course> courses = courseService.getAllCourses();
			System.out.printf("All Courses:%n%-5s %-25s %-20s %n","Id",	"Course Name:" ,"Instructor:");
			for(Course c : courses) {
				System.out.printf("%-5d %-25s %-20s %n", c.getcId(), c.getcName(), c.getcInstuctorName());
			}
			System.out.println("\nEnter the Id of the course you wish to register:");
			int cId = getIntInput(1, courses.size());
			studentService.registerStudentToCourse(currentStudent.getsEmail(), cId);
			displayCourses();
			break;
		case 2:
			
			break;
		}
	}
	// checks user input until it receives an int between min and max inclusive.  returns input int
	public int getIntInput(int min, int max) {
		Scanner sc = new Scanner(System.in);
		int input;
		do {
		    System.out.printf("\nPlease enter a digit from %d to %d:\n", min, max);
		    while (!sc.hasNextInt()) {
		        System.out.println("Wrong input! Enter a digit");
		        sc.next(); 
		    }
		    input = sc.nextInt();
		} while (input < min || input > max);
		return input;
	}
}
