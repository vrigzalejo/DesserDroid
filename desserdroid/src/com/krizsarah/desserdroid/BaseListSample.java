package com.krizsarah.desserdroid;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseListSample extends FragmentActivity implements
		MenuAdapter.MenuListener {

	private static final String STATE_ACTIVE_POSITION = "net.simonvt.menudrawer.samples.LeftDrawerSample.activePosition";

	protected MenuDrawer mMenuDrawer;

	protected MenuAdapter mAdapter;
	protected ListView mList;

	private int mActivePosition = 0;

	@Override
	protected void onCreate(Bundle inState) {
		super.onCreate(inState);

		if (inState != null) {
			mActivePosition = inState.getInt(STATE_ACTIVE_POSITION);
		}

		mMenuDrawer = MenuDrawer.attach(this, MenuDrawer.Type.BEHIND,
				getDrawerPosition(), getDragMode());

		Intent i = getIntent();
		Bundle b = i.getExtras();
		List<Object> items = new ArrayList<Object>();
		
		switch (b.getInt("pos")) {

		case 0: // Main dishes
			items.add(new Item("Yema Cake", R.drawable.ic_food));
			items.add(new Item("Sinukmani", R.drawable.ic_food));
			items.add(new Item("Lechetin", R.drawable.ic_food));
			items.add(new Item("Cathedral Window", R.drawable.ic_food));
			items.add(new Item("Bibingka Royal", R.drawable.ic_food));
			items.add(new Item("Kalamay", R.drawable.ic_food));
			items.add(new Item("Butsi", R.drawable.ic_food));
			items.add(new Item("Nilupak", R.drawable.ic_food));
			items.add(new Item("Espasol", R.drawable.ic_food));
			items.add(new Item("Suman sa Lihiya", R.drawable.ic_food));
			items.add(new Item("Puto", R.drawable.ic_food));
			items.add(new Item("Kwek Kwek", R.drawable.ic_food));
			items.add(new Item("Inangit", R.drawable.ic_food));
			items.add(new Item("Suman sa Gata (Binut-ong)", R.drawable.ic_food));
			items.add(new Item("Ube Macapuno Panna Cotta", R.drawable.ic_food));
			items.add(new Item("Karioka", R.drawable.ic_food));
			items.add(new Item("Inutak", R.drawable.ic_food));
			items.add(new Item("Maja de Fruta", R.drawable.ic_food));
			items.add(new Item("Suman Moron", R.drawable.ic_food));
			items.add(new Item("Binagol", R.drawable.ic_food));
			
			
			mList = new ListView(this);

			mAdapter = new MenuAdapter(this, items);
			mAdapter.setListener(this);
			mAdapter.setActivePosition(mActivePosition);

			mList.setAdapter(mAdapter);
			mList.setOnItemClickListener(mItemClickListener);

			mMenuDrawer.setMenuView(mList);
			setTitle("Pinoy Desserts");
			break;
		case 1: // Watch Videos
			items.add(new Category("Under Construction"));
			items.add(new Item("Under Construction", R.drawable.ic_action_refresh_dark));
			
			mList = new ListView(this);

			mAdapter = new MenuAdapter(this, items);
			mAdapter.setListener(this);
			mAdapter.setActivePosition(mActivePosition);

			mList.setAdapter(mAdapter);
			mList.setOnItemClickListener(mItemClickListener);

			mMenuDrawer.setMenuView(mList);
			setTitle("Watch Videos");
			break;
		case 2: // About Us
			items.add(new Category("Under Construction"));
			items.add(new Item("Under Construction", R.drawable.ic_action_refresh_dark));
				
			
			mList = new ListView(this);

			mAdapter = new MenuAdapter(this, items);
			mAdapter.setListener(this);
			mAdapter.setActivePosition(mActivePosition);

			mList.setAdapter(mAdapter);
			mList.setOnItemClickListener(mItemClickListener);

			mMenuDrawer.setMenuView(mList);
			setTitle("About Us");
			break;
	
		}
		
	}

	protected abstract void onMenuItemClicked(int position, Item item);

	protected abstract int getDragMode();

	protected abstract Position getDrawerPosition();

	private AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			mActivePosition = position;
			mMenuDrawer.setActiveView(view, position);
			mAdapter.setActivePosition(position);
			onMenuItemClicked(position, (Item) mAdapter.getItem(position));
		}
	};

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(STATE_ACTIVE_POSITION, mActivePosition);
	}

	@Override
	public void onActiveViewChanged(View v) {
		mMenuDrawer.setActiveView(v, mActivePosition);
	}
}
