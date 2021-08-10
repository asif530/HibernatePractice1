package org.controller;

import org.dto.Address;
import org.dto.UserDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateTest {
    public static void main(String[] args) {

        UserDetails userDetails = new UserDetails();
        Address address = new Address();
        Address office = new Address();

//        userDetails.setUserId(3); //Uncomment it to get a NullPointer exception
        userDetails.setUserName("First User");

        address.setStreet("kafrul");
        address.setCity("Dhaka");

        office.setStreet("Jasimuddin");
        office.setLocation("Uttara");

        userDetails.setAddress(address);
        /*We will include office name here. That will be an entity inside of UserDetails entity*/
        userDetails.setOffice(office);

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(userDetails);
        session.getTransaction().commit();

        //Session returns an object..So we have to cast it to appropriate class
        //get(String,Serializable) means get(className to map, primaryKey)
        userDetails = (UserDetails)session.get(UserDetails.class,1);
        /*We will get NullPointerException because even though we set 3 in userId hibernate set userId as 1.
        * Why??? Because we used strategy and it let hibernate the power to generate a userId for the entry
        * */

        System.out.println(userDetails.getUserName()+" lives in "
                          +userDetails.getAddress().getCity()+" on "
                          +userDetails.getAddress().getStreet()+" road!!!");
        System.out.println(userDetails.getUserName()+" works in "
                +userDetails.getOffice().getLocation()+" office which is beside "
                +userDetails.getOffice().getStreet()+" road!!!");

        session.close();
    }
}
