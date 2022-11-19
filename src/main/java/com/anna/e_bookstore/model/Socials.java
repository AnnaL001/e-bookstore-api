package com.anna.e_bookstore.model;

import javax.persistence.*;

@Entity
@Table(name = "socials")
public class Socials {
  @Id
  @GeneratedValue
  private Long id;
  private String instagram;
  private String facebook;
  private String twitter;

  public Socials() {}

  public Socials(String instagram, String facebook, String twitter) {
    this.instagram = instagram;
    this.facebook = facebook;
    this.twitter = twitter;
  }

  public Long getId() {
    return id;
  }

  public String getInstagram() {
    return instagram;
  }

  public void setInstagram(String instagram) {
    this.instagram = instagram;
  }

  public String getFacebook() {
    return facebook;
  }

  public void setFacebook(String facebook) {
    this.facebook = facebook;
  }

  public String getTwitter() {
    return twitter;
  }

  public void setTwitter(String twitter) {
    this.twitter = twitter;
  }
}
