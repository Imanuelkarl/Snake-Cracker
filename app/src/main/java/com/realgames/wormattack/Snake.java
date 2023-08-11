package com.realgames.wormattack;
import java.util.*;

import android.graphics.*;
public class Snake extends Sprite
{
	ArrayList<Sprite> body = new ArrayList<>();
	private boolean isAlive;
	private String num="";
	private int stime;
	private boolean right;
	private float space=0;
	int id;
	int i=0;
	private boolean left;
	private ArrayList<Float> data=new ArrayList<>();
	private CollisionRect collider = new CollisionRect();
	public Snake(){
		isAlive=true;
		left=false;
		right=false;
		stime=0;
		id=0;
	}

	public void setData(ArrayList<Float> data)
	{
		this.data = data;
	}

	public ArrayList<Float> getData()
	{
		return data;
	}
	public void adjust(int speed){
		if(stime%speed==0){
			
		}
		stime++;
	}

	@Override
	public void setCenterY(float y)
	{
		// TODO: Implement this method
		space=getCenterY()-y;
		super.setCenterY(y);
	}
	
	@Override
	public void setCenterX(float x)
	{
		
		if(left||right){
			if(left){
				if(x>getCenterX()){
					
				}
				else{
					data.add(x);
					super.setCenterX(x);
					
				}
			}
			if(right){
				if(x<getCenterX()){

				}
				else{
					data.add(x);
					super.setCenterX(x);
				}
			}
		}
		else{
			data.add(x);
			super.setCenterX(x);
		}
	}
	public void adjuster(int time,int speed){
		float newer=this.getCenterX();
		float init;
		float newery=this.getCenterY();
		float inity;
		if(i<body.size()){
			if(time%1==0){
				init=body.get(i).getCenterX();
				inity=body.get(i).getCenterY();
				body.get(i).setCenterX(newer);
				body.get(i).setCenterY(newery);
				newer = init;
				newery=inity;
				i++;
			}
		}
		else{
			i=0;
		}
	}
	public void stopLeft(boolean xyes){
		left=xyes;
	}
	public void stopRight(boolean yyes){
		right=yyes;
	}
	public Boolean rightIsLocked(){
		return right;
	}
	public Boolean leftIsLocked(){
		return left;
	}
	public void addBody(int amount){
		for(int i=0;i<amount;i++){
			Sprite bod = new Sprite();
			bod.drawBatch(batch);
			bod.setRadius(radius);
			float x=getCenterX();
			if(body.size()!=0){
				x=body.get(body.size()-1).getCenterX();
			}
			float y=getCenterY()+(2*radius*body.size());
			bod.setCenter(x,y);
			bod.setPaint(paint);
			body.add(bod);
		}
	}
	public void removeBody(){
		if(body.size()!=0){
			body.remove(body.get(0));
		}
		else{
			isAlive=false;
		}
	}
	public int getSnakeLength(){
		return body.size();
	}
	@Override
	public void putCircle(Canvas canvas)
	{
		Paint tp = new Paint();
		num=""+body.size();
		tp.setTextSize(radius);
		tp.setColor(Color.WHITE);
		batch.drawText(canvas,num,this.getCenterX()-5,this.getCenterY()-40,radius,tp);
		for(int i=0;i<body.size();i++){
			
			if(i+1<body.size()){
				float pack=body.get(i).getCenterX()-body.get(i+1).getCenterX();
				if(Math.abs(pack)>radius*2){
					
				}
			}
			body.get(i).putCircle(canvas);
			
		}
		if(body.size()!=0){
		
		}
		this.collider.top=this.getCenterY()-this.getHeight()/2;
		this.collider.left=this.getCenterX()-this.radius+getWidth()/6;
		this.collider.bottom=this.getCenterY()+this.getHeight()/2;
		this.collider.right=this.getCenterX()+this.radius-getWidth()/6;
		this.setCollisionRect(collider);
	}
	public ArrayList<Sprite> getBody(){
		return body;
	}
	public void move(ArrayList<Float> data){
	
		float newer=this.getCenterX();
		float init;
		float yco=0;
		for(int i=0;i<body.size();i++){
			init=body.get(i).getCenterX();
			yco=Math.abs(newer-init);
			if(yco>=radius*2){
				yco=radius*2;
			}
			if(i!=0){
				body.get(i).setCenterY(body.get(i-1).getCenterY()-yco+radius*2);
			}
		
			body.get(i).setCenterX(newer);
			if(i==body.size()-1){
				if(data.size()>0){
					data.remove(id);
				}
			}
			newer = init;
		}
		}
	public void move(){
		
		float newer=this.getCenterX();
		float init;
		float newery=this.getCenterY();
		float inity;
		for(int i=0;i<body.size();i++){
			init=body.get(i).getCenterX();
			inity=body.get(i).getCenterY();
			body.get(i).setCenterX(newer);
			body.get(i).setCenterY(newery);
			newer = init;
			newery=inity;
		}

	}
	
	
	public void move(int g){
		move(data);
	}
}
