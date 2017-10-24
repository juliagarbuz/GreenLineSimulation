/*
 * Julia Garbuz
 */
import java.util.Random;

public class Stop {
    int stopId;
    //boolean west;
    Q1 qE = new Q1();
    Q1 qW = new Q1();

    public Stop(){
        stopId = getId(randStop());
    }

    public Stop(int i){
        stopId = i;
        Simulation.agenda.add(new PassengerMaker(),0);
    }

    public static String getStop(int n) {
        String[] stopsOrdered = {
                "Target Field",
                "Warehouse/Hennepin Ave",
                "Nicollet Mall",
                "Government Plaza",
                "U.S. Bank Stadium",
                "West Bank",
                "East Bank",
                "Stadium Village",
                "Prospect Park",
                "Westgate",
                "Raymond Ave",
                "Fairview Ave",
                "Snelling Ave",
                "Hamline Ave",
                "Lexington Parkway",
                "Victoria St",
                "Dale St",
                "Western Ave",
                "Capitol/Rice St",
                "Robert St",
                "10th St",
                "Central",
                "Union Depot"
        };
        return stopsOrdered[n];

    }
    public static int getId(String n) {
        int i = 0;
        while (i<23) {
            if (n.equals(getStop(i))) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static int getPriority(int n) {
        int i = 0;
        String x = getStop(n);
        String[] zero = {"Target Field", "Warehouse/Hennepin Ave", "Nicollet Mall", "Government Plaza",
                "U.S. Bank Stadium", "Capitol/Rice St", "Robert St", "10th St",
                "Central", "Union Depot" };
        while (i< zero.length) {
            if (x.equals(zero[i])) {
                return 0;
            }
            i++;
        }
        i = 0;
        String[] one = {"West Bank", "East Bank", "Stadium Village" };
        while (i< one.length) {
            if (x.equals(one[i])) {
                return 1;
            }
            i++;
        }
        String[] two = {"Prospect Park", "Westgate", "Raymond Ave", "Fairview Ave", "Snelling Ave",
                "Hamline Ave", "Lexington Parkway", "Victoria St", "Dale St", "Western Ave" };
        i = 0;
        while (i < two.length) {
            if (x.equals(two[i])) {
                return 2;
            }
            i++;
        }
        return -1;
    }//getPriority

    public static int getTime(int n) {
        int i = 0;
        String x = getStop(n);
        String[] zero = {"Target Field", "Warehouse/Hennepin Ave", "Nicollet Mall", "Government Plaza",
                "U.S. Bank Stadium", "Capitol/Rice St", "Robert St", "10th St",
                "Central", "Union Depot" };
        while (i < zero.length) {
            if (x.equals(zero[i])) {
                return 30;
            }
            i++;
        }
        i = 0;
        String[] one = {"West Bank", "East Bank", "Stadium Village" };
        while (i < one.length) {
            if (x.equals(one[i])) {
                return 25;
            }
            i++;
        }
        i = 0;
        String[] two = {"Prospect Park", "Westgate", "Raymond Ave", "Fairview Ave", "Snelling Ave",
                "Hamline Ave", "Lexington Parkway", "Victoria St", "Dale St", "Western Ave" };
        while (i < two.length) {
            if (x.equals(two[i])) {
                return 20;
            }
            i++;
        }
        return -1;
    }//getTime

    public static String randStop() {
        String[][] stopsAll = {{"Target Field", "Warehouse/Hennepin Ave", "Nicollet Mall", "Government Plaza",
                "U.S. Bank Stadium", "Capitol/Rice St", "Robert St", "10th St",
                "Central", "Union Depot" }, {"West Bank", "East Bank", "Stadium Village" }, {"Prospect Park",
                "Westgate", "Raymond Ave", "Fairview Ave", "Snelling Ave", "Hamline Ave", "Lexington Parkway",
                "Victoria St", "Dale St", "Western Ave" }}; //stops[0] –> dwtwn stops, [1] –> campus, [2] –> normal

        int[] freq = {  0,0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,0,
                        1,1,1,1,1,1,1,1,1,
                        2,2,2,2,2,2,2,2,2,2};
        int r = freq[new Random().nextInt(69)];
        String stop;
        if (r == 0){
            String[] stops = stopsAll[0];
            int r2 = new Random().nextInt(10);
            stop = stops[r2];
            return stop;
        }
        else if (r == 1){
            String[] stops = stopsAll[1];
            int r2 = new Random().nextInt(3);
            stop = stops[r2];
            return stop;
        }
        else if (r == 2){
            String[] stops = stopsAll[2];
            int r2 = new Random().nextInt(10);
            stop = stops[r2];
            return stop;
        }
        else return null;
    }//randStop

    public boolean add(boolean west, Passenger p){
        if (p != null){
            if (west){
                qW.add(p);
                return true;
            }
            qE.add(p);
            return true;
        }
        return false;

    }//add

    public Q1 getQ(boolean west, int stop){
        if (west){ return qW; }
        else{ return qE; }
    }//getQ

    public static void main(String[] args) {
        int o = 0;
        while (o < 1000) {
            String y = randStop();
            System.out.println(y);
            o++;
        }
    }//main

}
