
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Client;
import domain.Gym;
import domain.Inscription;
import repositories.InscriptionRepository;

@Service
@Transactional
public class InscriptionService {

	@Autowired
	private InscriptionRepository inscriptionRepository;


	public Inscription create() {
		return new Inscription();
	}

	public Inscription findOne(int inscriptionId) {
		return this.inscriptionRepository.findOne(inscriptionId);
	}

	public Inscription save(Inscription inscription) {
		return this.inscriptionRepository.save(inscription);
	}

	public void delete(Inscription inscription) {
		this.inscriptionRepository.delete(inscription);
	}

	public Collection<Inscription> findAll() {
		return this.inscriptionRepository.findAll();
	}

	public Collection<Inscription> findByGymAndClient(Gym gym, Client client) {
		return this.inscriptionRepository.findByGymAndClient(gym, client);
	}
}
