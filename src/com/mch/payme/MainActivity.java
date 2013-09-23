package com.mch.payme;

import io.triangle.reader.PaymentCard;
import io.triangle.reader.ReaderActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ReaderActivity {
	
	 LinearLayout root;
	 TextView tv;
	 PaymentCard card;
	 String d = "YqbKek+J1CavqyE5cyzXpqHJKZm1/k32qyt0gS5FaobJzBM+IoqsuIPqIrtNf690doukK8XX1yKfdi+tYFGf35cbKSaZx1yRir1gRZbzpPTYtA4OL41FlIIQMDwkKCOzoD+GTvMONVX/XhGaz0cCPlHm9rqe4uAHBAPIStJCydrvNaaXfZG9ZHj9Zoi577c8EB5JXAYH6n9Pz3URwOaml3sikhuM03kQsAt28r0LWh29L0QW4cegoefJpA94UqVTnmTrh9O5Fkik4qpgS+7J0UU5Q6toR1YkG6rmnJ6yhYB7dNQD7LK8sWglt6CaOhP0YsF3AGCd4pl3P0dSSt4RkQ==";
	 String modulus = "3X+p2VcQtHb+3fPHc6h+9MKYsBCyATW+tJEPXNMBEXa15/7lkqPOvPkjcmw/I2EmvS3U7pu96iXttkSrKeM3lr700ejIuDwRXABFoMyJhP2D1hEESQkNX3sC59ciHFPF53zsDSroBC0xAD7N0IhpOME0y3Pl15gIzk4TuKGQvUnHZ5hstOEWLWwWFvy8slZd4FpI5jBatMLMLlyq7fDhKnGm4x7h94ZfS9RSSHqVdp5sGpfVIjRASgJcAvuWU8utY5hfcpYpCZrJrQloO/ya1PRD4fzPwml70bYRc+uMq5AUTUU1RnIaf3JjodpbAiZyE+BiwGeuEeeDEFTuqH+S5w==";
	 
	 RSA rsa; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.root = (LinearLayout)this.findViewById(R.id.main_LinearLayout_root);
		this.tv = (TextView) this.findViewById(R.id.textView);
	}
	

	 /**
     * This method is called after the card has been processed via NFC.
     * @param cardInformation will contain information obtained from the NFC card. If there was a failure processing
     *                        the card, this object will be null.
     */

	@Override
	protected void onPostNFCExecute(PaymentCard cardInformation) {
		
		if (cardInformation == null)
        {
            // The card could not be scanned properly. Inform the user as such.
            Toast.makeText(this, "Scanning failed, please try again.", Toast.LENGTH_SHORT).show();
        }
        else
        {
        	card = cardInformation;
        	tv.setText("Welcome to Pyro! Press Next to enter the amount!");
        	 Toast.makeText(this, "Scanning success", Toast.LENGTH_SHORT).show();
             

             
        	 Log.i("Pyro: cardinformation YES", cardInformation.getEncryptedAccountNumber().toString());
        	 
        	 // Card was successfuly read, create a new card view and add it to the layout so that the user can see the
            // card information
            int index = this.root.getChildCount();
            this.root.addView(
                    new CardViewClass(this.root, cardInformation, this, index),
                    index,
                    new android.view.ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        
        
        }

		
	}
	

	@Override
	protected void onPreNFCExecute() {
		// TODO Auto-generated method stub
		
	}
	
	 /**
     * Allows the user to recommend this application to a friend.
     */
    public void add_amount(final View view)
    {

    if (card == null)
    	{
    		Toast.makeText(this, "Please scan a valid card", Toast.LENGTH_SHORT).show();
    	   
    	}
    	else
    	{
    		Intent addAmountIntent = new Intent(this, AddAmountClass.class);

  	        addAmountIntent.setType("text/plain");

//  	        addAmountIntent.putExtra(Intent.EXTRA_SUBJECT, "App that lets you scan your credit card");
  	      addAmountIntent.putExtra("KeyToAccessData", card.toString());
  	        startActivity(addAmountIntent);
    	}
   
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
