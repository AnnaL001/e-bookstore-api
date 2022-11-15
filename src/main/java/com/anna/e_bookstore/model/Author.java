package com.anna.e_bookstore.model;

import javax.persistence.*;

@Entity
public class Author {
  @Id
  @GeneratedValue
  private Long id;
  private String firstname;
  private String lastname;
  private String fullname;
  private String short_bio;
  private int reads;
  @OneToOne
  @JoinColumn(name = "socials_id", referencedColumnName = "id")
  private Socials socials;

  public Author() {}

  public Author(String firstname, String lastname, String fullname, String short_bio, int reads, Socials socials) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.fullname = fullname;
    this.short_bio = short_bio;
    this.reads = reads;
    this.socials = socials;
  }

  public Long getId() {
    return id;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public String getShort_bio() {
    return short_bio;
  }

  public void setShort_bio(String short_bio) {
    this.short_bio = short_bio;
  }

  public int getReads() {
    return reads;
  }

  public void setReads(int reads) {
    this.reads = reads;
  }

  public Socials getSocials() {
    return socials;
  }

  public void setSocials(Socials socials) {
    this.socials = socials;
  }
}
