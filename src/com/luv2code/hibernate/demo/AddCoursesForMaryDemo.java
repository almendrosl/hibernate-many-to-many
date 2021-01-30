package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCoursesForMaryDemo {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {

            session.beginTransaction();

            int theId = 2;

            Student mary = session.get(Student.class, theId);

            System.out.println("Loaded Student: " + mary);
            System.out.println("Courses from student: " + mary.getCourses());

            Course course1 = new Course("Rubik's Cube - How to Speed Cube");
            Course course2 = new Course("Atari 2600 - Game Development");

            course1.addStudent(mary);
            course2.addStudent(mary);

            System.out.println("Saving the courses...");

            session.save(course1);
            session.save(course2);


            session.getTransaction().commit();

            System.out.println("Done!");

        } finally {
            session.close();
            sessionFactory.close();
        }

    }
}
