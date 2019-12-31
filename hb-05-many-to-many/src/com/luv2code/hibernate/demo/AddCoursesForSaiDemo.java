package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;


public class AddCoursesForSaiDemo {

	public static void main(String[] args) {
		
		//create session factory ( created only once that produces all sessions )
		
		SessionFactory sessionFactory = new Configuration()
											.configure("hibernate.cfg.xml")
											.addAnnotatedClass(Instructor.class)
											.addAnnotatedClass(InstructorDetail.class)
											.addAnnotatedClass(Course.class)
											.addAnnotatedClass(Review.class)
											.addAnnotatedClass(Student.class)
											.buildSessionFactory();
		
		//create session
		
		Session session = sessionFactory.getCurrentSession();

		try {
			
			
			//begin the transaction 
			session.beginTransaction();
			
			
			//get student sai from db
			int id = 2;
			Student student = session.get(Student.class, id);
			System.out.println("student sai:"+student);
			System.out.println("Sai's courses"+student.getCourses());
			//create more courses for the above student
			
			Course course1 = new Course("Volleyball");
			Course course2 = new Course("Shuttle");
			
			//add student to courses
			course1.add(student);
			course2.add(student);
			
			//save courses
			System.out.println("Saving the courses:");
			session.save(course1);
			session.save(course2);

			
			//commit the transaction
			session.getTransaction().commit();
			
		} catch (Exception e) {
				e.printStackTrace();
		}
		finally {
			session.close();
			sessionFactory.close();
		}

	}

}
