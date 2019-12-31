package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;


public class DeleteEnglishCourse {

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
			int id = 10;
			Course course = session.get(Course.class,10);
			
			System.out.println("deleting course:"+course);
			
			session.delete(course);

			
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
