
package services.gym;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Gym;
import repositories.GymRepository;

@Service
@Transactional
public class GymService {

	@Autowired
	private GymRepository gymRepository;


	public Gym create() {
		return new Gym();
	}

	public Collection<Gym> findAll() {
		return this.gymRepository.findAll();
	}

	public Gym findOne(final int gymId) {
		return this.gymRepository.findOne(gymId);
	}

	public Gym save(final Gym gym) {
		return this.gymRepository.save(gym);
	}

	public void delete(final Gym gym) {
		this.gymRepository.delete(gym);
	}

	public Collection<Gym> findAvailableGyms() {
		return this.gymRepository.findAvailableGyms();
	}
}
