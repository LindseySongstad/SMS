package jpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

public class StudentService implements StudentDAO {
	// db connection
		final SessionFactory sessionFactory = new Configuration()
				.configure("hibernate-1.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		final Session session = sessionFactory.openSession();
	
	// returns a list of all students currently in the database
	public List<Student> getAllStudents() {
		Query query = session.createQuery("From Student");
		List<Student> list  =  query.getResultList();
		return list;
	}
	
	// returns single student by email
	public Student getStudentByEmail(String sEmail) {
		Query query = session.createQuery("From Student s Where s.sEmail = :sEmail");
		query.setParameter("sEmail", sEmail);
		Student s = (Student)query.getSingleResult();
		return s;
	}
	
	// returns true or false if email password combo are in current student list
	public boolean validateStudent(String sEmail, String sPassword) {
		boolean flag = false;
		List<Student> list = getAllStudents();
		for(Student s: list) {
			if(s.getsEmail().equals(sEmail) && s.getsPass().equals(sPassword)) {
				flag = true;
			}
		}
		return flag;
	}
	// takes in email and course id, registers corresponding student to corresponding course if not already
	public void registerStudentToCourse(String sEmail, int cId) {
		Transaction t = null;
		try {
			t = session.beginTransaction();
			Course tempCourse = getCourseById(cId);
			Student tempStudent = getStudentByEmail(sEmail);
			Set courses = tempStudent.getsCourses();
			if(courses.contains(tempCourse)) {
				System.out.println("You are already registered in that course!");
			} else {
				courses.add(tempCourse);
				tempStudent.setsCourses(courses);
				session.merge(tempStudent);
				t.commit();
				System.out.println("You have registered for the course: "+ tempCourse.getcName());
			}
		} catch (HibernateException e) {
		        if (t!=null) t.rollback();
		        e.printStackTrace(); 
		}
	}
	//  returns list of courses for student with input email
	public List<Course> getStudentCourses(String sEmail) {
		Student s = getStudentByEmail(sEmail);
		Set courseSet = s.getsCourses();
		List<Course> studentCourses= new ArrayList<Course>(courseSet);
		return studentCourses;
	}
	// returns course with input id
	public Course getCourseById(int cId) {
		Query query = session.createQuery("From Course c Where c.cId = :cId");
		query.setParameter("cId", cId);
		Course c = (Course)query.getSingleResult();
		return c;
	}
}
