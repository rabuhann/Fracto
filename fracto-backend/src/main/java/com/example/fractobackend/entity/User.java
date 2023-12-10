package com.example.fractobackend.entity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "users", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"id"}),
		@UniqueConstraint(columnNames = {"email"})
})
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Column(name="userName")
	private String username;
	@Column(name="userEmail")
	private String email;
	@Column(name="password")
	private String password;

	//	@OneToMany(targetEntity = Appointment.class,cascade = CascadeType.ALL)
//	@JoinColumn(name="user_id",referencedColumnName = "id")
	@OneToMany(fetch = FetchType.LAZY,mappedBy="user", cascade = CascadeType.ALL)
	private List<Appointment> appointments;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Set<Role> roles;

	public User() {

	}

	public Long getUserId() {
		return id;
	}
	public void setUserId(Long userId) {
		this.id = userId;
	}
	public String getUserName() {
		return username;
	}
	public void setUserName(String username) {
		this.username = username;
	}
	public String getUserEmail() {
		return email;
	}
	public void setUserEmail(String userEmail) {
		this.email = userEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", appointments=" + appointments + ", roles=" + roles + "]";
	}



}
