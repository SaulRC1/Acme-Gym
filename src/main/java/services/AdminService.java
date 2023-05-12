package services;

import java.util.Collection;
import java.util.Iterator;

import javax.transaction.Transactional;

import org.apache.commons.lang.NullArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Admin;
import domain.Client;
import domain.Gym;
import domain.Manager;
import domain.Training;
import repositories.AdminRepository;

@Service
@Transactional
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private ClientService clientService;

    /**
     * Will create an Admin with no data.
     *
     * @return An empty Admin instance.
     */
    public Admin create() {
	return new Admin();
    }

    /**
     * This method will find all admins stored inside the database.
     *
     * @return All Admins stored inside the database.
     */
    public Collection<Admin> findAll() {
	return this.adminRepository.findAll();
    }

    /**
     * This method will find an admin by his id from database
     *
     * @param adminId The admin ID from database
     * @return An admin, or null if it does not find anything.
     */
    public Admin findOne(int adminId) {
	return this.adminRepository.findOne(adminId);
    }

    /**
     * This method will find an admin by his email.
     *
     * @param email The email related to the admin
     * @return The Admin object, or null in case this email is not registered.
     */
    public Admin findByEmail(String email) {
	return this.adminRepository.getByEmail(email);
    }

    /**
     * This method will save an admin inside database
     *
     * @param admin The admin to be saved
     * @return The saved admin
     */
    public Admin save(Admin admin) throws Exception {
	if (admin == null)
	    throw new NullArgumentException("admin");

	return this.adminRepository.save(admin);
    }

    /**
     * This method will delete an admin from the database.
     *
     * @param admin The admin to be deleted
     */
    public void delete(Admin admin) {
	this.adminRepository.delete(admin);
    }

    /**
     * Will ban a manager
     *
     * @param manager The manager to be banned
     */
    public void banManager(Manager manager) {

	if (manager != null) {

	    manager.setBanned(true);

	    this.managerService.save(manager);
	}
    }

    /**
     * Will unban a manager
     *
     * @param manager
     */
    public void unbanManager(Manager manager) {

	if (manager != null) {

	    manager.setBanned(false);

	    this.managerService.save(manager);
	}
    }

    /**
     * This method will return the minimum number of gyms that a manager is in
     * charge of.<br>
     * <br>
     *
     * By querying all managers stored inside the database, this method checks how
     * many gyms each manager is in charge of, returning the minimum number of gyms
     * per manager.
     *
     * @return The minimum number of gyms per manager
     */
    public int getMinimumNumberOfGymsPerManager() {

	Collection<Manager> managers = this.managerService.findAll();

	if (managers.isEmpty())
	    return 0;

	// Get an iterator to iterate over this collection of managers
	Iterator<Manager> it = managers.iterator();

	// Because the minimum number of gyms is wanted, we first initialize
	// our minimum to the maximum possible integer value. Hence, when
	// the first manager is checked, no matter how low the value is, it
	// will establish itself as the minimum value.
	int minimumNumberOfGyms = Integer.MAX_VALUE;

	while (it.hasNext()) {

	    Manager manager = it.next();

	    if (manager.getGyms().size() < minimumNumberOfGyms)
		minimumNumberOfGyms = manager.getGyms().size();
	}

	return minimumNumberOfGyms;
    }

    /**
     * This method will return the maximum number of gyms per manager.<br>
     * <br>
     *
     * By querying all managers stored inside the database, this method check how
     * many gyms each manager is in charge of, returning the maximum number of gyms
     * per manager.
     *
     * @return The maximum number of gyms per manager.
     */
    public int getMaximumNumberOfGymsPerManager() {

	Collection<Manager> managers = this.managerService.findAll();

	// Get an iterator to iterate over this collection of managers
	Iterator<Manager> it = managers.iterator();

	int maximumNumberOfGyms = 0;

	while (it.hasNext()) {

	    Manager manager = it.next();

	    if (manager.getGyms().size() > maximumNumberOfGyms)
		maximumNumberOfGyms = manager.getGyms().size();
	}

	return maximumNumberOfGyms;
    }

    /**
     * This method will return the average of how many gyms a manager is in charge
     * of.<br>
     * <br>
     *
     * Take into account that this method will only return the average integer
     * portion, leaving out all float values.
     *
     * @return The average number of gyms per manager.
     */
    public int getAverageNumberOfGymsPerManager() {

	Collection<Manager> managers = this.managerService.findAll();

	if (managers.isEmpty())
	    return 0;

	// Get an iterator to iterate over this collection of managers
	Iterator<Manager> it = managers.iterator();

	int totalNumberOfGyms = 0;

	int totalNumberOfManagers = 0;

	while (it.hasNext()) {

	    Manager manager = it.next();

	    totalNumberOfGyms += manager.getGyms().size();

	    totalNumberOfManagers++;
	}

	int averageNumberOfGymsPerManager = totalNumberOfGyms / totalNumberOfManagers;

	return averageNumberOfGymsPerManager;
    }

    public double getStandarDeviationOfGymsPerManager() {
	return 0;
    }

    public int getMinimumNumberOfGymsPerClient() {

	Collection<Client> clients = this.clientService.findAll();

	if (clients == null)
	    return 0;

	// Get an iterator to iterate over this collection of clients
	Iterator<Client> it = clients.iterator();

	// Because the minimum number of gyms is wanted, we first initialize
	// our minimum to the maximum possible integer value. Hence, when
	// the first client is checked, no matter how low the value is, it
	// will establish itself as the minimum value.
	int minimumNumberOfGyms = Integer.MAX_VALUE;

	while (it.hasNext()) {

	    Client client = it.next();

	    // Because the client does not have any straighforward relationship with Gym
	    // inscriptions are used instead.
	    //
	    // Since the inscription always must be related to a gym, by the number of
	    // inscriptions
	    // we can get how many gyms has this client attended to
	    int numberOfGymsInscripted = this.clientService.getNumberOfGymsInscripted(client);

	    if (numberOfGymsInscripted < minimumNumberOfGyms)
		minimumNumberOfGyms = numberOfGymsInscripted;
	}

	return minimumNumberOfGyms;
    }

    public int getMaximumNumberOfGymsPerClient() {
	return 0;
    }

    public double getAverageNumberOfGymsPerClient() {
	return 0;
    }

    public double getStandardDeviationOfGymsPerClient() {
	return 0;
    }

    public int getMinimumNumberOfClientsPerGym() {
	return 0;
    }

    public int getMaximumNumberOfClientsPerGym() {
	return 0;
    }

    public double getAverageNumberOfClientsPerGym() {
	return 0;
    }

    public double getStandardDeviationOfClientsPerGym() {
	return 0;
    }

    public Gym getGymWithMostNumberOfActivities() {
	return null;
    }

    public Collection<Client> getClientsWithMostActivities() {
	return null;
    }

    public int getMinimumNumberOfTrainersPerGym() {
	return 0;
    }

    public int getMaximumNumberOfTrainersPerGym() {
	return 0;
    }

    public double getAverageNumberOfTrainersPerGym() {
	return 0;
    }

    public int getMinimumNumberOfStepsPerTraining() {
	return 0;
    }

    public int getMaximumNumberOfStepsPerTraining() {
	return 0;
    }

    public double getAverageNumberOfStepsPerTraining() {
	return 0;
    }

    public Collection<Training> getListOfTrainingsFromBestValuedToWorst() {
	return null;
    }

}
