package com.realgames.wormattack;
import android.graphics.*;

public class CollisionRect extends RectF
{
	float height;
	float width;
	float startX;
	float startY;
	float centerX;
	float centerY;
	boolean collided=false;
	
	public Boolean hits(CollisionRect space){
		
		if(this.left>space.left&&this.right<space.right){
			if(this.top>space.top&&this.bottom<space.bottom){
			
			}
			else{
				collided=false;
			}
			
		}
		return (this.left>space.left&&this.right<space.right&&
			this.top>space.top&&this.bottom<space.bottom);
	}
}
