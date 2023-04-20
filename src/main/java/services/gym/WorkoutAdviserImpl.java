package services.gym;

import java.util.Set;

import org.springframework.stereotype.Service;

import domain.Client;
import domain.Training;

@Service
public class WorkoutAdviserImpl implements WorkoutAdviser {

	@Override
	public Set<Training> deliverWorkoutRecommendations(final Client client) {

		return null;
	}

}
