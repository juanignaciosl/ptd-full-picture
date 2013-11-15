package com.juanignaciosl.ptd.fullpicture.bucket;

import java.util.Date;

import javax.persistence.EntityManager;

import com.juanignaciosl.ptd.fullpicture.model.Temperature;

public class TemperatureTestDataBuilder {

	private EntityManager em;
	private Date date;
	private long temperature;

	public TemperatureTestDataBuilder(EntityManager em) {
		this.em = em;
	}

	public TemperatureTestDataBuilder withDate(Date date) {
		this.date = date;
		return this;
	}

	public TemperatureTestDataBuilder withTemperature(long temperature) {
		this.temperature = temperature;
		return this;
	}

	public void insert() {
		em.persist(new Temperature(date, temperature));
	}

}
