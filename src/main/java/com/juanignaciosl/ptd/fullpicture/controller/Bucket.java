package com.juanignaciosl.ptd.fullpicture.controller;

import java.util.Date;

import javax.ejb.Stateful;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

@Stateful
@Model
@ApplicationScoped
public class Bucket {

	private Long temperature = 0L;

	public Long getTemperature() {
		return temperature;
	}

	public void setTemperature(Long temperature) {
		this.temperature = temperature;
	}

	@Inject
	private EntityManager em;

	public Long averageTemperature(Date today) {
		final Query averageDateTemperature = em
				.createQuery("select avg(t.measuredTemperature) from Temperature t where t.date = :date");
		averageDateTemperature.setParameter("date", today, TemporalType.DATE);
		final Long average = (Long) averageDateTemperature.getSingleResult();
		return average == null ? 0 : average;
	}

}
