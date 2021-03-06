package com.example.thoithanh.shoppingappp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		SharedPreferences settings = getSharedPreferences("UserInfo", 0);
		if(settings.getString("UserID", "").equals("")){
			setContentView(R.layout.activity_main);
			final EditText etUserID = (EditText) findViewById(R.id.etFirstName);
			final EditText etPassword = (EditText) findViewById(R.id.etPassword);

			final ImageButton bLogin = (ImageButton) findViewById(R.id.bLogin);
			final ImageButton bCreateNewID = (ImageButton) findViewById(R.id.bCreateNewId);
			final ImageButton bForgotPassword = (ImageButton) findViewById(R.id.bForgotPassword);

			bCreateNewID.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
					MainActivity.this.startActivity(registerIntent);
				}
			});

			bLogin.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

					final String userId = etUserID.getText().toString();
					final String password = etPassword.getText().toString();

					JSONObject jsonObject = new JSONObject();
					try {
						jsonObject.put("username", userId);
						jsonObject.put("password", password);
					} catch (JSONException e) {
						e.printStackTrace();
					}


					Response.Listener<JSONObject> responeListener = new Response.Listener<JSONObject>() {
						@Override
						public void onResponse(JSONObject response) {
							//String name = jsonRespond.getString("userID");
							try {
								int userId = response.getJSONObject("data").getInt("id");
								SharedPreferences settings = getSharedPreferences("UserInfo", 0);
								SharedPreferences.Editor editor = settings.edit();
								editor.putString("UserID",userId+"");
								editor.apply();
								gotoHomePage(userId);

							} catch (JSONException e) {
								e.printStackTrace();
							}
						}
					};

					LoginRequest loginRequest = null;
					try {
						loginRequest = new LoginRequest(jsonObject, responeListener);
					} catch (JSONException e) {
						e.printStackTrace();
					}
					RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
					queue.add(loginRequest);

				}
			});
		} else {
			gotoHomePage(Integer.parseInt(settings.getString("UserID", "")));
		}
    }

    void gotoHomePage(int userId){
		Intent intent = new Intent(MainActivity.this, DidLoginActivity.class);
		intent.putExtra("userId", userId);
		MainActivity.this.startActivity(intent);
		MainActivity.this.finish();
	}
}
