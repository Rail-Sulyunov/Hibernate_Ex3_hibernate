package hibernate_one_to_one;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test1 {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Detail.class)
                .buildSessionFactory();

        Session session = null;
        try {
            session = factory.getCurrentSession();
            Employee employee = new Employee("Rail", "Sulyunov"
                    , "IT", 500);
            Detail detail = new Detail("Ylyanovsk", "12345"
                    , "Sulyunov@mail.com");

            employee.setEmpDetail(detail);
            session.beginTransaction();

            session.save(employee);

            session.getTransaction().commit();
            System.out.println("**********************************");

            session = factory.getCurrentSession();

            session.beginTransaction();
            Employee emp = session.get(Employee.class, 1);
            System.out.println(emp.getEmpDetail());


            session.getTransaction().commit();

        } finally {
            session.close();
            factory.close();
        }

    }
}
