package com.juanignaciosl.ptd.fullpicture.bucket;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import javax.inject.Inject;

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
				.addClasses(Bucket.class, Temperature.class, Resources.class)
				.addAsResource("META-INF/persistence.xml",
						"META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Inject
	private Bucket bucket;

	private Date today;

	@Before
	public void init() {
		today = new Date();
	}

	@Test
	public void readAverageTemperature() {
		assertThat(bucket.averageTemperature(today), is(equalTo(0L)));
	}

}
