package com.realgames.wormattack;
import android.view.*;
import android.content.*;
import android.graphics.*;
import java.util.*;
import java.io.*;
import android.app.Activity;
import android.widget.*;
import android.media.*;
import android.content.res.*;

public class Game extends View
{
	private Paint paint;
	private int score;
	private int highscore;
	private int time=0;
	private int speed=5;
	private boolean key;
	private Context cont;
	private float rad;
	private Activity act;
	private int timer;
	private ArrayList<Sprite> snakeMotion=new ArrayList<>();
	private boolean spd;
	private int dir;
	private Block collided = new Block();
	private Block colleft=new Block();
	private Block collright=new Block();
	private Line colline=new Line();
	private Line lefts=new Line();
	private Line rights=new Line();
	private boolean pause;
	private boolean rendering;
	private Sprite pausebtn;
	private int create=0;
	private int blockdim;
	private int level;
	private boolean gameStarted;
	private Snake snake=new Snake();
	private Bitmap myblocks;
	private LinearLayout lin;
	private View gameover;
	private int max,min;
	private int rows;
	private int madeRows;
	private Bitmap btn;
	private int endLineTop,endLineBottom;
	private TextView scored;
	private TextView higsc;
	private MediaPlayer mpWin = new MediaPlayer();
	private MediaPlayer fail = new MediaPlayer();
	private ArrayList<Row> blck=new ArrayList<>();
	private ArrayList<Float> datas=new ArrayList<>();
	private SharedPreferences sp;
	private float initialTouchX;
	private Camera camera;
	private Batch batch;
	private Bitmap pausebg,playtexture,hometexture,restarttexture;
	private Sprite homebtn,playbtn,restart,pauseBack;
	boolean isOver;
	private int fact;
	private int crow;
	private boolean isMoving;
	public Game(Context context){
		super(context);
		cont=context;
		paint= new Paint();
		pausebtn=new Sprite();
		gameStarted=false;
		key=true;
		lin=null;
		gameover=null;
		loadAssets();
		rad=23;
		spd=false;
		max=20;
		level=1;
		endLineTop=0;
		endLineBottom=10;
		madeRows=0;
		min=1;
		dir=0;
		fact=1;
		homebtn=new Sprite();
		playbtn=new Sprite();
		restart=new Sprite();
		pauseBack=new Sprite();
		rows=5*level+10;
		rendering=false;
		pause=false;
		camera=new Camera(this);
		batch=new Batch();
		crow=blck.size();
		isMoving=false;
	}
	public void saveTo(SharedPreferences sp){
		this.sp =sp;
		sp.edit().putString("mode","level").commit();
		
	}
	public void callGameOver(View gameo){
		gameover=gameo;
	}
	private WinBody createWinBody(int n){
		WinBody winbody=new WinBody();
		winbody.drawBatch(batch);
		Paint pp=new Paint();
		float blockY=-getMeasuredWidth()/2;
		float blockX=(n+1)*getMeasuredWidth()/10+n*getMeasuredWidth()/10;
		winbody.setCenter(blockX,blockY);
		winbody.setScore(getRandom(1,8));
		winbody.setRadius(rad);
		pp.setStyle(Paint.Style.FILL);
		pp.setColor(Color.CYAN);
		winbody.setPaint(pp);

		return winbody;
	}
	public void activity(Activity act){
		this.act=act;
	}
	public void makeView(){
		act.setContentView(this);
	}
	public void endGame(){
		if(score>highscore){
			sp.edit().putString("hs",String.valueOf(score)).commit();
			highscore=score;
		}
		scored.setText("SCORE:"+score);
		higsc.setText("BEST:"+highscore);
		lin.removeAllViews();
		gameover.setVisibility(View.VISIBLE);
		lin.addView(gameover);
	}
	private void loadAssets() { 
		try { 
            this.myblocks= BitmapFactory.decodeStream(cont.getAssets().open("blocks.png"));
			btn=BitmapFactory.decodeStream(cont.getAssets().open("blocks_frame0010.png"));
			pausebg= BitmapFactory.decodeStream(cont.getAssets().open("pause_bg.png"));
			playtexture=BitmapFactory.decodeStream(cont.getAssets().open("playbtn.png"));
			hometexture= BitmapFactory.decodeStream(cont.getAssets().open("homebtn.png"));
			restarttexture=BitmapFactory.decodeStream(cont.getAssets().open("restartbtn.png"));
			MediaPlayer mediaPlayer = new MediaPlayer();
			
			AssetFileDescriptor afd = cont.getAssets().openFd("Wallpaper.mp3");
			mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
			mediaPlayer.setLooping(true); // set looping
			mediaPlayer.prepare();
		
			AssetFileDescriptor afd2 = cont.getAssets().openFd("life.mp3");
			mpWin.setDataSource(afd2.getFileDescriptor(), afd2.getStartOffset(), afd2.getLength());
			mpWin.prepare();
			AssetFileDescriptor afd3 = cont.getAssets().openFd("kick.mp3");
			fail.setDataSource(afd3.getFileDescriptor(), afd3.getStartOffset(), afd3.getLength());
			fail.prepare();
            return; 
        } 
        catch (IOException iOException) { 
            return; 
        } 
    } 
	private ArrayList<Integer> positions(int n){
		ArrayList<Integer> list=new ArrayList<>();
		for(int i=0;i<n;i++){
			list.add(i);
		}
		return list;
	}
	public void scoreText(TextView text){
		scored=text;
		scored.setText("SCORE:"+score);
	}
	public void setHighScore(TextView text){
		higsc=text;
		
	}
	private Row createRow(){
		Row row = new Row();
		row.setRowY(0-blockdim*(2*crow));
		if(blck.size()==0){
			for(int i=0;i<5;i++){
				Block boll= new Block();
				boll=createBlock(i);
				boll.setScore(getRandom(1,2));
				row.Blocks(boll);

			}
		}
		else{
			int nb=getRandom(0,10);
			int nl=getRandom(0,15);
			int nw=getRandom(0,9);
			if(nl>3){
				nl=0;
			}
			if(nb>5){
				nb=getRandom(0,4);
			}
			if(nw>3){
				nw=0;
			}
			ArrayList<Integer> bpl = positions(5);
			ArrayList<Integer> lpl=positions(4);
			int bp,inb;
			int lp,inl;
			int wp,inw;
			for(int i=0;i<nb;i++){
				if(bpl.size()!=0){
					inb=getRandom(0,bpl.size()-1);
					bp=bpl.get(inb);
					Block blo = createBlock(bp);
					row.Blocks(blo);
					bpl.remove(inb);
				}
			}
			if(nb==5){
				row.getBlocks().get(getRandom(0,4)).setScore(getRandom(1,min));
			}
			lpl=bpl;
			for(int i=0;i<nw;i++){
				if(bpl.size()!=0){
					inw=getRandom(0,bpl.size()-1);
					wp=bpl.get(inw);
					WinBody winbd=new WinBody();
					winbd=createWinBody(wp);
					row.winbody(winbd);
					bpl.remove(inw);
				}
			}
			for(int i=0;i<nl;i++){
				if(lpl.size()!=0){
					inl=getRandom(0,lpl.size()-1);
					lp=lpl.get(inl);
					Line lin=new Line();
					lin=createLine(lp);
					row.Line(lin);
					lpl.remove(inl);
				}
			}
		}
		return row;
	}
	private void createSnake(){
		paint.setColor(Color.CYAN);
		snake.drawBatch(batch);
		snake.setRadius(rad);
		snake.setPaint(paint);
		snake.setCenter(getMeasuredWidth()/2,getMeasuredHeight()/2);
		snake.addBody(1);
	}
	@Override
	protected void onDraw(Canvas canvas)
	{
	
		//onCreate
		if(!gameStarted){
			camera=new Camera(this);
			batch=new Batch();
			camera.setup(0,0,getWidth(),getHeight());
			batch.setupCamera(camera);
			sp.edit().putString("mode","level").commit();
			pausebtn.setCenter(getMeasuredWidth()-55,55);
			pausebtn.setTexture(btn);
			pausebtn.setHeight(100);
			pausebtn.setWidth(100);
			blockdim=getMeasuredWidth()/5;
			homebtn.setCenter(getWidth()/2,getHeight()/2+getHeight()/8);
			homebtn.setTexture(hometexture);
			homebtn.setHeight(60);
			homebtn.setWidth(240);
			playbtn.setCenter(getWidth()/2,getHeight()/2);
			playbtn.setTexture(playtexture);
			playbtn.setHeight(60);
			playbtn.setWidth(240);
			restart.setCenter(getWidth()/2,getHeight()/2-getHeight()/8);
			restart.setTexture(restarttexture);
			restart.setHeight(60);
			restart.setWidth(240);
			pauseBack.setTexture(pausebg);
			pauseBack.setHeight(getHeight()-2*getHeight()/6);
			pauseBack.setWidth(getWidth()-2*getWidth()/8);
			pauseBack.setCenter(getWidth()/8+pauseBack.getWidth()/2,getHeight()/2);
			gameStarted=true;
			rendering=true;
			rad=blockdim/6;
			createSnake();
			if(sp.contains("hs")){
				highscore=Integer.parseInt(sp.getString("hs",""));
			}
			isMoving=true;
		}
		
		//render method
		if(rendering){
			
			batch.setupCamera(camera);
			paint.setColor(Color.BLACK);
			canvas.drawPaint(paint);
			if(time%2500==0){
				speed+=1;
				key=false;
				max+=5;
				min++;
			}
			time++;
			paint.setColor(Color.CYAN);
			snake.putCircle(canvas);
			
			
			
			
			if(time<60){
				if(snake.getBody().size()<5){
					if(time%12==0){
						snake.addBody(1);
					}
				}
			}
			else{
				if(isMoving){
					camera.translate(0,-speed*fact);
					snake.setCenterY(snake.getCenterY()-speed*fact);
				}
				
				snake.move();
				
			}
			
					
			if(time>60){
				
				if(!spd){
					speed=7;
					max=50;
					min=5;
					spd=true;
				}
				if(sp.getString("mode","")=="level"){
					if(madeRows<=rows){
						makegame();
					}
					else{
						paint.setColor(Color.WHITE);
						if(blck.get(blck.size()-1).getRowY()>getMeasuredHeight()/2+blockdim){
							canvas.drawRect(0,endLineTop,getMeasuredWidth(),endLineBottom,paint);
						}
					}
				}
				else{
					if(blck.size()<15){
						makegame();
					}
				}
			}
			if(snake.getCenterY()+snake.getHeight()/2>endLineTop){
				endLineTop+=speed;
				endLineBottom+=speed;
			}
			for(int i=0;i<blck.size();i++){
				blck.get(i).playOn(canvas);
				if(blck.get(i).isMoving()){
					blck.get(i).setRowY(blck.get(i).getRowY()+0);
				}
				if(blck.get(i).getRowY()>getMeasuredHeight()+blockdim){
					blck.remove(i);
				}
				if(camera.getStartY()+camera.getHeight()<blck.get(i).getRowY()-blockdim){
					blck.remove(i);
				}
			}
			
			
				datas.add(snake.getCenterX());
				
			
			
			for(Row roww:blck){
				for(Block block:roww.getBlocks()){
					if(block.isActive()){
						if(snake.getCollisionRect().intersect(block.getLeft())){
							snake.stopLeft(true);
							colleft=block;
							
						}
						if(snake.getCollisionRect().intersect(block.getRight())){
							snake.stopRight(true);
							collright=block;
							
						}
						if(snake.getBody().size()!=0){
						if(snake.getCollisionRect().intersect(block.getCollisionRect())){
							stopMoving();
							collided=block;
							
							
							if(block.getScore()!=0){
								if(timer%8==0){
									fail.seekTo(fail.getDuration());
									snake.removeBody();
									score++;
									fail.seekTo(4);
									fail.start();
									block.setScore(block.getScore()-1);
									timer=1;

								}
							}
							else{
								block.setActive(false);
								keepMoving();
							}
							timer++;
						}
						if(snake.body.size()==0){
							isOver=true;
						}
					}
					}
					if(!snake.getBound().intersect(collided.getCollisionRect())){
						keepMoving();
					}
					
				}
				if(!snake.getCollisionRect().intersect(lefts.getLeft())&&!snake.getCollisionRect().intersect(colleft.getLeft())){
					snake.stopLeft(false);
				}
				if(!snake.getCollisionRect().intersect(rights.getRight())&&!snake.getCollisionRect().intersect(collright.getRight())){
					snake.stopRight(false);
				}
				
				for(WinBody winbd:roww.getWinBodies()){
					if(winbd.isActive()){
						if(snake.getBound().intersect(winbd.getCollisionRect())){
							snake.addBody(winbd.getScore());
							mpWin.seekTo(10);
							mpWin.start();
							winbd.setActive(false);

						}
					}

				}

				for(Line line:roww.getLines()){
					if(snake.getCollisionRect().intersect(line.getLeft())){
						snake.stopLeft(true);
						lefts=line;
						
					}
					if(snake.getCollisionRect().intersect(line.getRight())){
						snake.stopRight(true);
						rights=line;
					
					}
					if(snake.getBound().intersect(line.getCollisionRect())){
						colline=line;
					}
				}
				
				pausebtn.put(canvas);
				paint.setTextSize(40);
				paint.setARGB(255,255,183,77);
				canvas.drawText("High Score: "+highscore,10,30,paint);
				canvas.drawText("Score:"+score,10,80,paint);
			}
		}
		
		//pause method
		if(pause){
			fact=0;
			paused(canvas);
		}
		else{
			fact=1;
		}
		if(isOver){
			close(canvas);
		}
		invalidate();
	}
	private void close(Canvas canvas){
		pause=true;
		paint.setColor(Color.argb(135,0,0,0));
		canvas.drawRect(0,0,getWidth(),getHeight(),paint);
		paint.setColor(Color.RED);
		canvas.drawCircle(getWidth()/2,getHeight()/2,150,paint);
		paint.setColor(Color.YELLOW);
		paint.setTextSize(70);
		canvas.drawText("Revive",getWidth()/2-100,getHeight()/2+20,paint);
		paint.setTextSize(60);
		paint.setColor(Color.WHITE);
		
		canvas.drawText("Tap to Continue",getWidth()/5,getHeight()/2+2*getHeight()/6,paint);
		
		
		
	}
	private Block createBlock(int n){
		Block blk = new Block();
		blk.drawBatch(batch);
		int posX,posY;
		posX=getRandom(0,4);
		posY=getRandom(0,1);
		float blockY=-getMeasuredWidth()/2;
		float blockX=(n+1)*getMeasuredWidth()/10+n*getMeasuredWidth()/10;
		blk.setTexture(myblocks);
		blk.setTextureData(2,5,0,posX,posY);
		blk.setCenter(blockX,blockY);
		blk.setScore(getRandom(min,max));
		blk.setHeight(blockdim-2);
		blk.setWidth(blockdim-2);
		return blk;
	}
	private Line createLine(int n){
		Line line = new Line();
		line.drawBatch(batch);
		Paint pol=new Paint();
		pol.setColor(Color.WHITE);
		float lineX=(n+1)*getMeasuredWidth()/10+n*getMeasuredWidth()/10+blockdim/2;
		float lineY=-blockdim/2;
		line.setCenter(lineX,lineY);
		line.setWidth(8);
		line.setHeight(blockdim);
		line.setPaint(pol);
		return line;
	}
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	public void parentView(LinearLayout lion){
		lin=lion;
	}
	private void paused(Canvas canvas){
		if(!isOver){
			paint.setColor(Color.argb(135,0,0,0));
			canvas.drawRect(0,0,getWidth(),getHeight(),paint);
			
			pauseBack.put(canvas);
			playbtn.put(canvas);
			restart.put(canvas);
			homebtn.put(canvas);
		}
	}
	private void makegame(){
		if(blck.size()!=0){
			if(create%(2)==0){
				if(blck.size()==1||blck.size()==2||blck.size()==3||blck.size()==4){
					key=false;
				}
				if(!key){
					if(blck.get(0).isMoving()){
						Row rows =createRow();
						rows.noBlock(true);
						key=true;
						blck.add(rows);
						crow++;
					}
				}
				else{
					Row rows=createRow();
					rows.noBlock(false);
					key=false;
					blck.add(rows);
					crow++;
				}
					madeRows++;
			}
			if(blck.get(0).isMoving()){
				create+=1*fact;
				
			}
		}
		else{
			blck.add(createRow());
			crow++;
			create+=1*fact;
			key=false;
		}
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				// Save the initial touch point
				if(event.getX()<pausebtn.getBound().right&&event.getX()>pausebtn.getBound().left){
					if(event.getY()<pausebtn.getBound().bottom&&event.getY()>pausebtn.getBound().top){
						if(pause){
							
							
						}
						else{
							pause=true;
						}
					}
				}
				if(pause){
					//restart button click
					if(event.getX()>4&&event.getX()<4){
						if(event.getY()>5&&event.getY()<5){

						}
					}
					//play button click
					if(event.getX()>playbtn.getBound().left&&event.getX()<playbtn.getBound().right){
						if(event.getY()>playbtn.getBound().top&&event.getY()<playbtn.getBound().bottom){
							pause=false;
						}
					}
					//home button click
					if(event.getX()>4&&event.getX()<4){
						if(event.getY()>5&&event.getY()<5){

						}
					}
				}
				if(isOver){
					if(event.getX()>getWidth()/5&&event.getX()<getWidth()-getWidth()/5){
						if(event.getY()>getHeight()/2+getHeight()/5&&event.getY()<getHeight()-getHeight()/10){
							endGame();
						}
					}
					if(event.getX()>4&&event.getX()<4){
						if(event.getY()>5&&event.getY()<5){

						}
					}
				}
				initialTouchX = event.getX();
				break;
			case MotionEvent.ACTION_MOVE:
				// Calculate the movement along the x-axis
				if(pause){
					
				}
				
				else{
				
				float deltaX = event.getX() - initialTouchX;

				// Update the snake's position
				float newX = snake.getCenterX() + deltaX;
				
				if(newX<snake.getCenterX()){
					dir=+1;
				}
				else{
					dir=-1;
				}
				if(newX>0+snake.getWidth()/2&&newX<getWidth()-snake.getWidth()/2){
					snake.setCenterX(newX);
					datas.add(snake.getCenterX());
				}
				 // Set the fixed y-coordinate here
					

				// Update the initial touch point for the next move event
				initialTouchX = event.getX();
				}
				break;
			case MotionEvent.ACTION_UP:
				// Reset thete initial touch point
				initialTouchX = 0;
				break;
		}
		return true;
	}
	
	private void keepMoving(){
		for(Row roes:blck){
			isMoving=true;
			roes.setMoving(true);
			fact=1;
		}
	}
	public void stopMoving(){
		for(Row rows:blck){
			isMoving=false;
			rows.setMoving(false);
			fact=0;
		}
	}
}

