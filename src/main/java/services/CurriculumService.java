
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Curriculum;
import repositories.CurriculumRepository;

@Service
@Transactional
public class CurriculumService {

	@Autowired
	private CurriculumRepository curriculumRepository;


	public Curriculum create() {
		return new Curriculum();
	}

	public Curriculum findOne(int curriculumId) {
		return this.curriculumRepository.findOne(curriculumId);
	}

	public Curriculum save(Curriculum curriculum) {
		return this.curriculumRepository.save(curriculum);
	}

	public void delete(Curriculum curriculum) {
		this.curriculumRepository.delete(curriculum);
	}

	public Collection<Curriculum> findAll() {
		return this.curriculumRepository.findAll();
	}
}
