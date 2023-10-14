package visitorPattern;

public class VisitorPattern {
    public static void main(String[] args) {
        Zoo zoo = new Zoo();
        zoo.add(new DolphinSpot());
        zoo.add(new LeopardSpot());
        zoo.accept(new Student());
        System.out.println("----------------------------");
        zoo.accept(new CommonUser());
    }
}
