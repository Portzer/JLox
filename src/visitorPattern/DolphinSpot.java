package visitorPattern;

public class DolphinSpot implements ScenerySpot{
    @Override
    public void accept(Visitor visitor) {
        visitor.visitorDolphinSpot(this);
    }

    @Override
    public Integer ticketRate() {
        return 20;
    }
}
