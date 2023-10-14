package visitorPattern;

public class LeopardSpot implements ScenerySpot{
    @Override
    public void accept(Visitor visitor) {
        visitor.visitorLeopardSpot(this);
    }

    @Override
    public Integer ticketRate() {
        return 15;
    }
}
