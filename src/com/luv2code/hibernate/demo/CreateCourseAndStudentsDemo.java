package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentsDemo {

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


            Course tempCourse1 = new Course("Ait Guitar - The Ultimate Guide");


            System.out.println("Saving the course...");
            session.save(tempCourse1);
            System.out.println("Saved the course: " + tempCourse1);

            Student tempStudent1 = new Student("Jhon", "Doe", "jhon@luv2conde.com");
            Student tempStudent2 = new Student("Mary", "Public", "mary@luv2conde.com");

            tempCourse1.addStudent(tempStudent1);
            tempCourse1.addStudent(tempStudent2);

            System.out.println("Saving the students...");
            session.save(tempStudent1);
            session.save(tempStudent2);
            System.out.println("Saved students: " + tempCourse1.getStudents());
            session.getTransaction().commit();

            System.out.println("Done!");

        }
        finally {
            session.close();
            sessionFactory.close();
        }

    }
}
