package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;


public class CreateCourseandStudentsDemo {

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
			
			
			//create a course
			Course tempCourse = new Course("English");
			
			
			session.save(tempCourse);
			
			//create students
			Student student1 = new Student("jashu", "muth", "jashu@muth.com");
			Student student2 = new Student("sai", "muth", "sai@muth.com");
			
			tempCourse.add(student1);
			tempCourse.add(student2);
			
			//save students
			System.out.println("saving course with students:");
			session.save(student1);
			session.save(student2);
			System.out.println("saved students: "+tempCourse.getStudents());
			
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
