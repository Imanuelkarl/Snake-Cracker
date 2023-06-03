package com.realgames.wormattack;
import java.util.*;
import android.graphics.*;
public class Row
{
	ArrayList<Block> blocks= new ArrayList<>();
	ArrayList<Line> lines=new ArrayList<>();
	ArrayList<WinBody> winbodies= new ArrayList<>();
	float rowY;
	boolean moving;
	boolean empty;
	Canvas canvas;
	Row(){
		empty=false;
		moving=true;
	}
	public boolean isMoving(){
		return moving;
	}
	public void setMoving(boolean motion){
		this.moving=motion;
	}
	public void delete(){
		
	}
	public void Blocks(Block blk){
		blocks.add(blk);
	}
	public void Line(Line line){
		lines.add(line);
	}
	public void winbody(WinBody winb){
		winbodies.add(winb);
	}
	public void setRowY(float y){
		rowY=y;
	}
	public Float getRowY(){
		return rowY;
	}
	public void noBlock(boolean yes){
		empty=yes;
	}
	public void playOn(Canvas canvas){
		if(!empty){
			for(Block block: blocks){
				if(block.isActive()){
					block.setCenterY(rowY);
					block.put(canvas);
				}
			}
		}
		for(Line line:lines){
			line.setCenterY(rowY);
			line.put(canvas);
		}
		for(WinBody winbd:winbodies){
			if(winbd.isActive()){
				winbd.setCenterY(rowY);
				winbd.putCircle(canvas);
			}
			
		}
	}
	public ArrayList<Block> getBlocks(){
		return blocks;
	}
	public ArrayList<WinBody> getWinBodies(){
		return winbodies;
	}
	public ArrayList<Line> getLines(){
		return lines;
	}
}
