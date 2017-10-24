/*
 * Julia Garbuz
 */
public class TrainEvent implements Event {
    Train currentTrain;
    int trainId;
    int currentStop;
    boolean west;
    int numCars;
    int sittingTime;
    int tripTime = 180;

    public TrainEvent(Train t) {
        currentTrain = t;
        trainId = t.trainId;
        currentStop = t.currentStop;
        west = t.west;
        numCars = t.numCars;
        sittingTime = 0;
    }

    public String direction(){  //used for debugging
        String s = "";
        if (west) {s = "W";}
        else{s = "E";}
        return s;
    }

    public void run() {
        //System.out.println("\n(" + Simulation.agenda.getCurrentTime() +") TRAIN " + trainId + " (" + direction() + ") @ Station: " + Stop.getStop(currentStop));
        int numRemovedPass = 0;
        for (int i = 0; i < numCars; i++){  // for each car check who needs to get off and remove them
            TrainCar thisCar = currentTrain.getTrainCar(i);
            numRemovedPass += thisCar.remove(currentStop);
        }
        //System.out.println(">> NUM REMOVED PASSENGERS: " + numRemovedPass);
        sittingTime += numRemovedPass*2;    // 2 sec per pass to get off

        if (currentTrain.trainEmpty()){
            currentTrain.setStartEmptyTime();
        }

        int numAddedPass = 0;
        Q1 waitingPassengers = Simulation.stops[currentStop].getQ(west, currentStop);

        //System.out.println("Stop: " + currentStop + " \t\t Len = " + waitingPassengers.length());

        Stat.updateLineLength(currentStop, waitingPassengers.length(), west);   //Collecting data on wait line length

        for (int i = 0; i < numCars; i++){  // for each car check who needs to get off and remove them
            TrainCar thisCar = currentTrain.getTrainCar(i);
            //System.out.println("CAR " + i + " FULL: " + thisCar.full());
            while (!waitingPassengers.isEmpty() && !thisCar.full()){
                Passenger p = (Passenger) waitingPassengers.remove();
                if (thisCar.add(p)){
                    numAddedPass += 1;}
            }
        }

        if (numAddedPass > 0){
            currentTrain.totalEmptyTime();
        }

        //System.out.println(">> NUM ADDED PASSENGERS: " + numAddedPass);
        sittingTime += numAddedPass;    // 1 sec per pass to get on

        if (sittingTime < 15){  // train has to stay at stop TrainCar.java:58a least 15 sec
            sittingTime = 15;
        }

        int rescheduleTime = sittingTime + 180;

        currentTrain.nextStop();
        TrainEvent nextTrainEvent = new TrainEvent(currentTrain);
        Simulation.agenda.add(nextTrainEvent, rescheduleTime);

    }//run

}
