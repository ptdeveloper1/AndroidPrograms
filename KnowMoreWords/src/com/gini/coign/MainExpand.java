package com.gini.coign;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;





import android.app.Activity;
import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
     

public class MainExpand extends Activity  {
	
	private static final String Log_TAG="WordBox";
	MyExpandableListAdapter elasricadapter;
	String WordInfo=""; 
	ExpandableListView elv;
	String WordfromIntent;
	String temp[];
	String t1[];
	int sval;
	String s,firststringvalues,secondstringvalues;
	int i,p=0;
	String delimeter="-";
	ArrayList<String> line;
	String s11;
	String pname,cname;
	
	String wordinfo[][];
	
	ArrayList<String> itemslist=new ArrayList<String>();
	ArrayList<String> itemslist1=new ArrayList<String>();
	
	   
	String items[]=new String[itemslist.size()];
	String items1[]=new String[itemslist1.size()];;
	@Override
    public void onCreate(Bundle savedInstanceState) {
	
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		
		
        super.onCreate(savedInstanceState);
      
        setContentView(R.layout.expand);
        elv=(ExpandableListView)findViewById(R.id.expandableListView1);
        
        Bundle bnn=getIntent().getExtras();
        if(bnn!=null)
        {
        	s11=bnn.getString("words");  
        }
        
        	
           	temp=s11.split(delimeter);  
        	for(i =0; i < temp.length ; i++)
        	{   System.out.println(temp[i]);
        	}
      
        
	    		DataGetting();
        	DataGetting1();
        	//childAdding();
        	
    
        	elasricadapter=new MyExpandableListAdapter(this);
         
        	elasricadapter.AddGroup(temp[0], itemslist);
        	elasricadapter.AddGroup(temp[1], itemslist1);
        	
        	elv.setAdapter(elasricadapter);
        

        	elv.setOnChildClickListener(new OnChildClickListener() {
				
				public boolean onChildClick(ExpandableListView parent, View v,
						int groupPosition, int childPosition, long id) {
					// TODO Auto-generated method stub
					cname=elasricadapter.getChild(groupPosition, childPosition).toString();
					pname=elasricadapter.getGroup(groupPosition).toString();
					
				ProcesssData(cname,pname);
					return false;
				}
			});
        	
      getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.window_title);
      setTheme(R.style.CustomWindowTitleBackground1);
      
      ImageView next = (ImageView) findViewById(R.id.Button02);
      next.setOnClickListener(new View.OnClickListener() {
          public void onClick(View view) {
              Intent intent = new Intent();
              setResult(RESULT_OK, intent);
              finish();
          }

      });
      TextView tag=(TextView)findViewById(R.id.headertag);
	    tag.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
             setResult(RESULT_OK, intent);
             finish();
			}
		});
      
    }
	  
	    
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.gc();
	
	}
	

	/*
	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		
		cname=elasricadapter.getChild(groupPosition, childPosition).toString();
		pname=elasricadapter.getGroup(groupPosition).toString();
		
	ProcesssData(cname,pname);
		return false;  
	}*/
	public void ProcesssData(String cn,String pn)
	{
		Intent i=new Intent(this,Meaning.class);
		i.putExtra("pn", pname);
		i.putExtra("cn", cname);
		startActivityForResult(i, 0);
		
	}

/*	public void childAdding()
	{
		System.out.println("length iss :"+items.length);
		System.out.println("length iss :"+items1.length);
		
		if(items.length<=items1.length)
		{
			p=items1.length;
			
			wordinfo=new String[2][items1.length];
		}else
		{
			p=items1.length;
			
			wordinfo=new String[2][items.length];
		}
		
		for(int k=0;k<2;k++)
		{
			
			
				switch(k)
				{
				case 0:
					for(int j=k;j<2;j++)
					{
						for(int m=0;m<items.length;m++){
							wordinfo[j][m]=items[m]; 
							
						}
						
					
					}
					break;
				case 1:
					for(int o=k;o<2;o++)
					{
						for(int n=0;n<items1.length;n++){
							wordinfo[o][n]=items1[n]; 
						}
					
					break;
					}
				}
		}
		System.out.println("ending with ");
		}
		
		
	 		*/
	
	
	public void DataGetting()
	{
		
		try {
			InputStream is =getResources().openRawResource(R.raw.cwords);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String s;
			String ss[];
	        int linecount=0;
	        String line;
	        int i2=0;
	        while ((s=br.readLine())!=null)
	     				  {
	     				  linecount++;
	     				  int indexfound=s.indexOf(temp[0]);
	     				  if(indexfound>-1)
	     							  {
	     					  ss=s.split("~");
	     					  for(int g=1;g<ss.length;g++)
	     					  {
	     						  
	     					
	     						 
	     						 itemslist.add(ss[g]);
	     					  }
	     					
	     							  }
	     				  }
	        for(int h=0;h<itemslist.size();h++)
			{
			
			
				
			
			items=itemslist.toArray(new String[itemslist.size()]);
			
					
			} 
	        

		
		}
		catch (Exception e) {
	
			e.printStackTrace();
		}
		
		
		
	      
	   
	        	
	}
	public void DataGetting1()
	{
		
		System.out.println("entered in to data grid1");
		

		try {
			InputStream is =getResources().openRawResource(R.raw.cwords);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String s;
			String ss[];
	        int linecount=0;
	        String line;
	        int i2=0;
	        while ((s=br.readLine())!=null)
	     				  {
	     				  linecount++;
	     				  int indexfound=s.indexOf(temp[1]);
	     				  if(indexfound>-1)
							  {
					  ss=s.split("~");
					  for(int g=1;g<ss.length;g++)
					  {
						  
						
						  itemslist1.add(ss[g]);
					  }
					
							  }
	     				  }
	        for(int h=0;h<itemslist1.size();h++)
			{
			
				items1=itemslist1.toArray(new String[itemslist1.size()]);
				
				
			}
	        

		
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
	        	  
	}
	
	
   
	public class  MyExpandableListAdapter extends BaseExpandableListAdapter
    {
		private final Context context;
    	//private String[] groupwords={temp[0],temp[1]};
		private final HashMap<String, ArrayList<String>> myData = new HashMap<String, ArrayList<String>>();
		private final HashMap<Integer, String> lookUp = new HashMap<Integer, String>();
    	
		
		MyExpandableListAdapter(final Context con)
    	{
    		context = con;
    	}
    	
		public boolean AddGroup(final String groupName, final ArrayList<String> list)
    	{
    	final ArrayList<String> prev = myData.put(groupName, list);

    	if (prev != null)
    	return false;

    	lookUp.put(myData.size() - 1, groupName);

    	notifyDataSetChanged();
    	return true;
    	}

  	 
    	
    	public Object getChild(int groupPos, int childPos){
			// TODO Auto-generated method stub
    		if (lookUp.containsKey(groupPos))
    		{
    		final String str = lookUp.get(groupPos);
    		final ArrayList<String> data = myData.get(str);

    		return data.get(childPos);
    		}

    		return null;
    		}

		public long getChildId(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return childPosition;
		}

		@Override
		public View getChildView(int groupPos, int childPos, boolean isLastChild,
		View convertView, ViewGroup parent)
		{
		//LinearLayout linear = new LinearLayout(context);

			  AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
	                    ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT);

		TextView text = new TextView(context);
		text.setGravity(Gravity.LEFT);
		
		// Indent
		final String str = (String)getChild(groupPos, childPos);

		//linear = new LinearLayout(context);
		///linear.setOrientation(LinearLayout.VERTICAL);

		text.setLayoutParams(lp);
		text.setText(str);
		text.setPadding(36, 0, 0, 15);
		////linear.addView(text);

		return text;
		}
		

		@Override
		public int getChildrenCount(int groupPos)
		{
		if (lookUp.containsKey(groupPos))
		return myData.get(lookUp.get(groupPos)).size();

		return 0;
		}

		@Override
		public Object getGroup(int groupPos)
		{
		if (lookUp.containsKey(groupPos))
		return myData.get(lookUp.get(groupPos));

		return null;
		}

		@Override
		public int getGroupCount()
		{
		return myData.size();
		}

		@Override
		public long getGroupId(int groupPos)
		{
		return groupPos;
		}

		@Override
		public View getGroupView(int groupPos, boolean isExpanded, View convertView, ViewGroup parent)
		{
		//LinearLayout linear = new LinearLayout(context);
		//	linear.setPadding(30, 30, 0, 15);
			  AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
	                    ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT);
			
		TextView text = new TextView(context);
		text.setGravity(Gravity.LEFT);
		// Push the group name slightly to the right for drop down icon
		final String str = lookUp.get(groupPos);

		//linear = new LinearLayout(context);
		//linear.setOrientation(LinearLayout.VERTICAL);

		text.setLayoutParams(lp);
		text.setText(str);
		text.setTextSize(18.0f);
		text.setPadding(50,20, 0, 15);
		//linear.addView(text);

		return text;
		}

		

      /*  public TextView getGenericView() {
            // Layout parameters for the ExpandableListView
            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT);

            TextView textView = new TextView(MainExpand.this);
            textView.setLayoutParams(lp);
            // Center the text vertically
            textView.setGravity( Gravity.LEFT);
            // Set the text starting position
            textView.setPadding(36, 0, 0, 15);
            return textView;
        }
        public TextView getGenericView1() {
            // Layout parameters for the ExpandableListView
            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT, 64);

            TextView textView = new TextView(MainExpand.this);
            textView.setLayoutParams(lp);
            // Center the text vertically
            textView.setGravity( Gravity.LEFT);
            // Set the text starting position
            textView.setPadding(36, 0, 0, 0);
            return textView;
        }
*/


        @Override
        public boolean hasStableIds()
        {
        return false;
        }

        @Override
        public boolean isChildSelectable(int groupPos, int childPos)
        {
        return true;
        }
    } 
}