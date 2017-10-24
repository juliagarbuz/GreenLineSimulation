/*
 * Julia Garbuz
 */
public class Passenger {
    double creationTime;
    int destination;
    boolean west;
    public double hopOnTime;    // (needed for Stat class)
    public double hopOffTime;

    public void setHopOnTime(double t){
        hopOnTime = t;
    }

    public void setHopOffTime(double t){
        hopOffTime = t;
    }


    public Passenger(int currentStop) {
        destination = Stop.getId(Stop.randStop());  // Randomly generated destination
                                                    // based off of given likelihoods

        while (destination == currentStop) {    // Making sure destination isn't current stop
            destination = Stop.getId(Stop.randStop());
        }

        west = destination < currentStop;   // Determine Passenger's desired direction to reach dest.
    }

    public void setCreationTime(double t){
        creationTime = t;
    }

}