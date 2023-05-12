
package services;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Client;
import domain.Gym;
import domain.Inscription;
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

    /**
     * This method will get the number of gyms the client passed by parameter has
     * attended to.<br>
     * <br>
     *
     * Take into account that this method will only review distinct gyms, it will
     * not take into account more than one inscription that refers to same gym.
     *
     * @param client The client to check.
     */
    public int getNumberOfGymsInscripted(Client client) {

	Collection<Inscription> inscriptions = client.getInscriptions();

	if (inscriptions == null)
	    return 0;

	int numberOfDistinctGyms = 0;

	Iterator<Inscription> it = inscriptions.iterator();

	// Since we only want the number of actual different gyms
	// a Set is used, because it does not permit duplicate elements
	// to be added.
	Set<Gym> distinctGyms = new HashSet<Gym>();

	while (it.hasNext())
	    // For every inscription, the gym that inscription belongs to
	    // will be added.
	    distinctGyms.add(it.next().getGym());

	// Because the set contains the real number of gyms the client
	// is or has been inscripted to, since it does not admit duplicate
	// instances, we assign this to the returned value
	numberOfDistinctGyms = distinctGyms.size();

	return numberOfDistinctGyms;
    }
}
