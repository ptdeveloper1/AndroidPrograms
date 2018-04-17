package com.gini.coign;

import java.util.ArrayList;
import java.util.List;






import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	
		List<String> Wlist;
		TextView tv;
		ArrayAdapter<String> Awordlistarray;
		ListView ls;
		String items[];
		ArrayList <String> l1=new ArrayList<String>();
		
		View row;
	

		TextView coigninfo;
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);

	        setContentView(R.layout.main);
	       
	         System.out.println("haii");
	        
	      //   Bitmap bm = ShrinkBitmap(100, 100);
	         
	        // iv.setImageBitmap(bm);
		    ls=(ListView)findViewById(R.id.listWords);

	    System.out.println("haii");
	
		items=getResources().getStringArray(R.array.wordss);
		
		
		for(int h=0;h<items.length;h++)
		{
			l1.add(items[h]);
			System.out.println("values"+l1);
		}
		//Awordlistarray=new ArrayAdapter<String>(this,R.layout.listitems,items);
		
	//	ls.setAdapter(Awordlistarray);
		
		ls.setAdapter(new MyCustomAdapter());
		
		ls.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {
				int s=Integer.parseInt(arg0.getItemAtPosition(arg2).toString());
				String s1=l1.get(s);
				Toast.makeText(getBaseContext(), s1, Toast.LENGTH_LONG).show();
				processPage(s1);
				
				
			}
		});
		
		
		}

	 
		private void processPage(String val)
		{
			System.out.println("In Intent");
			Intent it1=new Intent(this,MainExpand.class);
			it1.putExtra("words", val);
			startActivityForResult(it1, 0);
			
		}
	/*	Bitmap ShrinkBitmap(int width, int height){//this is method in out side of activity
		  	   
	        BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
	           bmpFactoryOptions.inJustDecodeBounds = true;
	           Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logcom, bmpFactoryOptions);
	            
	           int heightRatio = (int)Math.ceil(bmpFactoryOptions.outHeight/(float)height);
	           int widthRatio = (int)Math.ceil(bmpFactoryOptions.outWidth/(float)width);
	            
	           if (heightRatio > 1 || widthRatio > 1)
	           {
	            if (heightRatio > widthRatio)  
	            {
	             bmpFactoryOptions.inSampleSize = heightRatio;
	            } else {
	             bmpFactoryOptions.inSampleSize = widthRatio; 
	            }
	           }
	            
	           bmpFactoryOptions.inJustDecodeBounds = false;
	           bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logcom, bmpFactoryOptions);
	        return bitmap;
	       }
		*/
		class MyCustomAdapter extends BaseAdapter {
			LayoutInflater inflater = getLayoutInflater();
			
			TextView textLabe2;
			TextView textLabel;
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return l1.size();
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return position;
			}
			

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				//LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT);
				row = inflater.inflate(R.layout.listitems, parent, false);
				textLabel = (TextView) row.findViewById(R.id.lttext);
				textLabe2 = (TextView) row.findViewById(R.id.lttext2);
				String dd[]=l1.get(position).split("-");
				//textLabe2.setLayoutParams(lp);
				//textLabel.setLayoutParams(lp);
				textLabe2.setGravity(Gravity.LEFT);
				textLabel.setGravity(Gravity.LEFT);
				textLabel.setText(dd[0]);
				textLabe2.setText(dd[1]);
				return (row);
			}
			
		
		}
		
	}

