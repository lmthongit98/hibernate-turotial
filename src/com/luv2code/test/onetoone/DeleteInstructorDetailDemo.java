package com.luv2code.test.onetoone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.entity.onetoone.Instructor;
import com.luv2code.entity.onetoone.InstructorDetail;

public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start a transaction
			session.beginTransaction();

			// get the instructor detail object
			int theId = 7;
			InstructorDetail instructorDetail = session.get(InstructorDetail.class, theId);
			
			// print the instructor detail
			System.out.println(instructorDetail);
			
			// print the associated instructor
			System.out.println(instructorDetail.getInstructor());
			
			// delete the instructor detail
			System.out.println("Deleting tempInstructorDetail: " + instructorDetail);
			
			// remove the associated object reference
			// break bi-directional link
			instructorDetail.getInstructor().setInstructorDetail(null);
			
			session.delete(instructorDetail);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// handle connection leak issue
			session.close();
			factory.close();
		}

	}

}
