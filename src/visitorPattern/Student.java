package visitorPattern;

public class Student implements Visitor{

    @Override
    public void visitorLeopardSpot(LeopardSpot spot) {
        int v = (int) (spot.ticketRate() * 0.5);
        System.out.println("学生浏览豹子馆的票价" + v);
    }

    @Override
    public void visitorDolphinSpot(DolphinSpot spot) {
        int v = (int) (spot.ticketRate() * 0.5);
        System.out.println("学生浏览海豚馆的票价" + v);
    }
}
