package jpa.service;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jpa.dao.CourseDAO;
//import jpa.dao.SMSAbstractDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

public class CourseService implements CourseDAO {
	// db connection
	final SessionFactory sessionFactory = new Configuration()
			.configure("hibernate-1.cfg.xml")
			.addAnnotatedClass(Course.class)
			.buildSessionFactory();
	final Session session = sessionFactory.openSession();
	
	// returns all courses in db
	public List<Course> getAllCourses() {
		Query query = session.createQuery("From Course");
		List<Course> list = query.getResultList();
		session.close();
		return list;
	}
}
