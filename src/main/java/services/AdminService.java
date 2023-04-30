package services;

import java.util.Collection;

import javax.transaction.Transactional;

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
     *
     * @param adminId
     * @return
     */
    public Admin findOne(int adminId) {
	return this.adminRepository.findOne(adminId);
    }

    /**
     *
     * @param admin
     * @return
     */
    public Admin save(Admin admin) {
	return this.adminRepository.save(admin);
    }

    /**
     *
     * @param admin
     */
    public void delete(Admin admin) {
	this.adminRepository.delete(admin);
    }

    /**
     * Will ban a manager
     *
     * @param manager
     */
    public void banManager(Manager manager) {

    }

    public void unbanManager(Manager manager) {

    }

    public int getMinimumNumberOfGymsPerManager() {
	return 0;
    }

    public int getMaximumNumberOfGymsPerManager() {
	return 0;
    }

    public double getAverageNumberOfGymsPerManager() {
	return 0;
    }

    public double getStandarDeviationOfGymsPerManager() {
	return 0;
    }

    public int getMinimumNumberOfGymsPerClient() {
	return 0;
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
