package com.example.dsvtransportsv.data;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.dsvtransportsv.R;
import com.example.dsvtransportsv.model.Materials;

/**
 * 
 * @author Camilla
 * 
 *         This it the adapter for the dialog. The adapter inflates my custom
 *         list view and holds filterable, which is my search function.
 * 
 * 
 */

@SuppressLint("DefaultLocale")
public class MaterialAdapter extends BaseAdapter implements Filterable {
	LayoutInflater inflater;
	Context context;
	List<Materials> searchItemList = null;
	ArrayList<Materials> materialList = new ArrayList<Materials>();

	public MaterialAdapter(Context context, List<Materials> searchItemList) {
		this.context = context;
		inflater = LayoutInflater.from(context);
		this.searchItemList = searchItemList;
		this.materialList = new ArrayList<Materials>();
		this.materialList.addAll(searchItemList);

	}

	@Override
	public int getCount() {
		return searchItemList.size();
	}

	@Override
	public Object getItem(int position) {
		return searchItemList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * Set the view of the custom list view.
	 * 
	 */
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder;
		if (convertView == null) {

			convertView = inflater.inflate(R.layout.d, null);
			viewHolder = new ViewHolder();
			viewHolder.tvMaterialName = (TextView) convertView
					.findViewById(R.id.tvMaterialName);
			viewHolder.tvMaterialNo = (TextView) convertView
					.findViewById(R.id.tvMaterialNo);

			convertView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) convertView.getTag();

		}

		viewHolder.tvMaterialName.setText((searchItemList.get(position))
				.getMaterialName());
		viewHolder.tvMaterialNo.setText((searchItemList.get(position))
				.getMaterialNo());

		return convertView;

	}

	/**
	 * 
	 * @author Camilla
	 * 
	 *         Class viewHolder, which holds the textviews in the custom list
	 *         view.
	 * 
	 */
	public class ViewHolder {
		TextView tvMaterialName;
		TextView tvMaterialNo;

	}

	/**
	 * The filter method. This is the search function in the custom dialog.
	 * 
	 */
	@SuppressLint("DefaultLocale")
	@Override
	public Filter getFilter() {
		Filter filter = new Filter() {

			@SuppressWarnings("unchecked")
			@Override
			protected void publishResults(CharSequence arg0,
					FilterResults results) {
				searchItemList = (List<Materials>) results.values;
				
				/**
				 * Displays the search result.
				 */
				if (results.count > 0) {
					notifyDataSetChanged();
				} else {
					notifyDataSetInvalidated();
				}

			}

			@Override
			protected FilterResults performFiltering(CharSequence arg0) {
				FilterResults filterResults = new FilterResults();
				ArrayList<Materials> searchList = new ArrayList<Materials>();
				
				/**
				 * Gets the valTues from the list and puts them into a new list, called searchList, 
				 * which is sent to displayed.
				 * 
				 */

				arg0 = arg0.toString().toLowerCase();
				for (int i = 0; i < searchItemList.size(); i++) {
					Materials m = searchItemList.get(i);
					if (m.toString().toLowerCase().startsWith(arg0.toString())) {
						searchList.add(m);
					}
				}

				filterResults.count = searchList.size();
				filterResults.values = searchList;

				Log.e("VALUES", filterResults.values.toString());

				return filterResults;
			}

		};
		return filter;
	}

}
