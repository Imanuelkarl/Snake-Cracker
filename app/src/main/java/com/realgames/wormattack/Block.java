package com.realgames.wormattack;
import android.graphics.*;

public class Block extends Sprite
{
	boolean active;
	String number = new String();
	int score;
	CollisionRect collider= new CollisionRect();
	CollisionRect boundLeft=new CollisionRect();
	CollisionRect boundRight=new CollisionRect();
	Paint paint = new Paint();
	public Block(){
		active=true;
	}
	public boolean powerUp(boolean yes){
		
		return true;
	}
	public CollisionRect getRight(){
		return boundRight;
	}
	public CollisionRect getLeft(){
		return boundLeft;
	}
	public void setPowerUp(PowerUp power){
		
	}
	public void setScore(int score){
		this.score=score;
	}
	public void placeBlock(){
		put(canvas);
	}
	public int getScore(){
		return score;
	}
	public Boolean isActive(){
		return active;
	}
	public void setActive(boolean act){
		this.active=act;
	}
	@Override
	public void put(Canvas canvas)
	{
		this.collider.top=this.getCenterY()+this.getHeight()/8;
		this.collider.left=this.getCenterX()-this.getWidth()/2;
		this.collider.bottom=this.getCenterY()+this.getHeight()/2;
		this.collider.right=this.getCenterX()+this.getWidth()/2;
		this.setCollisionRect(collider);
		boundLeft.left=getCenterX()-getWidth()/2-getHeight()/6;
		boundLeft.right=getCenterX()-getWidth()/2;
		boundLeft.top=this.getCenterY()-this.getHeight()/2;
		boundLeft.bottom=this.getCenterY()+this.getHeight()/2-this.getHeight()/16;
		boundRight.right=getCenterX()+getWidth()/2+getHeight()/6;
		boundRight.left=getCenterX()+getWidth()/2;
		boundRight.top=this.getCenterY()-this.getHeight()/2;
		boundRight.bottom=this.getCenterY()+this.getHeight()/4-this.getHeight()/16;
		// TODO: Implement this method
		super.put(canvas);
		paint.setTextSize(getWidth()/2);
		paint.setColor(Color.BLACK);
		canvas.drawText(number+score,getCenterX()-40,getCenterY()+20,paint);
	}
}
