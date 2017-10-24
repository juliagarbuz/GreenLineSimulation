/*
 * Julia Garbuz
 */
import java.util.Random;

public class PassengerMaker implements Event {
    double reschedule;
    double n;    //multiplier for time
    int stop;   //index of stop

    public PassengerMaker() {
        stop = new Random().nextInt(23);
        n = Stop.getTime(stop);
        double[] x = {1.75 * n, 1.75 * n, 1.5 * n, 1.5 * n, 1.5 * n,
                1.2 * n, 1.2 * n, 1.2 * n, 1.2 * n, n, n, 0.8 * n, 0.8 * n,
                0.8 * n, 0.8 * n, 0.5 * n, 0.5 * n, 0.5 * n, 0.25 * n, 0.25 * n};
        reschedule = x[new Random().nextInt(20)];
    }

    public void run() {
        //System.out.println("\n(" + Simulation.agenda.getCurrentTime() + ") PASSENGER MADE @ " + Stop.getStop(stop));
        double t = Simulation.agenda.getCurrentTime();  //Time Passenger is created
        Simulation.agenda.add(new PassengerMaker(), reschedule);
        Passenger p = new Passenger(stop);
        p.setCreationTime(t);
        Simulation.stops[stop].add(p.west, p);
    }//run

}
