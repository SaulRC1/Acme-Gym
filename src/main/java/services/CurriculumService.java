
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
	private final CurriculumRepository curriculumRepository;


	public CurriculumService(final CurriculumRepository curriculumRepository) {
		this.curriculumRepository = curriculumRepository;
	}

	public Curriculum save(final Curriculum curriculum) {
		return this.curriculumRepository.save(curriculum);
	}

	public void delete(final Curriculum curriculum) {
		this.curriculumRepository.delete(curriculum);
	}

	public Collection<Curriculum> findAll() {
		return this.curriculumRepository.findAll();
	}
}
