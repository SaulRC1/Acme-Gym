
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Client;
import repositories.ClientRepository;

@Service
@Transactional
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;


	public Client create() {
		return new Client();
	}

	public Collection<Client> findAll() {
		return this.clientRepository.findAll();
	}

	public Client findOne(final int clientId) {
		return this.clientRepository.findOne(clientId);
	}

	public Client save(final Client client) {
		return this.clientRepository.save(client);
	}

	public void delete(final Client client) {
		this.clientRepository.delete(client);
	}
}
