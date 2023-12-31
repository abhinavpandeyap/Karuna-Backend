package com.app.karuna.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "item")
public class Item extends BaseEntity {

	@NotNull
	@Enumerated(EnumType.STRING)
	private Category category;

	@Column(length = 500)
	private String description;

	@NotNull
	private String quantity;

	@Column(name = "listed_date_time")
	private LocalDateTime listedDateTime;

	@PrePersist
	public void prePersist() {
		listedDateTime = LocalDateTime.now();
	}

	@Column(name = "accepted_date_time")
	private LocalDateTime acceptedDateTime;

	@Column(name = "status", columnDefinition = "BIT default b'0'")
	private boolean status;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "donor_id")
	private Donor donor;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "receiver_id")
	private Receiver receiver;

	@ManyToOne
	@JoinColumn(name = "campaign_id")
	private Campaign campaign;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "delivery_id")
	private Delivery delivery;

	@OneToOne(mappedBy = "item")
	private Request request;

}
