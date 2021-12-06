package game.com.anish.world;

import java.awt.Color;

public class Player extends Creature implements Runnable{

    private int xPos;
    private int yPos;

    public Player(Color color, World world, int xPos, int yPos) {
        super(color, (char) 2, world);
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getxPos(){
        return xPos;
    }

    public int getyPos(){
        return yPos;
    }

    public void setxPos(int xPos){
        this.xPos = xPos;
    }

    public void setyPos(int yPos){
        this.yPos = yPos;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        
    }
}
