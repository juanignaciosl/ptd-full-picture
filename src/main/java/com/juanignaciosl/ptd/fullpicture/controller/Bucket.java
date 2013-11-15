package com.juanignaciosl.ptd.fullpicture.controller;

import java.util.Calendar;
import java.util.Date;

import javax.ejb.Stateful;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.time.DateUtils;

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

	public Double averageTemperature(Date date) {
		final TypedQuery<Double> averageDateTemperature = em
				.createQuery(
						"select avg(t.measuredTemperature) from Temperature t where t.date between :dateStart and :dateEnd",
						Double.class);
		averageDateTemperature.setParameter("dateStart", start(date), TemporalType.DATE);
		averageDateTemperature.setParameter("dateEnd", start(tomorrow(date)), TemporalType.DATE);
		final Double average = averageDateTemperature.getSingleResult();
		return average == null ? 0 : average;
	}

	private Date tomorrow(Date date) {
		return DateUtils.add(date, Calendar.DAY_OF_MONTH, 1);
	}

	private Date start(Date date) {
		return DateUtils.truncate(date, Calendar.DATE);
	}

}
