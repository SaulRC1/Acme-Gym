
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
		Assert.notNull(manager);

		Manager result;

		Assert.isTrue(!manager.getGyms().contains(manager));

		result = this.managerRepository.save(manager);

		return result;
	}

	public void delete(final Manager manager) {
		Assert.notNull(manager);
		Assert.isTrue(manager.getId() != 0);
		Assert.isTrue(this.managerRepository.exists(manager.getId()));

		this.managerRepository.delete(manager);
	}

	public Collection<Manager> findAll() {
		Collection<Manager> result;

		result = this.managerRepository.findAll();
		Assert.notNull(result);

		return result;
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
		Assert.isTrue(managerId != 0);

		Manager result;

		result = this.managerRepository.findOne(managerId);
		Assert.notNull(result);

		return result;
	}

	public Manager findByUserAccountId(final int userAccountId) {
		return this.managerRepository.findByUserAccountId(userAccountId);
	}
}
