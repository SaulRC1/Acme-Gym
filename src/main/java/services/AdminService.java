
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Activity;
import domain.Admin;
import domain.Client;
import domain.Gym;
import domain.Manager;
import domain.Trainer;
import domain.Training;
import domain.comparator.ClientComparatorByNumberOfActivities;
import domain.comparator.TrainingComparatorByMark;
import repositories.AdminRepository;
import services.gym.GymService;

@Service
@Transactional
public class AdminService {

	@Autowired
	private AdminRepository	adminRepository;

	@Autowired
	private ManagerService	managerService;

	@Autowired
	private ClientService	clientService;

	@Autowired
	private GymService		gymService;

	@Autowired
	private TrainingService	trainingService;


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
	 * @param adminId
	 *            The admin ID from database
	 * @return An admin, or null if it does not find anything.
	 */
	public Admin findOne(final int adminId) {
		return this.adminRepository.findOne(adminId);
	}

	/**
	 * This method will find an admin by his email.
	 *
	 * @param email
	 *            The email related to the admin
	 * @return The Admin object, or null in case this email is not registered.
	 */
	public Admin findByEmail(final String email) {
		return this.adminRepository.getByEmail(email);
	}

	/**
	 * This method will save an admin inside database
	 *
	 * @param admin
	 *            The admin to be saved
	 * @return The saved admin
	 */
	public Admin save(final Admin admin) {
		Assert.notNull(admin);

		Admin result;

		result = this.adminRepository.save(admin);

		return result;
	}

	/**
	 * This method will delete an admin from the database.
	 *
	 * @param admin
	 *            The admin to be deleted
	 */
	public void delete(final Admin admin) {
		this.adminRepository.delete(admin);
	}

	/**
	 * Will ban a manager. Changes will be persisted to database immediately.
	 *
	 * @param manager
	 *            The manager to be banned
	 */
	public void banManager(final Manager manager) {

		if (manager != null) {

			manager.setBanned(true);

			this.managerService.save(manager);
		}
	}

	/**
	 * Will unban a manager. Changes will be persisted to database immediately.
	 *
	 * @param manager
	 */
	public void unbanManager(final Manager manager) {

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

		final Collection<Manager> managers = this.managerService.findAll();

		if (managers == null || managers.isEmpty())
			return 0;

		// Get an iterator to iterate over this collection of managers
		final Iterator<Manager> it = managers.iterator();

		// Because the minimum number of gyms is wanted, we first initialize
		// our minimum to the maximum possible integer value. Hence, when
		// the first manager is checked, no matter how low the value is, it
		// will establish itself as the minimum value.
		int minimumNumberOfGyms = Integer.MAX_VALUE;

		while (it.hasNext()) {

			final Manager manager = it.next();

			final int numberOfGyms = manager.getGyms() == null ? 0 : manager.getGyms().size();

			if (numberOfGyms < minimumNumberOfGyms)
				minimumNumberOfGyms = numberOfGyms;
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

		final Collection<Manager> managers = this.managerService.findAll();

		if (managers == null || managers.isEmpty())
			return 0;

		// Get an iterator to iterate over this collection of managers
		final Iterator<Manager> it = managers.iterator();

		int maximumNumberOfGyms = 0;

		while (it.hasNext()) {

			final Manager manager = it.next();

			final int numberOfGyms = manager.getGyms() == null ? 0 : manager.getGyms().size();

			if (numberOfGyms > maximumNumberOfGyms)
				maximumNumberOfGyms = numberOfGyms;
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
	public double getAverageNumberOfGymsPerManager() {

		final Collection<Manager> managers = this.managerService.findAll();

		if (managers == null || managers.isEmpty())
			return 0;

		// Get an iterator to iterate over this collection of managers
		final Iterator<Manager> it = managers.iterator();

		double totalNumberOfGyms = 0;

		double totalNumberOfManagers = 0;

		while (it.hasNext()) {

			final Manager manager = it.next();

			final int numberOfGyms = manager.getGyms() == null ? 0 : manager.getGyms().size();

			totalNumberOfGyms += numberOfGyms;

			totalNumberOfManagers++;
		}

		final double averageNumberOfGymsPerManager = totalNumberOfGyms / totalNumberOfManagers;

		return averageNumberOfGymsPerManager;
	}

	/**
	 * Calculates the standard deviation of gyms per manager.
	 *
	 * @return The standard deviation of gyms per manager.
	 */
	public double getStandarDeviationOfGymsPerManager() {

		final double averageNumberOfGymsPerManager = this.getAverageNumberOfGymsPerManager();

		final Collection<Manager> managers = this.managerService.findAll();

		if (managers == null || managers.isEmpty())
			return 0;

		final Iterator<Manager> it = managers.iterator();

		// The formula of standard deviation is formed by a series of member that
		// belongs to
		// a summatory, each member is elevated to the second power
		final List<Double> summatoryMembersCalculationResult = new ArrayList<Double>();

		while (it.hasNext()) {

			final Manager manager = it.next();

			final int numberOfGyms = manager.getGyms() == null ? 0 : manager.getGyms().size();

			final double managerGymsMinusAverage = numberOfGyms - averageNumberOfGymsPerManager;

			final double summatoryMemberCalculation = Math.pow(managerGymsMinusAverage, 2);

			summatoryMembersCalculationResult.add(summatoryMemberCalculation);
		}

		double totalSummatoryResult = 0;

		// Calculate the summatory
		for (final double summatoryMemberCalculation : summatoryMembersCalculationResult)
			totalSummatoryResult += summatoryMemberCalculation;

		double standardDeviation = totalSummatoryResult / summatoryMembersCalculationResult.size();

		standardDeviation = Math.sqrt(standardDeviation);

		return standardDeviation;
	}

	/**
	 * This method will return the minimum number of gyms per client.<br>
	 * <br>
	 *
	 * In order to calculate this minimum, all different gyms that a client has ever
	 * or is currently inscripted to, will be taken into account.<br>
	 * <br>
	 *
	 * If the client has multiple inscriptions to a gym, the gym will only be
	 * counted once.
	 *
	 * @return The minimum number of gyms per client.
	 */
	public int getMinimumNumberOfGymsPerClient() {

		final Collection<Client> clients = this.clientService.findAll();

		if (clients == null || clients.isEmpty())
			return 0;

		// Get an iterator to iterate over this collection of clients
		final Iterator<Client> it = clients.iterator();

		// Because the minimum number of gyms is wanted, we first initialize
		// our minimum to the maximum possible integer value. Hence, when
		// the first client is checked, no matter how low the value is, it
		// will establish itself as the minimum value.
		int minimumNumberOfGyms = Integer.MAX_VALUE;

		while (it.hasNext()) {

			final Client client = it.next();

			// Because the client does not have any straighforward relationship with Gym
			// inscriptions are used instead.
			//
			// Since the inscription always must be related to a gym, by the number of
			// inscriptions
			// we can get how many gyms has this client attended to
			final int numberOfGymsInscripted = this.clientService.getNumberOfGymsInscripted(client);

			if (numberOfGymsInscripted < minimumNumberOfGyms)
				minimumNumberOfGyms = numberOfGymsInscripted;
		}

		return minimumNumberOfGyms;
	}

	/**
	 * This method will return the maximum number of gyms per client.<br>
	 * <br>
	 *
	 * In order to calculate this maximum, all different gyms that a client has ever
	 * or is currently inscripted to, will be taken into account.<br>
	 * <br>
	 *
	 * If the client has multiple inscriptions to a gym, the gym will only be
	 * counted once.
	 *
	 * @return The maximum number of gyms per client.
	 */
	public int getMaximumNumberOfGymsPerClient() {

		final Collection<Client> clients = this.clientService.findAll();

		if (clients == null || clients.isEmpty())
			return 0;

		// Get an iterator to iterate over this collection of clients
		final Iterator<Client> it = clients.iterator();

		// Because the maximum number of gyms is wanted, we first initialize
		// our maximum to -1, because the minimum possible value we will get is 0.
		// Hence, when the first client is checked, no matter how low the value is, it
		// will establish itself as the maximum value.
		int maximumNumberOfGyms = -1;

		while (it.hasNext()) {

			final Client client = it.next();

			// Because the client does not have any straighforward relationship with Gym
			// inscriptions are used instead.
			//
			// Since the inscription always must be related to a gym, by the number of
			// inscriptions
			// we can get how many gyms has this client attended to
			final int numberOfGymsInscripted = this.clientService.getNumberOfGymsInscripted(client);

			if (numberOfGymsInscripted > maximumNumberOfGyms)
				maximumNumberOfGyms = numberOfGymsInscripted;
		}

		return maximumNumberOfGyms;
	}

	/**
	 * Gets the average number of gyms per client.
	 *
	 * @return The average number of gyms per client.
	 */
	public double getAverageNumberOfGymsPerClient() {

		final Collection<Client> clients = this.clientService.findAll();

		if (clients == null || clients.isEmpty())
			return 0;

		// Get an iterator to iterate over this collection of clients
		final Iterator<Client> it = clients.iterator();

		double totalNumberOfGymsPerClient = 0;
		final double totalClients = clients.size();

		while (it.hasNext()) {

			final Client client = it.next();

			totalNumberOfGymsPerClient += this.clientService.getNumberOfGymsInscripted(client);
		}

		final double averageNumberOfGymsPerClient = totalNumberOfGymsPerClient / totalClients;

		return averageNumberOfGymsPerClient;
	}

	/**
	 * Gets the standard deviation of gyms per client.
	 *
	 * @return The standard deviation of gyms per client.
	 */
	public double getStandardDeviationOfGymsPerClient() {

		final double averageNumberOfGymsPerClient = this.getAverageNumberOfGymsPerClient();

		final Collection<Client> clients = this.clientService.findAll();

		if (clients == null || clients.isEmpty())
			return 0;

		// Get an iterator to iterate over this collection of clients
		final Iterator<Client> it = clients.iterator();

		// The formula of standard deviation is formed by a series of member that
		// belongs to
		// a summatory, each member is elevated to the second power
		final List<Double> summatoryMembersCalculationResult = new ArrayList<Double>();

		while (it.hasNext()) {

			final Client client = it.next();

			final double clientGymsMinusAverage = this.clientService.getNumberOfGymsInscripted(client) - averageNumberOfGymsPerClient;

			final double summatoryMemberCalculation = Math.pow(clientGymsMinusAverage, 2);

			summatoryMembersCalculationResult.add(summatoryMemberCalculation);
		}

		double totalSummatoryResult = 0;

		// Calculate the summatory
		for (final double summatoryMemberCalculation : summatoryMembersCalculationResult)
			totalSummatoryResult += summatoryMemberCalculation;

		double standardDeviation = totalSummatoryResult / summatoryMembersCalculationResult.size();

		standardDeviation = Math.sqrt(standardDeviation);

		return standardDeviation;
	}

	/**
	 * This method will return the minimum number of clients per gym.
	 *
	 * @return The minimum number of clients per gym.
	 */
	public int getMinimumNumberOfClientsPerGym() {

		final Collection<Gym> gyms = this.gymService.findAll();

		if (gyms == null || gyms.isEmpty())
			return 0;

		final Iterator<Gym> it = gyms.iterator();

		int minimumNumberOfClientsPerGym = Integer.MAX_VALUE;

		while (it.hasNext()) {

			final Gym gym = it.next();

			final int numberOfClients = this.gymService.getNumberOfClientsFromGym(gym);

			if (numberOfClients < minimumNumberOfClientsPerGym)
				minimumNumberOfClientsPerGym = numberOfClients;
		}

		return minimumNumberOfClientsPerGym;
	}

	/**
	 * This method will return the maximum number of clients per gym.
	 *
	 * @return The maximum number of clients per gym.
	 */
	public int getMaximumNumberOfClientsPerGym() {

		final Collection<Gym> gyms = this.gymService.findAll();

		if (gyms == null || gyms.isEmpty())
			return 0;

		final Iterator<Gym> it = gyms.iterator();

		int maximumNumberOfClientsPerGym = -1;

		while (it.hasNext()) {

			final Gym gym = it.next();

			final int numberOfClients = this.gymService.getNumberOfClientsFromGym(gym);

			if (numberOfClients > maximumNumberOfClientsPerGym)
				maximumNumberOfClientsPerGym = numberOfClients;
		}

		return maximumNumberOfClientsPerGym;
	}

	/**
	 * This method returns the average number of clients per gym
	 *
	 * @return The average number of clients per gym
	 */
	public double getAverageNumberOfClientsPerGym() {

		final Collection<Gym> gyms = this.gymService.findAll();

		if (gyms == null || gyms.isEmpty())
			return 0;

		final Iterator<Gym> it = gyms.iterator();

		double totalNumberOfClientsPerGym = 0;
		final double totalNumberOfGyms = gyms.size();

		while (it.hasNext()) {

			final Gym gym = it.next();

			totalNumberOfClientsPerGym += this.gymService.getNumberOfClientsFromGym(gym);
		}

		return (totalNumberOfClientsPerGym / totalNumberOfGyms);
	}

	/**
	 * This method will return the standard deviation of clients per gym
	 *
	 * @return The standard deviation of clients per gym.
	 */
	public double getStandardDeviationOfClientsPerGym() {

		final double averageNumberOfClientsPerGym = this.getAverageNumberOfClientsPerGym();

		final Collection<Gym> gyms = this.gymService.findAll();

		if (gyms == null)
			return 0;

		final Iterator<Gym> it = gyms.iterator();

		// The formula of standard deviation is formed by a series of member that
		// belongs to
		// a summatory, each member is elevated to the second power
		final List<Double> summatoryMembersCalculationResult = new ArrayList<Double>();

		while (it.hasNext()) {

			final Gym gym = it.next();

			final double gymClientsMinusAverage = this.gymService.getNumberOfClientsFromGym(gym) - averageNumberOfClientsPerGym;

			final double summatoryMemberCalculation = Math.pow(gymClientsMinusAverage, 2);

			summatoryMembersCalculationResult.add(summatoryMemberCalculation);
		}

		double totalSummatoryResult = 0;

		// Calculate the summatory
		for (final double summatoryMemberCalculation : summatoryMembersCalculationResult)
			totalSummatoryResult += summatoryMemberCalculation;

		double standardDeviation = totalSummatoryResult / summatoryMembersCalculationResult.size();

		standardDeviation = Math.sqrt(standardDeviation);

		return standardDeviation;
	}

	/**
	 * This method will return the gym with the most number of activities<br>
	 * <br>
	 *
	 * In case that there are no gyms this method will return null. Also this method
	 * will only take into account activities that are active.
	 *
	 * @return The gym with the most number of activities or null.
	 */
	public Gym getGymWithMostNumberOfActivities() {

		Gym gymWithMostNumberOfActivities = null;

		final Collection<Gym> gyms = this.gymService.findAll();

		if (gyms == null || gyms.isEmpty())
			return null;

		int mostNumberOfActivities = -1;

		for (final Gym gym : gyms) {

			final Collection<Activity> activities = gym.getActivities();

			int numberOfActivities = 0;

			for (final Activity activity : activities)
				if (activity.isActive())
					numberOfActivities++;

			if (numberOfActivities > mostNumberOfActivities) {

				mostNumberOfActivities = numberOfActivities;
				gymWithMostNumberOfActivities = gym;
			}
		}

		return gymWithMostNumberOfActivities;
	}

	/**
	 * This method will return the top 5 clients that have the most activities.
	 *
	 * @return The top 5 clients that have the most activities.
	 */
	public Collection<Client> getClientsWithMostActivities() {

		final Collection<Client> clientCollection = this.clientService.findAll();

		if (clientCollection == null || clientCollection.isEmpty())
			return null;

		final List<Client> clientList = new ArrayList<Client>(clientCollection);

		Collections.sort(clientList, new ClientComparatorByNumberOfActivities());

		final Collection<Client> topFiveClientsWithMostActivities = new ArrayList<Client>();

		for (int i = 0; i < 5; i++)
			if (i < clientList.size())
				topFiveClientsWithMostActivities.add(clientList.get(i));

		return topFiveClientsWithMostActivities;
	}

	/**
	 * This method will return the minimum number of trainers per gym.
	 *
	 * @return The minimum number of trainers per gym.
	 */
	public int getMinimumNumberOfTrainersPerGym() {

		final Collection<Gym> gyms = this.gymService.findAll();

		if (gyms == null || gyms.isEmpty())
			return 0;

		final Iterator<Gym> it = gyms.iterator();

		int minimumNumberOfTrainersPerGym = Integer.MAX_VALUE;

		while (it.hasNext()) {

			final Gym gym = it.next();

			final Collection<Trainer> trainers = gym.getTrainers();

			final int numberOfTrainers = trainers == null ? 0 : trainers.size();

			if (numberOfTrainers < minimumNumberOfTrainersPerGym)
				minimumNumberOfTrainersPerGym = numberOfTrainers;
		}

		return minimumNumberOfTrainersPerGym;
	}

	/**
	 * This method will return the maximum number of trainers per gym.
	 *
	 * @return The maximum number of trainers per gym.
	 */
	public int getMaximumNumberOfTrainersPerGym() {

		final Collection<Gym> gyms = this.gymService.findAll();

		if (gyms == null || gyms.isEmpty())
			return 0;

		final Iterator<Gym> it = gyms.iterator();

		int maximumNumberOfTrainersPerGym = -1;

		while (it.hasNext()) {

			final Gym gym = it.next();

			final Collection<Trainer> trainers = gym.getTrainers();

			final int numberOfTrainers = trainers == null ? 0 : trainers.size();

			if (numberOfTrainers > maximumNumberOfTrainersPerGym)
				maximumNumberOfTrainersPerGym = numberOfTrainers;
		}

		return maximumNumberOfTrainersPerGym;
	}

	/**
	 * This method returns the average number of trainers per gym.
	 *
	 * @return the average number of trainers per gym.
	 */
	public double getAverageNumberOfTrainersPerGym() {

		final Collection<Gym> gyms = this.gymService.findAll();

		if (gyms == null || gyms.isEmpty())
			return 0;

		final Iterator<Gym> it = gyms.iterator();

		double averageNumberOfTrainers = 0;
		double totalNumberOfTrainers = 0;

		while (it.hasNext()) {

			final Gym gym = it.next();

			final double numberOfTrainers = gym.getTrainers() == null ? 0 : gym.getTrainers().size();

			totalNumberOfTrainers += numberOfTrainers;
		}

		averageNumberOfTrainers = totalNumberOfTrainers / gyms.size();

		return averageNumberOfTrainers;
	}

	public int getMinimumNumberOfStepsPerTraining() {

		final Collection<Training> trainings = this.trainingService.findAll();

		if (trainings == null || trainings.isEmpty())
			return 0;

		final Iterator<Training> it = trainings.iterator();

		int minimumNumberOfSteps = Integer.MAX_VALUE;

		while (it.hasNext()) {

			final Training training = it.next();

			final int numberOfSteps = training.getSteps() == null ? 0 : training.getSteps().size();

			if (numberOfSteps < minimumNumberOfSteps)
				minimumNumberOfSteps = numberOfSteps;
		}

		return minimumNumberOfSteps;
	}

	public int getMaximumNumberOfStepsPerTraining() {

		final Collection<Training> trainings = this.trainingService.findAll();

		if (trainings == null || trainings.isEmpty())
			return 0;

		final Iterator<Training> it = trainings.iterator();

		int maximumNumberOfSteps = -1;

		while (it.hasNext()) {

			final Training training = it.next();

			final int numberOfSteps = training.getSteps() == null ? 0 : training.getSteps().size();

			if (numberOfSteps > maximumNumberOfSteps)
				maximumNumberOfSteps = numberOfSteps;
		}

		return maximumNumberOfSteps;
	}

	public double getAverageNumberOfStepsPerTraining() {

		final Collection<Training> trainings = this.trainingService.findAll();

		if (trainings == null || trainings.isEmpty())
			return 0;

		final Iterator<Training> it = trainings.iterator();

		double averageNumberOfSteps = 0;

		double totalNumberOfSteps = 0;

		while (it.hasNext()) {

			final Training training = it.next();

			final double numberOfSteps = training.getSteps() == null ? 0 : training.getSteps().size();

			totalNumberOfSteps += numberOfSteps;
		}

		averageNumberOfSteps = totalNumberOfSteps / trainings.size();

		return averageNumberOfSteps;
	}

	public Collection<Training> getListOfTrainingsFromBestValuedToWorst() {

		final Collection<Training> trainings = this.trainingService.findAll();

		if (trainings == null || trainings.isEmpty())
			return Collections.emptyList();

		final List<Training> trainingsList = new ArrayList<Training>(trainings);

		Collections.sort(trainingsList, Collections.reverseOrder(new TrainingComparatorByMark()));

		return trainingsList;
	}

	public Admin findByUserAccountId(final int userAccountId) {
		return this.adminRepository.findByUserAccountId(userAccountId);
	}

}
