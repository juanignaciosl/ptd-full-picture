package com.juanignaciosl.ptd.fullpicture.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Temperature {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private Long measuredTemperature;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

}
