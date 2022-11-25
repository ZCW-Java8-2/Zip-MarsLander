import java.util.Objects;

public class Vehicle {

    // this is initial vehicle setup
    int Altitude= 8000;
    int PrevAltitude= 8000;
    int Velocity= 1000;
    int Burn = 0;
    int Flying = FLYING;
    int Fuel = 12000;
    int Gravity = 100;
    /* The rate in which the spaceship descents in free fall (in ten seconds) */

    // Various end-of-game messages and status result codes.
    String dead = "\nCRASH!!\n\tThere were no survivors.\n\n";
    public static final int DEAD = -3;
    String crashed = "\nThe Starship crashed. Good luck getting back home. Elon is pissed.\n\n";
    public static final int CRASHED = -2;
    String emptyfuel = "\nThere is no fuel left. You're floating around like Major Tom.\n\n";
    public static final int EMPTYFUEL = -1;
    String success = "\nYou made it! Good job!\n\n";
    public static final int SUCCESS = 0;
    public static final int FLYING = 1;


    public Vehicle() {}
    public Vehicle(int InitialAltitude) {
        this.Altitude = InitialAltitude;
        this.PrevAltitude = InitialAltitude;
        // initialize the altitude AND previous altitude to initialAltitude
    }

    public String checkFinalStatus() {
        String s = "";
        if (this.Altitude <= 0) {
            if (this.Velocity > 10) {
                s = dead;
                Flying = DEAD;
            }
            if (this.Velocity < 10 && this.Velocity > 3) {
                s = crashed;
                Flying = CRASHED;
            }
            if (this.Velocity < 3) {
                s = success;
                Flying = SUCCESS;
            }
        } else {
            s = emptyfuel;
            Flying = EMPTYFUEL;
        }
        return s;
    }

    public int computeDeltaV() {
        // return velocity + gravity - burn amount
        return Velocity + Gravity - Burn;
    }

    public void adjustForBurn(int burnAmount) {
        // set burn to burnamount requested
        // save previousAltitude with current Altitude
        // set new velocity to result of computeDeltaV function.
        // subtract speed from Altitude
        // subtract burn amount fuel used from tank
        Burn = burnAmount;
        PrevAltitude = Altitude;
        Velocity = computeDeltaV();
        Altitude -= Velocity;
        Fuel -= Burn;
    }

    public boolean stillFlying() {
        // return true if altitude is positive
        return Altitude > 0;
    }
    public boolean outOfFuel() {
        // return true if fuel is less than or equal to zero
        return Fuel <= 0;
    }

    public DescentEvent getStatus(int tick) {
        int st = 0;
        if (checkFinalStatus().equals(dead)) {
            st = DEAD;
        } else if (checkFinalStatus().equals(crashed)) {
            st = CRASHED;
        } else if (checkFinalStatus().equals(success)) {
            st = SUCCESS;
        } else if (checkFinalStatus().equals(emptyfuel)) {
            st = EMPTYFUEL;
        }
        return new DescentEvent(tick, Velocity, Fuel, Altitude, st);
        // create a return a new DescentEvent object
        // filled in with the state of the vehicle.
    }

}
