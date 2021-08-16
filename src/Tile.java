
public abstract class Tile {

    public abstract void uncover();
    
    private boolean cover;
    private int number;
    private boolean flag = false;
    
    public boolean getCover() {
        return cover;
    }
    
    public void changeCoverTo(boolean x) {
        cover = x;
    }
    
    public int getNumber() {
        return number;
    }
    public void changeNumberTo(int x) {
        number = x;
    }
    
    public boolean getFlag() {
        return flag;
    }
    public void changeFlag() {
        flag = !flag;
    }
    public String hello() {
        return null;
    }
    
    public abstract String explode();

}
