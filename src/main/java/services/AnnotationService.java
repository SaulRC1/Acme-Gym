
package services;

import java.time.LocalDate;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Activity;
import domain.Actor;
import domain.Annotation;
import domain.Gym;
import domain.Training;
import repositories.AnnotationRepository;

@Service
@Transactional
public class AnnotationService {

	@Autowired
	private AnnotationRepository annotationRepository;


	public Collection<Annotation> findAll() {
		return this.annotationRepository.findAll();
	}

	public Collection<Annotation> findByGym(Gym gym) {
		return this.annotationRepository.findByGym(gym);
	}

	public Collection<Annotation> findByActivity(Activity activity) {
		return this.annotationRepository.findByActivity(activity);
	}

	public Collection<Annotation> findByTraining(Training training) {
		return this.annotationRepository.findByTraining(training);
	}

	public Collection<Annotation> findByActor(Actor actor) {
		return this.annotationRepository.findByActor(actor);
	}

	public Collection<Annotation> findByMark(Integer mark) {
		return this.annotationRepository.findByMark(mark);
	}

	public Collection<Annotation> findByDate(LocalDate date) {
		return this.annotationRepository.findByDate(date);
	}

	public Annotation save(Annotation annotation) {
		return this.annotationRepository.save(annotation);
	}

	public void delete(Annotation annotation) {
		this.annotationRepository.delete(annotation);
	}
}
