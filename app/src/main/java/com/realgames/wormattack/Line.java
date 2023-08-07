package com.realgames.wormattack;
import android.graphics.*;

public class Line extends Sprite
{
	CollisionRect collider = new CollisionRect();
	CollisionRect boundLeft=new CollisionRect();
	CollisionRect boundRight=new CollisionRect();
	Line(){
		
	}
	CollisionRect getRight(){
		return boundRight;
	}
	CollisionRect getLeft(){
		return boundLeft;
	}
	@Override
	public void put(Canvas canvas)
	{
		this.collider.top=this.getCenterY()+this.getHeight()/4;
		this.collider.left=this.getCenterX()-this.getWidth()/2;
		this.collider.bottom=this.getCenterY()+this.getHeight()/2+2;
		this.collider.right=this.getCenterX()+this.getWidth()/2;
		this.setCollisionRect(collider);
		boundLeft.left=getCenterX()-getWidth()/2-getHeight()/6;
		boundLeft.right=getCenterX()-getWidth()/2;
		boundLeft.top=this.getCenterY()-this.getHeight()/2-getHeight()/4;
		boundLeft.bottom=this.getCenterY()+this.getHeight()/2-this.getHeight()/16;
		boundRight.right=getCenterX()+getWidth()/2+getHeight()/6;
		boundRight.left=getCenterX()+getWidth()/2;
		boundRight.top=this.getCenterY()-this.getHeight()/2-getHeight()/4;
		boundRight.bottom=this.getCenterY()+this.getHeight()/2-this.getHeight()/16;
		// TODO: Implement this method
		super.put(canvas);
		
	}
}
