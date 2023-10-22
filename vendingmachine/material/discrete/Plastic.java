package vendingmachine.material.discrete;

public class Plastic extends DiscreteRealObject {
    private final String plasticDesc;
    private final IC ic;
    private final Magnetic magnetic;

    public Plastic(String plasticDesc, IC ic, Magnetic magnetic) {
        this.plasticDesc = plasticDesc;
        this.ic = ic;
        this.magnetic = magnetic;
    }


    public String getPlasticDesc() {
        return plasticDesc;
    }

    public IC getIc() {
        return ic;
    }

    public Magnetic getMagnetic() {
        return magnetic;
    }

    @Override
    public String toString() {
        return plasticDesc;
    }
}
