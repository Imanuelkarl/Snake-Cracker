package com.realgames.wormattack;
import android.graphics.*;
public class WinBody extends Sprite
{
	private String number="";
	private int score=0;
	private boolean active;
	private CollisionRect collider = new CollisionRect();
	private Paint paint= new Paint();
	public WinBody(){
		active=true;
	}
	public void setScore(int score){
		this.score=score;
	}
	public int getScore(){
		return this.score;
	}
	public void setActive(boolean act){
		active=act;
	}
	public Boolean isActive(){
		return active;
	}
	@Override
	public void putCircle(Canvas canvas)
	{
		// TODO: Implement this method
		super.putCircle(canvas);
		paint.setTextSize(radius);
		paint.setColor(Color.WHITE);
		batch.drawText(canvas,number+score,getCenterX()-5,getCenterY()-40,radius,paint);
		this.collider.top=this.getCenterY()-this.getHeight()/2;
		this.collider.left=this.getCenterX()-this.getWidth()/2;
		this.collider.bottom=this.getCenterY()+this.getHeight()/2;
		this.collider.right=this.getCenterX()+this.getWidth()/2;
		this.setCollisionRect(collider);
	}
}
