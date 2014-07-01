package com.example.dsvtransportsv.data;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dsvtransportsv.R;
import com.example.dsvtransportsv.model.ListItems;
/**
 * 
 * @author Camilla
 *
 * This is the list adapter, for the startup list.
 * This just inflates my custom list view and sets the values
 * of the truckdrivers and the registationnumber.
 */

public class ListAdapter extends ArrayAdapter<ListItems> {
	LayoutInflater inflater;
	Context context;
	List<ListItems> itemList;

	public ListAdapter(Context context, int resourceId, List<ListItems> itemList) {
		super(context, android.R.layout.simple_list_item_2, itemList);
		this.context = context;

	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		ListItems item = getItem(position);

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.list_item, null);
			viewHolder = new ViewHolder();
			viewHolder.tvRegNo = (TextView) convertView
					.findViewById(R.id.tvRegNo);
			viewHolder.tvName = (TextView) convertView
					.findViewById(R.id.tvName);

			convertView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) convertView.getTag();

		}
		viewHolder.tvRegNo.setText(item.getRegistationNo());
		viewHolder.tvName.setText(item.getTruckdriverName());

		return convertView;
	}

	private class ViewHolder {
		TextView tvRegNo;
		TextView tvName;

	}

}
