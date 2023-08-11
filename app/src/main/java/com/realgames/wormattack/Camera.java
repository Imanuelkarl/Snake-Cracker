package com.realgames.wormattack;
import android.renderscript.*;
import android.view.*;
import org.apache.http.conn.util.*;

public class Camera
{
	public Camera(View view){
		this.view=view;
		this.width=view.getWidth();
		this.height=view.getHeight();
		position=new Float3();
		direction=new Float3();
	}
	public View view;
	public Float3 position;
	public Float3 direction;
	float startX=0,startY=0;
	float endX,endY;
	float width,height;
	public Camera(){
		position=new Float3();
		direction=new Float3();
	}

	public void setStartX(float startX)
	{
		this.startX = startX;
	}

	public float getStartX()
	{
		return startX;
	}

	public void setStartY(float startY)
	{
		this.startY = startY;
	}

	public float getStartY()
	{
		return startY;
	}
	public void setWidth(float width)
	{
		this.width = width;
	}
	
	public float getWidth()
	{
		return width;
	}

	public void setHeight(float height)
	{
		this.height = height;
	}

	public float getHeight()
	{
		return height;
	}
	public void setup(float x,float y,float width,float height){
		position.x=x;
		position.y=y;
		this.width=width;
		this.height=height;
	}
	public void translate(Float3 change){
		position.x=position.x+change.x;
		position.y=position.y+change.y;
	}
	public void translate(float x,float y,float z){
		
	}
	public void translate(float x,float y){
		startX+=x;
		startY+=y;
		position.x=x;
		position.y=y;
	}
	
}
