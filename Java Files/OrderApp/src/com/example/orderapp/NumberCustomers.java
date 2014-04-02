package com.example.orderapp;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

public class NumberCustomers extends Activity {
    static int selectedCust = 0;
	public static Customer CurrentCust;  
	Button b;
      ScrollView scrollview;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        scrollview = new ScrollView(this);
        LinearLayout linearlayout = new LinearLayout(this);
        linearlayout.setOrientation(LinearLayout.VERTICAL);
        scrollview.addView(linearlayout);
       
        for(int i = 0; i<WelcomePage.getNumCust();i++)
        {
            LinearLayout linear1 = new LinearLayout(this);
            linear1.setOrientation(LinearLayout.HORIZONTAL);
            linearlayout.addView(linear1);
            b = new Button(this);
            b.setText("Order for guest "+(i+1));
            b.setId(i);
            b.setTextSize(10);
            b.setPadding(8, 3, 8, 3);
            b.setTypeface(Typeface.SERIF,Typeface.BOLD_ITALIC);
            b.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
           
            linear1.addView(b);
           
       

            b.setOnClickListener(new View.OnClickListener() {
                       
                        @Override
                        public void onClick(View v) {
                              // TODO Auto-generated method stub
                              //Toast.makeText(getApplicationContext(), "Button Clicked.."+ (v.getId()+1), Toast.LENGTH_SHORT).show();
                              selectedCust = (v.getId()+1);
                              CurrentCust = WelcomePage.CustArray[selectedCust];
                              Transistion(v);

                        }
                  });
        }
        this.setContentView(scrollview);
    }
    public void Transistion(View view) {
        Intent intent = new Intent(this, ViewMenu.class);
        startActivity(intent);
    }
    
	public static int getSelCust(){
	return selectedCust;
	}
}
