package blog.thoughts.on.java.jpa21.business;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import blog.thoughts.on.java.jpa21.entity.Trip;

@Stateless
@LocalBean
public class TripBean {

	@PersistenceContext
	private EntityManager em;

	public Trip createTrip(Trip trip) {
		this.em.persist(trip);
		return trip;
	}

	public Trip getTrip(Integer id) {
		return this.em.find(Trip.class, id);
	}
}
