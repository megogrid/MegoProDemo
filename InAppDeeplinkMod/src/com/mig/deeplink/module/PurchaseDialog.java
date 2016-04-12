package com.mig.deeplink.module;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class PurchaseDialog extends Dialog implements
		android.view.View.OnClickListener {

	private Context context;
	 SetOnDialogListner dialogListner;
	TextView itemPurchase;
	Button confirm, cancel;
	int position;
	private String name;

	public PurchaseDialog(Context context, String name, SetOnDialogListner listner,int position) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.dialogListner = listner;
		this.position=position;
		this.name = name;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.purchase_dialog);
		confirm = (Button) findViewById(R.id.confirm);
		cancel = (Button) findViewById(R.id.cancel);
		itemPurchase = (TextView) findViewById(R.id.itemPurchase);
		itemPurchase.setText(this.name);
		confirm.setOnClickListener(this);
		cancel.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == cancel) {
			dialogListner.cancel(position);
		}
		if (v == confirm) {
			dialogListner.confirm(position);
		}

	}

	public interface SetOnDialogListner {
		public void confirm(int pos);

		public void cancel(int pos);
	}

}
