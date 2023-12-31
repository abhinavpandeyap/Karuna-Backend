package com.app.karuna.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="staff")
public class Staff extends BaseEntity {
	
	@Column(length=100)
	private String name;
	
	@NotBlank(message = "Email can't be blank!")
	@Email(message = "Invalid Email Format")
	@Length(min = 5,max=40,message = "Invalid Email length!!!!!!!")
	private String email;

	
	@NotBlank(message = "Password can't be blank!")
	@Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})",message = "Blank or Invalid Password!!!!")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	@NotNull(message = "Phone can't be blank!")
	@Column(length=13)
	private Long phone;
	
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Role role;
	
	
	@NotBlank(message = "Address can't be blank!")
	private String address;
	
	@NotNull
	private Location location;
	
	@Column(name = "status", columnDefinition = "BIT default b'0'")
	private boolean status;
	
	@OneToMany(mappedBy = "staff")
	@JsonIgnore
	@ToString.Exclude
	private List<Request> requests;
	
	@OneToMany(mappedBy = "staff")
	@ToString.Exclude
	private List<Campaign> campaigns;
	
	@OneToMany(mappedBy = "staff")
	@ToString.Exclude
	private List<Delivery> deliveries;
	
}
