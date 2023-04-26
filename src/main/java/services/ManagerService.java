
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
