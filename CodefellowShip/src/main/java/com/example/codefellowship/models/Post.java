package com.example.codefellowship.models;



import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String body;
    @CreationTimestamp
    private Date createdAt;

    @ManyToOne
    ApplicationUser applicationUser;

    public Post() {
    }

    public Post(String body, ApplicationUser applicationUser) {
        this.body = body;
        this.applicationUser = applicationUser;
    }

    public long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

//    public ApplicationUser getApplicationUser() {
//        return applicationUser;
//    }

    public void setBody(String body) {
        this.body = body;
    }
}
