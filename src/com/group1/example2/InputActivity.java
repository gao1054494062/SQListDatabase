package com.group1.example2;

import java.io.Serializable;

import com.group1.homework.R;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputActivity extends Activity {

	private String strName;
	private String strSex;
	private String strNumber;
	private String strSign;
	private Button btnSave;
	private Button btnCancel;
	private EditText editName;
	private EditText editSex;
	private EditText editNumber;
	private EditText editSig;
	private SQLiteDatabase mDataBase;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.input_item);
		
		getInputValues();
		
		btnSave = (Button) findViewById(R.id.btnSave);
		btnSavelistener();

		btnCancel = (Button) findViewById(R.id.btnCancel);
		btnCancelListener();
	}

	private void getInputValues() {
		editName = (EditText) findViewById(R.id.input_name);
		editSex = (EditText) findViewById(R.id.input_sex);
		editNumber = (EditText) findViewById(R.id.input_number);
		editSig = (EditText) findViewById(R.id.input_signature);
		strName = editName.getText().toString();
		strSex = editSex.getText().toString();
		strNumber = editNumber.getText().toString();
		strSign = editSig.getText().toString();
	}

	private void btnSavelistener() {
		btnSave.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mDataBase = SQLiteDatabase.openOrCreateDatabase(
						InputActivity.this.getFilesDir().toString() + "mydata.db3", null);
				
				ContentValues insertlValues = new ContentValues();
				insertlValues.put("name", strName);
				insertlValues.put("sex", strSex);
				insertlValues.put("number", strNumber);
				insertlValues.put("sign", strSign);
				mDataBase.insert("studentInfo", null, insertlValues);
				
				Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_LONG).show();
				
				Intent intent = new Intent();
				intent.setClass(InputActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

	private void btnCancelListener() {
		btnCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(InputActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}