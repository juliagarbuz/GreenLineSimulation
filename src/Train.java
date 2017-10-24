/*
 * Julia Garbuz
 */
import java.util.Random;

public class Train {
    boolean west;
    int numCars; //random number of cars btw 1-3
    int trainId; //train number (id of train)
    int currentStop;
    TrainCar[] trainCars;
    double startEmptyTime;
    boolean started = false;
    double endEmptyTime;
    double totalEmptyTime;

    public Train(int n) {
        trainId = n; //train number
        currentStop = randStartSpot(); //randomly generated starting point and location/side
        west = randDirection();

        numCars = new Random().nextInt(3)+1;    //determine numCars (randInt)
        trainCars = new TrainCar[numCars];      //Create train array – array of cars in train (len = numCars)
        int i = 0;
        while (i < numCars) {
            trainCars[i] = new TrainCar(trainId, i);    //create cars in trainCars
            i++;
        }
    }

    public void setStartEmptyTime() {
        if (!started) {
            startEmptyTime = Simulation.agenda.getCurrentTime();
        }
    }

    public void totalEmptyTime(){
        if (started != false){
            totalEmptyTime += Simulation.agenda.getCurrentTime() - startEmptyTime;
        }
    }

    public boolean trainEmpty(){
        for (int i = 0; i < numCars; i++){
            if (trainCars[i].listPassengers.size() != 0){
                return false;
            }
        }
        return true;
    }

    public TrainCar getTrainCar(int i){
        return trainCars[i];
    }

    public void nextStop(){
        if (currentStop == 22 && !west){
            changeDirection();
        }

        if (currentStop == 0 && west){
            changeDirection();
        }
        if (west){currentStop--;}
        else{currentStop++;}
    }

    public void changeDirection(){
        if (west){west = false;}
        else{west = true;}
    }

    public int randStartSpot(){
        return new Random().nextInt(23);
    }

    public boolean randDirection(){
        int n = new Random().nextInt(2);
        if (n == 0){return true;}
        return false;
    }

    public int length() {
        return numCars;
    }   //returns numCars (same as length of trainCars array)

}
