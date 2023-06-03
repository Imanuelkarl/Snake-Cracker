package com.realgames.wormattack;

import android.app.*;
import android.os.*;
import android.content.*;
import android.widget.*;
import android.view.*;
import android.view.View.*;

public class MainActivity extends Activity 
{
	Game wormgame;
	
	SharedPreferences sp;
	private LinearLayout linear1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		linear1=findViewById(R.id.linear1);
	
		sp=getSharedPreferences("sp", Activity.MODE_PRIVATE);
		wormgame=new Game(this);
		wormgame.saveTo(sp);
		wormgame.activity(this);
		wormgame.parentView(linear1);
        setContentView(R.layout.main);
		wormgame.makeView();
		
		
    }
}
