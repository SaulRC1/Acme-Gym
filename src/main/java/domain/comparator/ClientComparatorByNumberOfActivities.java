package domain.comparator;

import java.util.Collection;
import java.util.Comparator;

import domain.Activity;
import domain.Client;

/**
 * This class offers a comparator to compare client by their number of
 * activities in order to sort them properly.
 */
public class ClientComparatorByNumberOfActivities implements Comparator<Client> {

    @Override
    public int compare(Client client1, Client client2) {

	Collection<Activity> client1Activities = client1.getActivities();

	Collection<Activity> client2Activities = client2.getActivities();

	Integer client1NumberOfActivities = 0;

	if (client1Activities != null && !client1Activities.isEmpty())
	    client1NumberOfActivities = client1Activities.size();

	Integer client2NumberOfActivities = 0;

	if (client2Activities != null && !client2Activities.isEmpty())
	    client2NumberOfActivities = client2Activities.size();

	return client1NumberOfActivities.compareTo(client2NumberOfActivities);
    }

}
