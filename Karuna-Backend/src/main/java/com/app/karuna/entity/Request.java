package com.app.karuna.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
@Table(name="request")
public class Request extends BaseEntity {

	@Column(length=500)
	private String description;
	
	@ManyToOne
	@JoinColumn(name="staff_id")
	private Staff staff;
	
	@ManyToOne
	@JoinColumn(name="receiver_id")
	private Receiver receiver;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
	private Item item;
	
	@Column(name="raised_date_time")
	private LocalDateTime raisedDateTime;
	
	@PrePersist
    public void prePersist() {
        raisedDateTime = LocalDateTime.now(); 
    }
	
	@Column(name="handled_date_time")
	private LocalDateTime handledDateTime;
	
	@Column(name = "status", columnDefinition = "BIT default b'0'")
	private boolean status;
	
}
