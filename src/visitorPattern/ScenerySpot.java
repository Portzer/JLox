package visitorPattern;

public interface ScenerySpot {
    void accept(Visitor visitor);

    Integer ticketRate();
}
