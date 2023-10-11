package demo;

public class Beignet extends Pastry{
    @Override
    void accept(PastryVisitor visitor) {
        visitor.visitBeignet(this);
    }
}
