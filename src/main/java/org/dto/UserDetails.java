package org.dto;

import javax.persistence.*;

/*Use the object address twice...A straight forward but large method*/
/*We can also assign a name property in Entity...If we do that hibernate will make table with that name*/
@Entity
public class UserDetails {
    @Id @GeneratedValue(strategy=GenerationType.AUTO)/*Four types of strategy are there*/
    private int userId;
    private String userName;
    @Embedded
    private Address address;
    @Embedded
    @AttributeOverrides({
         @AttributeOverride(name="street",column=@Column(name = "job_place")),
         @AttributeOverride(name="city",column=@Column(name = "job_city")),
         @AttributeOverride(name="location",column=@Column(name = "job_location"))
    })
    private Address office;
    /*
    * AttributeOverrides -> was needed because we had multiple field in the object.
    * AttributeOverride -> overrides the default field...In this case "rasta" and city..
    * */

    /* If we want we can use @Id @GeneratedValue(strategy=GenerationType.AUTO) here above get Method... All the annotations are*/
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getOffice() {
        return office;
    }

    public void setOffice(Address office) {
        this.office = office;
    }
}
