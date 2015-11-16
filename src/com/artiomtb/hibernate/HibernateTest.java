package com.artiomtb.hibernate;

import com.artiomtb.hibernate.model.Address;
import com.artiomtb.hibernate.model.UserDetails;
import com.artiomtb.hibernate.model.Vehicle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

public class HibernateTest {

    public static void main(String[] args) {
        try {
            UserDetails user1 = new UserDetails();
            Address homeAddress1 = new Address();
            homeAddress1.setCity("Prague");
            homeAddress1.setState("Chech");
            homeAddress1.setStreet("Central");
            homeAddress1.setPinCode("test");
            Address officeAddress1 = new Address();
            officeAddress1.setStreet("Central");
            officeAddress1.setCity("Prague");
            officeAddress1.setState("Chech");
            officeAddress1.setPinCode("test_office");

            user1.setName("First user");

            Collection<Address> set = new ArrayList<Address>() {{
                add(homeAddress1);
                add(officeAddress1);
            }};

            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleName("BMW");

            user1.setSetOfAddress(set);
            user1.setVehicle(vehicle);

            Configuration configuration = new Configuration();
            Properties dbProperties = new Properties();
            dbProperties.load(new FileInputStream("jdbc.properties"));
            configuration.configure().addProperties(dbProperties);

            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
                    configuration.getProperties()).buildServiceRegistry();
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(user1);
            session.save(vehicle);
            transaction.commit();
            UserDetails user = (UserDetails) session.get(UserDetails.class, 1);
            if (user != null) {
                session.close();
                System.out.println(user.getSetOfAddress().size());

                System.out.println(user.getName());
            } else {
                System.out.println("User is not exists");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
