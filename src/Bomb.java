
public class Bomb extends Tile {
    
    public Bomb() {
        // TODO Auto-generated constructor stub
        super.changeCoverTo(false);
        super.changeNumberTo(10);
    }

    @Override
    public void uncover() {
        // TODO Auto-generated method stub
        super.changeCoverTo(true);

    }
    
    public String explode() {
        return "boom";
    }
    

}
