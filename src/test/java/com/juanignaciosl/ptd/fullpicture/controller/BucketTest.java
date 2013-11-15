package com.juanignaciosl.ptd.fullpicture.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;

public class BucketTest {

	private Bucket bucket;
	
	@Before
	public void init() {
		bucket = new Bucket();
	}

	@Test
	public void alarmTest() {
		final EntityManager em = mockDayTemperature(10d);
		
		bucket.em = em;
		bucket.setTemperature(100L);
		
		assertTrue(bucket.isAlarm());
	}
	
	@Test
	public void notAlarmTest() {
		final EntityManager em = mockDayTemperature(10d);
		
		bucket.em = em;
		bucket.setTemperature(1L);
		
		assertFalse(bucket.isAlarm());
	}

	private EntityManager mockDayTemperature(double dayTemperature) {
		final TypedQuery query = mock(TypedQuery.class);
		when(query.getSingleResult()).thenReturn(dayTemperature);
		final EntityManager em = mock(EntityManager.class);
		when(em.createQuery(anyString(), any((Class.class)))).thenReturn(query);
		return em;
	}

}
