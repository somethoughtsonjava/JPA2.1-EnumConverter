package blog.thoughts.on.java.jpa21;

import javax.inject.Inject;

import junit.framework.Assert;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import blog.thoughts.on.java.jpa21.business.TripBean;
import blog.thoughts.on.java.jpa21.converter.VehicleConverter;
import blog.thoughts.on.java.jpa21.entity.Trip;
import blog.thoughts.on.java.jpa21.enums.Vehicle;

@RunWith(Arquillian.class)
public class TestTripBean {

	@Inject
	private TripBean tripBean;

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap
				.create(JavaArchive.class)
				.addClasses(Trip.class, VehicleConverter.class, TripBean.class,
						Vehicle.class)
				.addAsManifestResource("META-INF/persistence.xml",
						"persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	public void createSquare() {
		Trip trip = new Trip();
		trip.setDestination("Dresden");
		trip.setVehicle(Vehicle.CAR);

		this.tripBean.createTrip(trip);
		Assert.assertNotNull(trip.getId());
	}
}
