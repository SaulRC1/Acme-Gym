
package services.gym;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Client;
import domain.Gym;
import domain.Inscription;
import domain.Manager;
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

    /**
     * This method will return all clients that are or have been inscripted to a
     * certain gym.
     *
     * @param gym The gym to get all clients from
     * @return All the clients that are inscripted to the gym passed by parameter.
     */
    public Set<Client> getAllClientsFromGym(final Gym gym) {

	if (gym == null)
	    return null;

	final Collection<Inscription> gymInscriptions = gym.getInscriptions();

	final Iterator<Inscription> it = gymInscriptions.iterator();

	if (gymInscriptions.isEmpty())
	    return null;

	final Set<Client> clientsFromGym = new HashSet<Client>();

	while (it.hasNext()) {

	    final Inscription inscription = it.next();

	    clientsFromGym.add(inscription.getClient());
	}

	return clientsFromGym;
    }

    /**
     * This method will return the number of clients that are or have been
     * inscripted to a certain gym.
     *
     * @param gym The gym to get the number of clients from.
     * @return All the clients that are inscripted to the gym passed by parameter.
     */
    public int getNumberOfClientsFromGym(final Gym gym) {

	final Set<Client> allClientsFromGym = this.getAllClientsFromGym(gym);

	if (allClientsFromGym != null)
	    return allClientsFromGym.size();

	return 0;
    }

    public Collection<Gym> findActivesGyms() {
	return this.gymRepository.findActivesGyms();
    }

    public Gym findByName(final String name) {
	return this.gymRepository.findByName(name);
    }

    public Collection<Gym> findActivesGymByManager(Manager manager) {
	Collection<Gym> gymsManager = manager.getGyms();

	if (gymsManager == null || gymsManager.isEmpty())
	    return Collections.emptyList();

	Collection<Gym> activeGyms = new ArrayList<>();

	for (Gym gym : gymsManager)
	    if (gym.isActive())
		activeGyms.add(gym);

	return activeGyms;
    }

    public Collection<Gym> findInactivesGymByManager(Manager manager) {
	Collection<Gym> gymsManager = manager.getGyms();

	if (gymsManager == null || gymsManager.isEmpty())
	    return Collections.emptyList();

	Collection<Gym> inactiveGyms = new ArrayList<>();

	for (Gym gym : gymsManager)
	    if (!gym.isActive())
		inactiveGyms.add(gym);

	return inactiveGyms;
    }

}
