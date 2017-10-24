/*
 * Julia Garbuz
 */
public class TrainCar {
    MyArrayList listPassengers;
    int numPassengers;
    int max = 50;
    int trainId;
    int carId;

    public TrainCar(int trainNum, int carNum) {
        listPassengers = new MyArrayList();
        numPassengers = 0;
        trainId = trainNum;
        carId = carNum;
    }

    public boolean full() { //car has reached maximum capacity
        if (numPassengers == max) {
            return true;
        }
        else {
            return false;
        }
    }//full

    public boolean add(Passenger p) {   //add passenger to listPassengers

        if (full() || p == null) {
            return false;   //cant add p
        }

        else {
            listPassengers.add(p);
            p.setHopOnTime(Simulation.agenda.getCurrentTime()); // currentTime is time passenger
                                                                // "hopped on train" which will be used
            Stat.updateWaitTime(p);                             // to calc waitTime in Stat class

            numPassengers++;
            return true;    //successfully added p
        }

    }//add

    public int remove(int stopId) {  //remove all passengers needed at stop
        //System.out.println(">>>>>>> StopID: " + stopId);
        Passenger[] passengersRemoved = new Passenger[50];
        int numRemoved = 0;

        if (numPassengers == 0) { // nothing to remove
            return 0;
        }

        else {
            int i = 0;
            int index = 0;
            while (i < numPassengers){
                //System.out.println("numPassengers: " + numPassengers);
                //System.out.println("i = " + i);
                //System.out.println("listPassengers.length " + listPassengers.length);
                Passenger currentPassenger = (Passenger) listPassengers.get(index);
                if(currentPassenger != null){
                    //System.out.println(index + "\t" + currentPassenger.destination);
                    if (currentPassenger.destination == stopId){ // currentPassenger wants to get off at this stop
                        passengersRemoved[numRemoved] = currentPassenger; //add removed passenger to array
                        listPassengers.remove(currentPassenger);
                        numRemoved++;
                        numPassengers--;

                        currentPassenger.setHopOffTime(Simulation.agenda.getCurrentTime()); // currentTime is time passenger
                                                                                            // "hopped off train" which will be used
                        Stat.updateTravelTime(currentPassenger);// to calc TravelTime in Stat class

                    }//if

                    else{
                        index++;
                    }
                }
                i++;
            }//while
        }//else
        return numRemoved;

    }//remove

}
