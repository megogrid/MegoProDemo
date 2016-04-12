package com.mig.deeplink.module;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.mig.deeplink.module.PurchaseDialog.SetOnDialogListner;

public class ItemListAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<String> itemList;
	private SetOnDialogListner dialogListner;
	PurchaseDialog dialog;

	public ItemListAdapter(Context context, ArrayList<String> itemList) {
		this.context = context;
		this.itemList = itemList;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return itemList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		View view = convertView;
		if (view == null) {
			view = inflater.inflate(R.layout.itemlist_row, null);
			holder = new ViewHolder();
			holder.item = (TextView) view.findViewById(R.id.item);
			holder.buy = (Button) view.findViewById(R.id.buy);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		holder.item.setText(itemList.get(position));
		holder.buy.setTag(position);
		holder.buy.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Integer pos = (Integer) v.getTag();
				dialog = new PurchaseDialog(context, itemList.get(position), dialogListner, pos);
				dialog.show();
			}
		});

		dialogListner = new SetOnDialogListner() {

			@Override
			public void confirm(int pos) {
				// TODO Auto-generated method stub
				setPurchase(itemList.get(pos));
				System.out.println("<<<<isPurchased position" + pos);
				itemList.remove(pos);
				notifyDataSetChanged();

			}

			@Override
			public void cancel(int pos) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}

		};

		return view;
	}

	private class ViewHolder {
		TextView item;
		Button buy;

	}

	public void setPurchase(String name) {
		ItemListPreference.getInstance(context).savePurchaseStatus(name, 1);

		dialog.dismiss();

	}

}
