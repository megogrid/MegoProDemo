package com.mig.deeplink.module;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class ItemListActivity extends Activity {
	private ItemListAdapter adapter;
	private ArrayList<String> itemList;
	private ListView mainItemList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.itemlist);
		mainItemList = (ListView) findViewById(R.id.mainItemList);
		itemList = new ArrayList<String>();
		addInitialData();
		addDataToList();
		adapter = new ItemListAdapter(ItemListActivity.this, itemList);
		mainItemList.setAdapter(adapter);
		System.out.println("<<<<isPurchased Glucometer "
				+ isPurchased("Glucometer"));
		System.out.println("<<<<isPurchased Medicine Tracker"
				+ isPurchased("Medicine Tracker"));
		System.out.println("<<<<isPurchased Doctor and Appointments "
				+ isPurchased("Doctor and Appointments"));
		System.out.println("<<<<Food and Exercise "
				+ isPurchased("Food and Exercise"));

	}

	public int isPurchased(String name) {
		int purchaseStatus = ItemListPreference.getInstance(this)
				.getPurchaseStatus(name);
		return purchaseStatus;
	}

	public void addDataToList() {
		ArrayList<String> addItemList = new ArrayList<String>();
		for (int i = 0; i < itemList.size(); i++) {
			if (isPurchased(itemList.get(i)) == 0) {

				addItemList.add(itemList.get(i));

			}

		}
		itemList = null;
		itemList = addItemList;
	}

	public void addInitialData() {

		itemList.add("Glucometer");
		itemList.add("Medicine Tracker");
		itemList.add("Doctor and Appointments");
		itemList.add("Food and Exercise");
	}

	

}
