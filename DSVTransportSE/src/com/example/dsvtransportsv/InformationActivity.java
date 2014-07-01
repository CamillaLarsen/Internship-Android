package com.example.dsvtransportsv;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dsvtransportsv.data.MaterialAdapter;
import com.example.dsvtransportsv.model.Materials;

/**
 * 
 * @author Camilla
 * 
 *         This is the information activity. The activity is for the window,
 *         which holds the information on the item from the startup activity.
 *         The activity holds two buttons.
 */
public class InformationActivity extends Activity {
	TextView name;
	TextView reg;
	TextView product;
	TextView productNo;
	ListView searchList;
	EditText search;

	Button accept;
	Button edit;
	Button cancel;
	Button ok;
	Dialog dialog;

	String resultProduct;
	String resultProductNo;
	String resultText;
	public static String result;
	int textLength = 0;

	List<Materials> materialList = new ArrayList<Materials>();
	MaterialAdapter adapter;

	/**
	 * Materials
	 */
	String[] material = { "Betong", "Grus", "Järn", "Metall", "Grus fin",
			"Grus grov", "Sten" };

	/**
	 * Material numbers
	 */
	public static String materialNo[] = { "123", "234", "345", "456", "567",
			"789", "012" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.information_activity);

		name = (TextView) findViewById(R.id.name);
		reg = (TextView) findViewById(R.id.reg);
		product = (TextView) findViewById(R.id.product);
		productNo = (TextView) findViewById(R.id.productNo);

		accept = (Button) findViewById(R.id.btnAccept);
		edit = (Button) findViewById(R.id.btnEdit);

		/**
		 * Bundle values. Values from the startup window, which is placed in the
		 * text view in this activity.
		 */
		Bundle extras = getIntent().getExtras();
		String selected_item = extras.getString("updateReg");
		reg = (TextView) findViewById(R.id.reg);
		reg.setText(selected_item);

		Bundle extras1 = getIntent().getExtras();
		String selected_item1 = extras1.getString("updateName");
		name = (TextView) findViewById(R.id.name);
		name.setText(selected_item1);

		/**
		 * Puts the arrays in the arraylist.
		 */
		for (int i = 0; i < material.length; i++) {
			Materials s = new Materials(material[i], materialNo[i]);
			materialList.add(s);

			/**
			 * Remove item. When on button accept, the item from the list view,
			 * at the startup window, is removed.
			 * 
			 */
			accept.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {

					int deletePos = ListActivity.deletePos;
					ListActivity.items.remove(deletePos);
					ListActivity.adapter.notifyDataSetChanged();

					finish();

				}

			});

			/**
			 * This button leads to a custom dialog.
			 * 
			 */
			edit.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					showDialog();
				}
			});
		}
	}

	/**
	 * This method inflate and shows the custom dialog.
	 * 
	 */
	private void showDialog() {
		dialog = new Dialog(this);

		View view = getLayoutInflater().inflate(R.layout.material_list, null);
		searchList = (ListView) view.findViewById(R.id.searchList);
		dialog.setTitle("Välj ny artikel");

		final MaterialAdapter adapter = new MaterialAdapter(
				InformationActivity.this, materialList);

		dialog.setContentView(view);

		searchList.setAdapter(adapter);

		/**
		 * onItemClick of the custom list view, gets the position of the item
		 * and toast, makes a text, to the user.
		 */

		searchList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				/**
				 * Gets the position of the item, even it the list view changes
				 * on filter, search.
				 * 
				 */
				Materials itemMat = (Materials) parent.getAdapter().getItem(
						position);

				resultProduct = itemMat.materialName;
				resultProductNo = itemMat.materialNo;

				/**
				 * Toast, shows a message with the material name and material
				 * number to the user.
				 */
				result = resultProduct + " " + resultProductNo;
				Toast.makeText(getApplicationContext(), result,
						Toast.LENGTH_SHORT).show();

			}

		});

		/**
		 * The edit text field is the search field. The edit text got a text
		 * watcher, which allows to insert a on text changed, so when the user
		 * types something in the search field, the list view changes along.
		 */
		search = (EditText) dialog.findViewById(R.id.search);

		search.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				adapter.getFilter().filter(s);

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

		/**
		 * The cancel button dismiss the dialog.
		 */
		cancel = (Button) dialog.findViewById(R.id.btnCancel);
		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();

			}
		});
		/**
		 * The OK button sets the text from which the user selected in the list
		 * view, either by searching or scrolling, and inserts it into the
		 * fields in the information activity, and dismiss the dialog.
		 * 
		 */
		ok = (Button) dialog.findViewById(R.id.btnOK);
		ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				product.setText(resultProduct);
				productNo.setText(resultProductNo);
				dialog.dismiss();

			}
		});

		dialog.show();

	}

}
