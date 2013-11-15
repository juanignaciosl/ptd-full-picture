package com.juanignaciosl.ptd.fullpicture.bucket;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.apache.commons.lang.time.DateUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.juanignaciosl.ptd.fullpicture.controller.Bucket;
import com.juanignaciosl.ptd.fullpicture.model.Temperature;
import com.juanignaciosl.ptd.fullpicture.util.Resources;

@RunWith(Arquillian.class)
public class BucketWeekTemperatureTest {

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addClasses(Bucket.class, Temperature.class,
						TemperatureTestDataBuilder.class, DateUtils.class,
						Resources.class)
				.addAsResource("META-INF/persistence.xml",
						"META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Inject
	private Bucket bucket;

	private Date today;
	private Date tomorrow;

	@Inject
	private EntityManager em;

	@Inject
	UserTransaction utx;

	@Before
	public void init() {
		today = new Date();
		Calendar tomorrowCalendar = Calendar.getInstance();
		tomorrowCalendar.add(Calendar.DAY_OF_MONTH, 1);

		tomorrow = tomorrowCalendar.getTime();
	}

	@Test
	public void readAverageTemperatureZero() {
		assertThat(bucket.averageTemperature(today), is(equalTo(0d)));
	}

	@Test
	public void readAverageTemperature() throws NotSupportedException,
			SystemException, SecurityException, IllegalStateException,
			RollbackException, HeuristicMixedException,
			HeuristicRollbackException {
		utx.begin();
		em.joinTransaction();

		final TemperatureTestDataBuilder builder = new TemperatureTestDataBuilder(
				em).withDate(tomorrow);
		builder.withTemperature(4L).insert();
		builder.withTemperature(6L).insert();

		utx.commit();
		em.clear();

		assertThat(bucket.averageTemperature(tomorrow), is(equalTo(5d)));
	}

	@Test
	public void readWeekTemperatures() {
		assertThat(
				bucket.weekTemperatures(),
				is(equalTo(Arrays.asList(new Double[] { 0d, 0d, 0d, 0d, 0d, 0d,
						0d, }))));
	}

}
