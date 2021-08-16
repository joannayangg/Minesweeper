import java.util.*;

public class NumberTile extends Tile {
    
    public NumberTile(int x) {
        // TODO Auto-generated constructor stub
        super.changeCoverTo(false);
        super.changeNumberTo(x);
        
    }

    @Override
    public void uncover() {
        // TODO Auto-generated method stub
        super.changeCoverTo(true);
        super.hello();
        
       // List<Integer> n = new List<Integer>();

    }
    
    public String explode() {
        return "number";
    }
    

}
