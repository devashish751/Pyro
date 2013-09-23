package com.mch.payme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddAmountClass extends Activity {
	
//	 PaymentCard card;
//	String

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_amount);
		
//		Log.i("Pyro: cardinformation YES", card.toString());
	}
	
	public void enter_cvv(final View view)
    {
		EditText amount = (EditText) findViewById(R.id.amountText);

		if ( amount.getText().toString().trim().equalsIgnoreCase(""))
		{
			Toast.makeText(this, "Please enter an amount", Toast.LENGTH_SHORT).show();
    	}
		else
		{
	        Intent enterCVVIntent = new Intent(this, EnterCVVClass.class);

	        enterCVVIntent.setType("text/plain");
	        startActivity(enterCVVIntent);
		}

    }

}
