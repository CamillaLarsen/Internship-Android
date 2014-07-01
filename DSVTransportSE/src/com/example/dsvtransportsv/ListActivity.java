package com.example.dsvtransportsv;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.example.dsvtransportsv.data.ListAdapter;
import com.example.dsvtransportsv.model.ListItems;

/**
 * 
 * @author Camilla
 * 
 *         This is the activity for the startup list. In the class, I got the
 *         list view and one buttons, which the user can interact with.
 */

public class ListActivity extends Activity {
	ListView list;
	Button exit;

	static List<ListItems> items = new ArrayList<ListItems>();
	public static int deletePos;
	static ListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		list = (ListView) findViewById(R.id.list);
		exit = (Button) findViewById(R.id.btnExit);

		/**
		 * Registration numbers
		 */
		final String[] title = new String[] { "XMT 123", "KLE 456", "CKL 789",
				"MRP 012", "DSV 345" };

		/**
		 * Name of the truck drivers
		 */
		final String[] subtitle = new String[] { "Peter Lund", "Hans Larsson",
				"Erik Petersson", "Bjørn Lundal", "Lars Svensson" };

		/**
		 * Adds the array values into a ArrayList called items.
		 */
		for (int i = 0; i < title.length; i++) {
			ListItems s = new ListItems(title[i], subtitle[i]);
			items.add(s);

		}
		/**
		 * Sets the adapter.
		 */
		adapter = new ListAdapter(this, android.R.layout.simple_list_item_2,
				items);
		list.setAdapter(adapter);

		/**
		 * onItemClick makes the user able to interact with the items in the
		 * list view. In this case, when the user clicks on a item, it opens a
		 * new window with the information, that was shown in the list view.
		 */
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				Intent intent = new Intent(ListActivity.this,
						InformationActivity.class);
				/**
				 * Takes the values from the list row and pass it to textviews
				 * in the next window.
				 */
				intent.putExtra("updateReg", title[position].toString());
				intent.putExtra("updateName", subtitle[position].toString());

				deletePos = position;
				adapter.notifyDataSetChanged();

				startActivity(intent);

			}

		});

		/**
		 * This button, closes the application. The user ends up at the devices'
		 * start window.
		 */
		exit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
				startActivity(intent);

			}
		});

	}

}
