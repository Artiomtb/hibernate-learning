package com.artiomtb.hibernate.model;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "users_hibernate")
public class UserDetails {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_generator")
    @SequenceGenerator(name = "id_seq_generator", sequenceName = "users_user_id_seq", allocationSize = 1)
    private int id;

    @Column(name = "user_name")
    private String name;

    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "addresses_hibernate", joinColumns = @JoinColumn(name = "user_id"))
    @SequenceGenerator(name = "address_id_generator", sequenceName = "addresses_address_id_seq", allocationSize = 1)
    @CollectionId(columns = {@Column(name = "address_id")}, generator = "address_id_generator", type = @Type(type = "long"))
    private Collection<Address> setOfAddress = new ArrayList<>();

    @Column(name = "join_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date = new Date();

    @Column(name = "description")
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Address> getSetOfAddress() {
        return setOfAddress;
    }

    public void setSetOfAddress(Collection<Address> setOfAddress) {
        this.setOfAddress = setOfAddress;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
