package com.realgames.wormattack;
import android.content.*;
import android.graphics.*;
import android.content.res.*;

public class Sprite
{
	Bitmap bits;
	Context context;
	CollisionRect space=new CollisionRect();
	AssetManager assets;
	Resources res;
	float width;
	float radius;
	float height;
	Batch mybatch;
	PointF end=new PointF();
	PointF center= new PointF();
	PointF origin= new PointF();
	Paint paint;
	Canvas canvas;
	int bitrow,bitcolumn,bitpad,n,m;
	RectF rect = new RectF();
	Rect tsize=new Rect();
	Batch batch;
	public Sprite(){
		paint= new Paint();
		space=new CollisionRect();
		bitrow=1;
		bitcolumn=1;
		bitpad=0;
		n=0;
		m=0;
	}
	public Sprite(Context context){
		this.context=context;
		paint=new Paint();
	}
	public void setTexture(Bitmap bitmap){
		bits=bitmap;
	}
	public void setRadius(float radius){
		this.radius=radius;
		this.height=2*radius;
		this.width=2*radius;
	}
	public void setPaint(Paint paint){
		this.paint=paint;
	}
	public void setStart(float x,float y){
		rect.top=y;
		rect.left=x;
		center.x=x-width/2;
		center.y=y;
	}
	public void setStartX(float x){
		this.origin.x=x;
	}
	float getWidth(){
		return width;
	}
	float getHeight(){
		return height;
	}
	float getStartX(){
		return this.origin.x;
	}
	float getStartY(){
		return this.origin.y;
	}
	public void setStartY(int y){
		this.origin.y=y;
	}
	public void setScaleX(int scale){
		
	}
	public void setEnd(float x,float y){
		this.end.x=x;
		this.end.y=y;
	}
	public PointF getEnd(){
		return end;
	}
	public float getEndX(){
		return this.end.x;
	}
	public float getEndY(){
		return this.end.y;
	}
	public void setCenter(float x,float y){
		rect.right=x+width/2;
		rect.left=x-width/2;
		rect.top=y-height/2;
		rect.bottom=y+height/2;
		this.center.x=x;
		this.center.y=y;
		origin.x=x-width/2;
		origin.y=y-height/2;
	}
	public void setCenterX(float x){
		this.center.x=x;
		origin.x=x-width/2;
	}
	public void setCenterY(float y){
		this.center.y=y;
		origin.y=y-height/2;
	}
	public void setScaleY(int scale){
		
	}
	public void setTextureData(int row,int column,int pad,int positionX,int positionY){
		bitcolumn=column;
		bitrow=row;
		bitpad=pad;
		n=positionX;
		m=positionY;
		tsize=new Rect(n*bits.getWidth()/bitcolumn+bitpad,m*bits.getHeight()/bitrow+bitpad,(n+1)*bits.getWidth()/bitcolumn-bitpad,(m+1)*bits.getHeight()/bitrow-bitpad);
	}
	public Rect getSize(){
		tsize=new Rect(n*bits.getWidth()/bitcolumn+bitpad,m*bits.getHeight()/bitrow+bitpad,(n+1)*bits.getWidth()/bitcolumn-bitpad,(m+1)*bits.getHeight()/bitrow-bitpad);
		return tsize;
	}
	public void setWidth(float width){
		this.width=width;
	}
	public void setHeight(float height){
		this.height=height;
	}
	public Bitmap getTexture(){
		return bits;
	}
	public float getCenterX(){
		
		return center.x;
	}
	public float getCenterY(){
		return center.y;
	}
	public Paint getPaint(){
		return paint;
	}
	public void draw(Canvas canvas){
		this.canvas=canvas;
		
		canvas.drawRect(center.x-width/2,center.y-height/2,center.x+width/2,center.y+height/2,paint);
	}
	public void drawBatch(Batch batvh){
		this.batch=batvh;
	}
	
	public void put(Canvas canvas){
		this.canvas=canvas;
		if(batch!=null){
			batch.drawSprite(this);
		}
		else{
		if(bits!=null){
			Rect size = new Rect(n*bits.getWidth()/bitcolumn+bitpad,m*bits.getHeight()/bitrow+bitpad,(n+1)*bits.getWidth()/bitcolumn-bitpad,(m+1)*bits.getHeight()/bitrow-bitpad);
			rect = new RectF(center.x-width/2,center.y-height/2,center.x+width/2,center.y+height/2);
			bits.prepareToDraw();
			canvas.drawBitmap(bits,size,rect,null);
		}
		else{
			draw(canvas);
		}
	
		}
	}
	public void putCircle(Canvas canvas){
		this.canvas=canvas;
		if(batch!=null){
			batch.drawCircle(this);
		}
		else{
			canvas.drawCircle(getCenterX(),getCenterY(),radius,paint);
		}
	}
	public Canvas getCanvas(){
		return canvas;
	}
	public RectF getBound(){
		RectF size =new RectF(center.x-width/2,center.y-height/2,center.x+width/2,center.y+height/2);
		return size;
	}
	 public void setCollisionRect(RectF collider){
		space=(CollisionRect)collider;
		space.top=collider.top;
		space.bottom=collider.bottom;
		space.left=collider.left;
		space.right=collider.right;
	}
	public CollisionRect getCollisionRect(){
		return space;
	}
}
