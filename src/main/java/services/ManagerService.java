
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Admin;
import domain.Gym;
import domain.Manager;
import repositories.ManagerRepository;

@Service
@Transactional
public class ManagerService {

	@Autowired
	private ManagerRepository managerRepository;


	public Manager save(Manager manager) {
		return this.managerRepository.save(manager);
	}

	public void delete(Manager manager) {
		this.managerRepository.delete(manager);
	}

	public Collection<Manager> findAll() {
		return this.managerRepository.findAll();
	}

	public void addAdministrator(Manager manager, Admin admin) {
		if (manager != null && admin != null) {
			manager.addAdministrator(admin);
			this.save(manager);
		}
	}

	public void removeAdministrator(Manager manager, Admin admin) {
		if (manager != null && admin != null) {
			manager.getAdministrators().remove(admin);
			this.save(manager);
		}
	}

	public Collection<Gym> getGyms(Manager manager) {
		if (manager != null)
			return manager.getGyms();
		return null;
	}

	public void addGym(Manager manager, Gym gym) {
		if (manager != null && gym != null) {
			manager.getGyms().add(gym);
			this.save(manager);
		}
	}

	public void removeGym(Manager manager, Gym gym) {
		if (manager != null && gym != null) {
			manager.getGyms().remove(gym);
			this.save(manager);
		}
	}

}
