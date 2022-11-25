public class OnBoardComputer implements BurnStream {

    @Override
    public int getNextBurn(DescentEvent status) {
        int burn = 0;
        if(status.getVelocity() <= 1500 && status.getVelocity() > 1000 && status.getAltitude() < 14000) burn = 200;
        if(status.getVelocity() == 1000 && status.getAltitude() < 10000) burn = 100;
        if(status.getAltitude() > 5000 && status.getAltitude() < 6000) burn = 150;
        if(status.getAltitude() < 5000) burn = 200;
        if(status.getVelocity() == 150) burn = 150;
        if(status.getVelocity() == 100 && status.getAltitude() > 100) burn = 100;
        if(status.getVelocity() != 150 && status.getAltitude() < 100) burn = 200 - (status.getAltitude() - 1);
        if(status.getAltitude() == 1) burn = 100 + (status.getVelocity() - 2);
        System.out.println(burn);
        return burn;
    }
}