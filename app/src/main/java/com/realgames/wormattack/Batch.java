package com.realgames.wormattack;
import android.graphics.*;

public class Batch
{
	float vx,vy;
	float vwidth,vheight;
	Canvas canvas;
	Bitmap bits;
	
	Camera camera=new Camera();
	public void setCanvas(Canvas canvas)
	{
		this.canvas = canvas;
	}

	public Canvas getCanvas()
	{
		return canvas;
	}
	public void draw(){
		
	}
	public void setupCamera(Camera cam){
		camera=cam;
	}
	
	public void drawSprite(Sprite sprite){
		vx=sprite.getCenterX()-camera.getStartX();
		vy=sprite.getCenterY()-camera.getStartY();
		vwidth=sprite.getWidth()*(camera.view.getWidth()/camera.getWidth());
		vheight=sprite.getHeight()*(camera.view.getHeight()/camera.getHeight());
		bits=sprite.getTexture();
		if(sprite.getTexture()!=null){
			Rect size = sprite.getSize();
			RectF rect = new RectF(vx-vwidth/2,vy-vheight/2,vx+vwidth/2,vy+vheight/2);
			sprite.getTexture().prepareToDraw();
			sprite.getCanvas().drawBitmap(sprite.getTexture(),size,rect,null);
		}
		else{
			Paint paint=sprite.getPaint();
			sprite.canvas.drawRect(vx-vwidth/2,vy-vheight/2,vx+vwidth/2,vy+vheight/2,paint);
		}
		
	}
	public void drawText(Sprite sprite,int score){
		String number="";
		vx=sprite.getCenterX()-camera.getStartX();
		vy=sprite.getCenterY()-camera.getStartY();
		vwidth=sprite.getWidth()*(camera.view.getWidth()/camera.getWidth());
		vheight=sprite.getHeight()*(camera.view.getHeight()/camera.getHeight());
		
		sprite.paint.setTextSize(vwidth/2);
		sprite.paint.setColor(Color.BLACK);
		sprite.canvas.drawText(number+score,vx-40,vy+20,sprite.paint);
	}
	public void drawText(Sprite sprite,String score,float x,float y,float size){
		float vsize;
		String number="";
		vx=x-camera.getStartX();
		vy=y-camera.getStartY();
		vwidth=sprite.getWidth()*(camera.view.getWidth()/camera.getWidth());
		vheight=sprite.getHeight()*(camera.view.getHeight()/camera.getHeight());
		vsize=size*(camera.view.getWidth()/camera.getWidth());
		sprite.paint.setTextSize(vsize);
		sprite.paint.setColor(Color.BLACK);
		sprite.canvas.drawText(number+score,vx-40,vy+20,sprite.paint);
	}
	public void drawText(Canvas canvas,String score,float x,float y,float size,Paint paint){
		float vsize;
		String number="";
		vx=x-camera.getStartX();
		vy=y-camera.getStartY();
		
		vsize=size*(camera.view.getWidth()/camera.getWidth());
		
		canvas.drawText(score,vx,vy,paint);
	}
	public void drawCircle(Sprite sprite){
		vx=sprite.getCenterX()-camera.getStartX();
		vy=sprite.getCenterY()-camera.getStartY();
		vwidth=sprite.getWidth()*(camera.view.getWidth()/camera.getWidth());
		vheight=sprite.getHeight()*(camera.view.getHeight()/camera.getHeight());
		sprite.getCanvas().drawCircle(vx,vy,vwidth/2,sprite.getPaint());
	}
}
