
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Client;
import domain.Gym;
import domain.Inscription;
import repositories.ClientRepository;
import repositories.GymRepository;
import repositories.InscriptionRepository;

@Service
@Transactional
public class InscriptionService {

	@Autowired
	private InscriptionRepository	inscriptionRepository;

	@Autowired
	private GymRepository			gymRepository;

	@Autowired
	private ClientRepository		clientRepository;


	public Inscription create() {
		return new Inscription();
	}

	public Inscription findOne(final int inscriptionId) {
		Assert.isTrue(inscriptionId != 0);

		Inscription result;

		result = this.inscriptionRepository.findOne(inscriptionId);
		Assert.notNull(result);

		return result;
	}

	public Inscription save(final Inscription inscription) {
		Assert.notNull(inscription);

		Inscription result;

		result = this.inscriptionRepository.save(inscription);

		return result;
	}

	public void delete(final Inscription inscription) {
		Assert.notNull(inscription);
		Assert.isTrue(inscription.getId() != 0);
		Assert.isTrue(this.inscriptionRepository.exists(inscription.getId()));

		this.inscriptionRepository.delete(inscription);
	}

	public Collection<Inscription> findAll() {
		Collection<Inscription> result;

		result = this.inscriptionRepository.findAll();
		Assert.notNull(result);

		return result;
	}
	
	public Inscription findLastInscription(Client client) {
	    	Inscription result;

	    	result = this.inscriptionRepository.findCurrentInscriptionByClient(client);

		return result;
	}

	public Collection<Inscription> findByGymAndClient(final Gym gym, final Client client) {
		Assert.notNull(gym);
		Assert.notNull(client);

		Assert.isTrue(this.gymRepository.exists(gym.getId()));
		Assert.isTrue(this.clientRepository.exists(client.getId()));

		Assert.isTrue(gym.getInscriptions().stream().anyMatch(inscripcion -> inscripcion.getClient().equals(client)));

		return this.inscriptionRepository.findByGymAndClient(gym, client);
	}
}
