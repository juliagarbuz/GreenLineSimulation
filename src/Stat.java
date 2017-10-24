/*
 * Julia Garbuz
 */
import java.text.DecimalFormat;
public class Stat {
    // public methods

    public static void updateLineLength(int stop, int len, boolean west) {
        if (west){
            timeLenCollectedW[stop] += 1;
            totalLengthW[stop] += len;
        }
        else{
            timeLenCollectedE[stop] += 1;
            totalLengthE[stop] += len;
        }
    }
    public static double avgLineLength(){
        int times = 0;
        int len = 0;
        for (int i = 0; i < 23; i++){
            times += timeLenCollectedW[i] + timeLenCollectedE[i];
            len += totalLengthW[i] + totalLengthE[i];
        }
        return len/times;
    }

    public static void updateTravelTime(Passenger p) {
        passCountOff += 1;
        double t = p.hopOffTime - p.hopOnTime;
        if (t > maxTravelTime){maxTravelTime = t;}
        //System.out.println(">>>>> (" + Stat.passCountOff + ") OFF: " + p.hopOffTime + "\t (hopOn: " + p.hopOnTime + ")");
        totalTravelTime += t;
    }
    private static double avgTravelTime(){
        return (totalTravelTime/passCountOff);
    }

    public static void updateWaitTime(Passenger p) {
        waitTimeCounter += 1;
        double waitTime = p.hopOnTime - p.creationTime;
        if (waitTime > maxWaitTime){maxWaitTime = waitTime;}
        //System.out.println(">>>>> (" + Stat.waitTimeCounter + ") ON: " + p.hopOnTime + "\t (creation: " + p.creationTime + ")");
        //System.out.println("waitTime = " + waitTime);
        totalWaitTime += waitTime;
    }
    private static double avgWaitTime(){
        return (totalWaitTime/waitTimeCounter);
    }

    private static double avgTrainEmptyTime(){
        double sum = 0;
        for (int i = 0; i < 23; i++){
            sum += totalEmptyTimes[i];
        }
        return sum/numTrains();
    }

    public static void displayStats() {
        DecimalFormat f = new DecimalFormat("#0.00");
        System.out.println("\n** Simulation Results **\n");
        System.out.println(" Total Simulation Time: " + Simulation.agenda.getCurrentTime() + "\n");
        System.out.println(" Num trains: " + numTrains());
        System.out.println(" Avg num cars: " + avgNumCars() + "\n");

        System.out.println(" >> maxTravelTime: " + f.format(maxTravelTime));
        System.out.println(" >> avgTravelTime: " + f.format(avgTravelTime()) + "\n");

        System.out.println(" >> maxWaitTime: " + f.format(maxWaitTime));
        System.out.println(" >> avgWaitTime: " + f.format(avgWaitTime()) + "\n");

        System.out.println(" >> avgTrainEmptyTime: " + f.format(avgTrainEmptyTime()) + "\n");

        System.out.println(" >> avgOverallNumPassPerStop(): " + f.format(avgLineLength()));
        System.out.println(" >> avgNumPassPerStop\t\tEAST\t\tWEST");
        try {
            for (int i = 0; i < 23; i++) {
                if (totalLengthE[i] != 0 && totalLengthW[i] != 0 && timeLenCollectedE[i] != 0 && timeLenCollectedW[i] != 0) {
                    System.out.println("\t\t\tStop " + (i + 1) + ": \t\t" + f.format(totalLengthE[i] / timeLenCollectedE[i]) + "\t\t" +
                            f.format(totalLengthW[i] / timeLenCollectedW[i]));
                } else if (totalLengthE[i] == 0 || timeLenCollectedE[i] == 0) {
                    System.out.println("\t\t\tStop " + (i + 1) + ": \t\t" + f.format(0) + "\t\t" +
                            f.format(totalLengthW[i] / timeLenCollectedW[i]));
                } else if (totalLengthW[i] == 0 || timeLenCollectedW[i] == 0) {
                    System.out.println("\t\t\tStop " + (i + 1) + ": \t\t" + f.format(totalLengthE[i] / timeLenCollectedE[i]) + "\t\t" +
                            f.format(0));
                } else {
                    System.out.println("\t\t\tStop " + (i + 1) + ": \t\t" + f.format(0) + "\t\t" +
                            f.format(0));
                }
            }//for
        }//try
        catch(Exception e){
            System.out.println();
        }

    }  // displayStats

    private static double avgNumCars(){
        int sum = 0;
        for (int i = 0; i < numTrains(); i++){
            Train t = Simulation.trains[i];
            sum += t.length();
        }
        return (sum/numTrains());
    }
    private static int numTrains(){
        return Simulation.numTrains;
    }

    public static double[] totalEmptyTimes = new double[23];

    private static int waitTimeCounter = 1;
    private static int passCountOff = 1;

    private static int[] totalLengthW = new int[23];
    private static int[] totalLengthE = new int[23];
    private static int[] timeLenCollectedW = new int[23];
    private static int[] timeLenCollectedE = new int[23];

    private static double maxWaitTime = 0;
    private static double totalWaitTime;

    private static double totalTravelTime;
    private static double maxTravelTime = 0;
}
