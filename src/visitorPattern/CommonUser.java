package visitorPattern;

public class CommonUser implements Visitor{
    @Override
    public void visitorLeopardSpot(LeopardSpot spot) {
        int v = spot.ticketRate();
        System.out.println("普通游客浏览豹子馆的票价" + v);
    }

    @Override
    public void visitorDolphinSpot(DolphinSpot spot) {
        int v = spot.ticketRate();
        System.out.println("普通游客浏览海豚馆的票价" + v);
    }
}
