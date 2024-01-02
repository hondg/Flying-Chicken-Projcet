public class Basket {
    private int x=0;
    private int y=380;
    private int move=2;
    
    public Basket(int y){
        this.y=y;
    }
    public Basket(int x,int move){
        this.x=x;
        this.move=move;
    }
    public Basket(int x,int y,int move){
        this.x=x;
        this.move=move;
        this.y=y;
    }
    public int getX(){
        return x;
    }
    public void setX(int x){
        this.x=x;
    }
    public int getY(){
        return y;
    }
    public void setY(int y){
        this.y=y;
    }
    public int getMove(){
        return move;
    }
    public void setMove(int move){
        this.move=move;
    }
    public boolean combo(int numberofcollision){
        if(numberofcollision==3)
            return true;
        else
            return false;
    }
}
