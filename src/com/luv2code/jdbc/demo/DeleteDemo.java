package com.luv2code.jdbc.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.jdbc.demo.entity.Instructor;
import com.luv2code.jdbc.demo.entity.InstructorDetail;

public class DeleteDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start a transaction
			session.beginTransaction();

			// get instructor by primary key / id
			int theId = 2;
			Instructor tempInstructor = session.get(Instructor.class, theId);

			System.out.println("Found instructor: " + tempInstructor);

			// delete the instructors
			if (tempInstructor != null) {

				System.out.println("Deleting: " + tempInstructor);

				// Note: will ALSO delete associated "details" object
				// because of CascadeType.ALL
				//
				session.delete(tempInstructor);
			}

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
