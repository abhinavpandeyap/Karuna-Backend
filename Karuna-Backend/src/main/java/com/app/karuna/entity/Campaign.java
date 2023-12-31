package com.app.karuna.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.builder.ToStringExclude;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name="campaign")
public class Campaign extends BaseEntity {
	
	@NotBlank
	@Column(length=100)
	private String name;
	
	@NotBlank
	@Column(length = 500)
	private String description;
	
	@OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL)
	@JsonIgnore
	@ToString.Exclude
    private List<Item> items;
	
	@ManyToMany(mappedBy = "campaigns")
	@JsonIgnore
	@ToString.Exclude
	private List<Donor> donors;
	
	@ManyToOne
	@JoinColumn(name="staff_id")
	@JsonIgnore
	private Staff staff;
	
	@Column(name = "status", columnDefinition = "BIT default b'1'")
	private boolean status;

}
