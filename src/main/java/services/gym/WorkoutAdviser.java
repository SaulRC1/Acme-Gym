package services.gym;

import java.util.Set;

import domain.Client;
import domain.Training;

/**
 * This interface provides a model to a workout adviser. <br>
 * <br>
 *
 * Workout adviser must be triggered when an user logs in inside the app, and
 * recommend the user with different workout that they may like or be interested
 * in. <br>
 * <br>
 *
 * Workout adviser should check for all workouts inside the database, and also
 * certain user's metrics to check which workouts are optimal for the user, and
 * then be elected as a recommendation.<br>
 * <br>
 *
 * Once recommendations are set, then they will be shown to the user.<br>
 * <br>
 *
 * THE USER SHOULD ONLY RECEIVE A MAXIMUM OF 5 RECOMMENDATIONS.
 */
public interface WorkoutAdviser {

    /**
     * This method will deliver to an authenticated client a recommendation of five
     * different workouts that the user may like taking into account certain
     * metrics.<br>
     * <br>
     *
     * The client will never receive a recommendation where a workout that the
     * client is already doing is included. Recommendations will be different
     * between themselves and also from the client's workouts.<br>
     * <br>
     *
     * @param client The client that must receive a recommendation
     * @return A {@link java.util.Set} with a maximum of 5 workouts inside.
     */
    public Set<Training> deliverWorkoutRecommendations(Client client);

}
