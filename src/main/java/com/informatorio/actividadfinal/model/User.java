package com.informatorio.actividadfinal.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @NotBlank
	@Column(name = "first_name")
	@Size(max = 40)
	private String firstName;

	@NotBlank
	@Column(name = "last_name")
	@Size(max = 40)
    private String lastName;
    
    @NotBlank
	@Size(max = 40)
	@Column(name = "email", unique = true)
	@Email
    private String email;
    
    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Size(max = 100)
	@Column(name = "password")
    private String password;
    
    @Column(name="creation_date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public LocalDate creationDate;

    @Size(max=50)
    @Column(name="city")
    private String city;

    @Size(max=40)
    @Column(name="province")
    private String province;

    @Size(max=50)
    @Column(name="country")
    private String country;

    @JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Post> posts;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments;
    
    public User(String firstName, String lastName, String email, String password, LocalDate creationDate,
                String city, String country, String province) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
        this.password = password;
        this.creationDate = creationDate;
        this.city = city;
        this.country = country;
        this.province = province;
    }
    
    public User(){}
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getCity() {
        return city;
    }
    public String getCountry() {
        return country;
    }
    public String getEmail() {
        return email;
    }
    public String getProvince() {
        return province;
    }
    public LocalDate getCreationDate() {
        return creationDate;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
