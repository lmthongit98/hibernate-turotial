package com.luv2code.jdbc.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.jdbc.demo.entity.Instructor;
import com.luv2code.jdbc.demo.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// create the objects
			/*
			 * Instructor tempInstructor = new Instructor("Chad", "Darby",
			 * "darby@luv2code.com");
			 * 
			 * InstructorDetail tempInstructorDetail = new InstructorDetail(
			 * "http://www.luv2code.com/youtube", "Luv 2 code!!!");
			 */

			Instructor tempInstructor = new Instructor("Thong", "Ly", "lminhthong@gmail.com");

			InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube.com", "Code Java");

			// associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);

			// start a transaction
			session.beginTransaction();

			// save the instructor
			//
			// Note: this will ALSO save the details object
			// because of CascadeType.ALL
			//
			System.out.println("Saving instructor: " + tempInstructor);
			session.save(tempInstructor);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			factory.close();
		}

	}

}
