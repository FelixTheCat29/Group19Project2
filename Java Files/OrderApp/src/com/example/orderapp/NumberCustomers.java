package com.example.orderapp;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class NumberCustomers extends Activity {
    static int selectedCust = 0;
	public static Customer CurrentCust;  
	//Button b;
    RelativeLayout mainRelativeLayout;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
       
        // Creating a new RelativeLayout
        mainRelativeLayout = new RelativeLayout(this);
        
        // Defining the RelativeLayout layout parameters with Fill_Parent
        RelativeLayout.LayoutParams relativeLayoutParameters = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);
        
        
 
        /*setContentView(R.layout.activity_number_customers);
        relativelayout = (RelativeLayout) findViewById(R.id.RelativeLayout1);
        
        LinearLayout linearlayout = new LinearLayout(this);
        linearlayout.setOrientation(LinearLayout.VERTICAL);
        relativelayout.addView(linearlayout);*/
       
        for(int i = 0; i<WelcomePage.getNumCust();i++)
        {
        	
        	int colorArray[]={Color.RED,Color.BLUE,Color.GREEN,Color.MAGENTA};
            
        	// Creating a new Left Button with Margin
            Button buttonLeftWithMargin = new Button(this);
            buttonLeftWithMargin.setText("Order for guest "+(i+1));
            
            // Add a Layout to the Button with Margin
            AddButtonLayout(buttonLeftWithMargin, RelativeLayout.ALIGN_PARENT_LEFT, 300, 80+i*200, 0, 0);
     
            
            //add button to the view
            mainRelativeLayout.addView(buttonLeftWithMargin);
            
            
            //set an id to the view
            buttonLeftWithMargin.setId(i);
            
            //set color
            buttonLeftWithMargin.setTextColor(colorArray[i]);
            
        	/*LinearLayout linear1 = new LinearLayout(this);
            linear1.setOrientation(LinearLayout.HORIZONTAL);
            linearlayout.addView(linear1);
            b = new Button(this);
            b.setText("Order for guest "+(i+1));
            b.setId(i);
            b.setTextSize(10);
            b.setPadding(8, 3, 8, 3);
            b.setTypeface(Typeface.SERIF,Typeface.BOLD_ITALIC);
            b.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
           
            linear1.addView(b);*/
           
       

            buttonLeftWithMargin.setOnClickListener(new View.OnClickListener() {
                       
                        @Override
                        public void onClick(View v) {
                              // TODO Auto-generated method stub
                              //Toast.makeText(getApplicationContext(), "Button Clicked.."+ (v.getId()+1), Toast.LENGTH_SHORT).show();
                              selectedCust = (v.getId()+1);
                              CurrentCust = WelcomePage.CustArray[(selectedCust-1)];
                              Transistion(v);

                        }
                  });
        }
        
     // Setting the RelativeLayout as our content view
        setContentView(mainRelativeLayout, relativeLayoutParameters);
        
    }
    public void Transistion(View view) {
        Intent intent = new Intent(this, ViewMenu.class);
        startActivity(intent);
    }
    
	public static int getSelCust(){
	return selectedCust;
	}
	
	private void AddButtonLayout(Button button, int centerInParent, int marginLeft, int marginTop, int marginRight, int marginBottom) {
        // Defining the layout parameters of the Button
        RelativeLayout.LayoutParams buttonLayoutParameters = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        // Add Margin to the LayoutParameters
        buttonLayoutParameters.setMargins(marginLeft, marginTop, marginRight, marginBottom);

        // Add Rule to Layout
        buttonLayoutParameters.addRule(centerInParent);

        // Setting the parameters on the Button
        button.setLayoutParams(buttonLayoutParameters);     
    }
	
	private void AddButtonLayout(Button button, int centerInParent) {
        // Just call the other AddButtonLayout Method with Margin 0
        AddButtonLayout(button, centerInParent, 0 ,0 ,0 ,0);
    }
}
