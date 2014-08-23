package com.group1.example2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.group1.homework.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ListView mListView;
	private SQLiteDatabase mDataBase;
	private DatabaseHelper databasehelp;
	private Cursor mCursor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		databasehelp = new DatabaseHelper(this, "mydata.db3");
		mDataBase = databasehelp.getWritableDatabase();
		
		mListView = (ListView) this.findViewById(R.id.mylist);
		mCursor = databasehelp.getAll(mDataBase);
		addListViewItem();
		
		mListViewListener();
	}
	public void addListViewItem() {
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
				this,
				R.layout.list_item,
				mCursor,
				new String[] {"name","sex","number","sign"},
				new int[] {R.id.name, R.id.sex, R.id.number, R.id.signature},
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		mListView.setAdapter(adapter);
	}
	private void mListViewListener() {
		mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				new AlertDialog.Builder(MainActivity.this).setTitle("确定要删除 吗")
				.setPositiveButton("确认", new OnClickListener(){
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						if (databasehelp.getAll(mDataBase).moveToFirst()){
							mDataBase.execSQL("delete from studentInfo", null);;
							mCursor.requery();
						}
					}
				})
				.setNegativeButton("取消", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						
					}
				}).show();
				return false;
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {		
		menu.add(0, 1, 1, "添加");
		menu.add(0, 2, 2, "取消");
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		if(item.getItemId() == 1){
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, InputActivity.class);
			startActivity(intent);
			finish();
		}
		if(item.getItemId() == 2){
			Toast.makeText(this, "取消选项", Toast.LENGTH_SHORT).show();
		}
		return super.onOptionsItemSelected(item);
	}
}
