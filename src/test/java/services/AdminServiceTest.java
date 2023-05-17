package services;

import java.util.Collection;
import java.util.Iterator;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.Client;
import domain.Training;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml" })
@Transactional
public class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    @Test
    public void testMinimumNumberOfGymsPerManager() {
	System.out
		.println("Minimum number of gyms per manager: " + this.adminService.getMinimumNumberOfGymsPerManager());
    }

    @Test
    public void testMaximumNumberOfGymsPerManager() {
	System.out
		.println("Maximum number of gyms per manager: " + this.adminService.getMaximumNumberOfGymsPerManager());
    }

    @Test
    public void testAverageNumberOfGymsPerManager() {
	System.out
		.println("Average number of gyms per manager: " + this.adminService.getAverageNumberOfGymsPerManager());
    }

    @Test
    public void testStandardDeviationOfGymsPerManager() {
	System.out.println(
		"Standard deviation of gyms per manager: " + this.adminService.getStandarDeviationOfGymsPerManager());
    }

    @Test
    public void testMinimumNumberOfGymsPerClient() {
	System.out.println("Minimum number of gyms per client: " + this.adminService.getMinimumNumberOfClientsPerGym());
    }

    @Test
    public void testMaximumNumberOfGymsPerClient() {
	System.out.println("Maximum number of gyms per client: " + this.adminService.getMaximumNumberOfClientsPerGym());
    }

    @Test
    public void testAverageNumberOfGymsPerClient() {
	System.out.println("Average number of gyms per client: " + this.adminService.getAverageNumberOfClientsPerGym());
    }

    @Test
    public void testStandardDeviationOfGymsPerClient() {
	System.out.println(
		"Standard deviation of gyms per client: " + this.adminService.getStandardDeviationOfGymsPerClient());
    }

    @Test
    public void testMinimumNumberOfClientsPerGym() {
	System.out.println("Minimum number of clients per gym: " + this.adminService.getMinimumNumberOfClientsPerGym());
    }

    @Test
    public void testMaximumNumberOfClientsPerGym() {
	System.out.println("Maximum number of clients per gym: " + this.adminService.getMaximumNumberOfClientsPerGym());
    }

    @Test
    public void testAverageNumberOfClientsPerGym() {
	System.out.println("Average number of clients per gym: " + this.adminService.getAverageNumberOfClientsPerGym());
    }

    @Test
    public void testStandardDeviationOfClientsPerGym() {
	System.out.println(
		"Standard deviation of clients per gym: " + this.adminService.getStandardDeviationOfClientsPerGym());
    }

    @Test
    public void testGymWithMostNumberOfActivities() {
	System.out
		.println("Gym with most number of activities: " + this.adminService.getGymWithMostNumberOfActivities());
    }

    @Test
    public void testClientsWithMostActivities() {
	Collection<Client> clientsWithMostActivities = this.adminService.getClientsWithMostActivities();

	Iterator<Client> it = clientsWithMostActivities.iterator();

	System.out.println("Clients With Most Activities");
	System.out.println("------------------------------");

	while (it.hasNext()) {

	    Client client = it.next();

	    System.out.println("Client: " + client.getFirstName() + " " + client.getLastName());
	}
    }

    @Test
    public void testGetMinimumNumberOfTrainersPerGym() {
	System.out
		.println("Minimum Number of trainers per gym: " + this.adminService.getMinimumNumberOfTrainersPerGym());
    }

    @Test
    public void testGetMaximumNumberOfTrainersPerGym() {
	System.out
		.println("Maximum Number of trainers per gym: " + this.adminService.getMaximumNumberOfTrainersPerGym());
    }

    @Test
    public void testGetAverageNumberOfTrainersPerGym() {
	System.out
		.println("Average Number of trainers per gym: " + this.adminService.getAverageNumberOfTrainersPerGym());
    }

    @Test
    public void testGetMinimumNumberOfStepsPerTraining() {
	System.out.println(
		"Minimum number of steps per training: " + this.adminService.getMinimumNumberOfStepsPerTraining());
    }

    @Test
    public void testGetMaximumNumberOfStepsPerTraining() {
	System.out.println(
		"Maximum number of steps per training: " + this.adminService.getMaximumNumberOfStepsPerTraining());
    }

    @Test
    public void testGetAverageNumberOfStepsPerTraining() {
	System.out.println(
		"Average number of steps per training: " + this.adminService.getAverageNumberOfStepsPerTraining());
    }

    @Test
    public void testGetListOfTrainingsFromBestValuedToWorse() {

	Collection<Training> trainings = this.adminService.getListOfTrainingsFromBestValuedToWorst();

	Iterator<Training> it = trainings.iterator();

	while (it.hasNext()) {

	    Training training = it.next();

	    System.out.println("Training: " + training.getTitle() + " Mark: " + training.getAverageMark());
	}
    }
}
