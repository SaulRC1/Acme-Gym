
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Gym;
import domain.Manager;
import repositories.ManagerRepository;

@Service
@Transactional
public class ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    public Manager create() {
	return new Manager();
    }

    public Manager save(final Manager manager) {
	return this.managerRepository.save(manager);
    }

    public void delete(final Manager manager) {
	this.managerRepository.delete(manager);
    }

    public Collection<Manager> findAll() {
	return this.managerRepository.findAll();
    }

    public Collection<Gym> getGyms(final Manager manager) {
	if (manager != null)
	    return manager.getGyms();
	return null;
    }

    public void addGym(final Manager manager, final Gym gym) {
	if (manager != null && gym != null) {
	    manager.getGyms().add(gym);
	    this.save(manager);
	}
    }

    public void removeGym(final Manager manager, final Gym gym) {
	if (manager != null && gym != null) {
	    manager.getGyms().remove(gym);
	    this.save(manager);
	}
    }

    public Manager findOne(final int managerId) {
	return this.managerRepository.findOne(managerId);
    }

    public Manager findByUserAccountId(int userAccountId) {
	return this.managerRepository.findByUserAccountId(userAccountId);
    }
}
