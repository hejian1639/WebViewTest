package com.example.webviewtest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListView listView = (ListView)findViewById(R.id.listView);
		listView.setAdapter(new SimpleAdapter(this, getData(),
                android.R.layout.simple_list_item_1, new String[] { "title" },
                new int[] { android.R.id.text1 }));
		listView.setOnItemClickListener(new ListView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		        Map<String, Class> map = (Map<String, Class>)parent.getItemAtPosition(position);
				
				        Intent intent = new Intent(getBaseContext(), map.get("intent"));
				        startActivity(intent.putExtra("url", "http://browserbench.org/JetStream/"));
				
			}
			
		});
		
    	final SwipeRefreshLayout		swipeRefreshLayout;
		//findview
		swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
		//设置卷内的颜色
		swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
				android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
		//设置下拉刷新监听
		swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {
				new Handler().postDelayed(new Runnable() {
					public void run() {
						//停止刷新动画
						swipeRefreshLayout.setRefreshing(false);
					}
				}, 3000);
			}
		});
        
	}

    protected List<Map<String, Object>> getData() {
        List<Map<String, Object>> myData = new ArrayList<Map<String, Object>>();
        myData.add(new HashMap<String, Object>(){{put("title", "Native WebView"); put("intent", NativeWebView.class);}});
        myData.add(new HashMap<String, Object>(){{put("title", "X Walk"); put("intent", XWalkWebView.class);}});
        
        return myData;
    }
	
//    @Override
//    protected void onListItemClick(ListView l, View v, int position, long id) {
//        Map<String, Object> map = (Map<String, Object>)l.getItemAtPosition(position);
//
////        Intent intent = new Intent((Intent) map.get("intent"));
//////        intent.addCategory(Intent.CATEGORY_SAMPLE_CODE);
////        startActivity(intent);
//    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
