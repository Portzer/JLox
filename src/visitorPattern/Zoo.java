package visitorPattern;

import java.util.ArrayList;
import java.util.List;

public class Zoo {

    private List<ScenerySpot> spotList = new ArrayList<>();

    public void accept(Visitor visitor) {
        for (ScenerySpot scenerySpot : spotList) {
            scenerySpot.accept(visitor);
        }
    }

    public void add(ScenerySpot spot) {
        spotList.add(spot);
    }

    public void remove(ScenerySpot spot) {
        spotList.remove(spot);
    }
}
