package com.mig.deeplink.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class ItemListPreference {

	private static ItemListPreference preference;
	private static SharedPreferences sharedPreferences;

	private static final String ITEMLIST_PEFERENCE = "itemlist_perference";
	private static final String PURCHASE_STATUS = "purchase_status";

	public static ItemListPreference getInstance(Context context) {
		if (preference == null) {
			preference = new ItemListPreference();
			sharedPreferences = context.getSharedPreferences(
					ITEMLIST_PEFERENCE, Context.MODE_PRIVATE);
		}
		return preference;
	}

	private Editor getEditor() {
		return sharedPreferences.edit();
	}

	public void savePurchaseStatus(String item, int purchaseStatus) {
		Editor editor = getEditor();
		editor.putInt(PURCHASE_STATUS + item, purchaseStatus);
		editor.commit();
	}

	public int getPurchaseStatus(String item) {
		int purchaseStatus = sharedPreferences.getInt(PURCHASE_STATUS + item, 0);
		return purchaseStatus;
	}

}
