/*
 * Julia Garbuz
 */
public class Simulation {
    static PQ agenda = new PQ();
    static Train[] trains;
    static int numTrains;
    static Stop[] stops = new Stop[23];

    public static void main(String[] args) {

        for (int i =0; i < 23; i++) {
            stops[i] = new Stop(i);
        }

        numTrains = 23;
        trains = new Train[numTrains];

        for (int i = 0; i < numTrains; i++){
            Train t = new Train(i); // i functions as Train id (first train's id = 0)
            trains[i] = t;
            agenda.add(new TrainEvent(t),0);
        }

        while (agenda.getCurrentTime() < 25000) {
            agenda.remove().run();
        }

        for (int i = 0; i < numTrains; i++) {
            Stat.totalEmptyTimes[i] = trains[i].totalEmptyTime;
        }


        Stat.displayStats();

    }
}
