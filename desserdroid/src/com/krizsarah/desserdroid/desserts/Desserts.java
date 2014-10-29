package com.krizsarah.desserdroid.desserts;

import com.krizsarah.desserdroid.BaseListSample;
import com.krizsarah.desserdroid.Item;
import com.krizsarah.desserdroid.R;
import com.krizsarah.desserdroid.R.drawable;
import com.krizsarah.desserdroid.R.id;
import com.krizsarah.desserdroid.R.layout;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class Desserts extends BaseListSample {

    private static final String STATE_CONTENT_TEXT = "com.krizsarah.desserdroid.desserts.Desserts.contentText";

    private String mTitleText, mContentText;
    private int mDesserImg;
    private TextView mTitleTextView, mContentTextView;
    private ImageView mImageView;
    private LinearLayout mFullDisplay;

    @Override
    protected void onCreate(Bundle inState) {
        super.onCreate(inState);

        if (inState != null) {
            mTitleText = inState.getString(STATE_CONTENT_TEXT);
        }

        mMenuDrawer.setContentView(R.layout.activity_contentsample);
        mMenuDrawer.setTouchMode(MenuDrawer.TOUCH_MODE_FULLSCREEN);
        mMenuDrawer.setSlideDrawable(R.drawable.ic_drawer);
        mMenuDrawer.setDrawerIndicatorEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mFullDisplay = (LinearLayout) findViewById(R.id.fullDisplay);
    	mFullDisplay.setBackgroundResource(R.drawable.background);
        
        mTitleTextView = (TextView) findViewById(R.id.titleText);
        mTitleTextView.setText(mTitleText);
        
        mImageView = (ImageView) findViewById(R.id.desserScreenShootImg);
        
        mContentTextView = (TextView) findViewById(R.id.contentText);

        
        
        mMenuDrawer.setOnInterceptMoveEventListener(new MenuDrawer.OnInterceptMoveEventListener() {
            @Override
            public boolean isViewDraggable(View v, int dx, int x, int y) {
                return v instanceof SeekBar;
            }
        });
    }

    @Override
    protected void onMenuItemClicked(int position, Item item) {
        mTitleTextView.setText(item.mTitle);
        
        switch(position) {
        case 0:
        	mFullDisplay.setBackgroundColor(Color.WHITE);
        	mImageView.setBackgroundResource(R.drawable.img_yema);
        	mContentTextView.setText(R.string.yema_cake);
        	
        	 break;
        case 1:
        	mFullDisplay.setBackgroundColor(Color.WHITE);
        	mImageView.setBackgroundResource(R.drawable.img_sinukmani);
        	mContentTextView.setText(R.string.sinukmani);
        	break;
        case 2:
        	mFullDisplay.setBackgroundColor(Color.WHITE);
        	mImageView.setBackgroundResource(R.drawable.img_lechetin);
        	mContentTextView.setText(R.string.lechetin);
        	break;
        case 3:
        	mFullDisplay.setBackgroundColor(Color.WHITE);
        	mImageView.setBackgroundResource(R.drawable.img_cathedral_window);
        	mContentTextView.setText(R.string.cathedral_window);
        	break;
        case 4:
        	mFullDisplay.setBackgroundColor(Color.WHITE);
        	mImageView.setBackgroundResource(R.drawable.img_bibingka);
        	mContentTextView.setText(R.string.sinukmani);
        	break;
        case 5:
        	mFullDisplay.setBackgroundColor(Color.WHITE);
        	mImageView.setBackgroundResource(R.drawable.img_kalamay);
        	mContentTextView.setText(R.string.lechetin);
        	break;
        case 6:
        	mFullDisplay.setBackgroundColor(Color.WHITE);
        	mImageView.setBackgroundResource(R.drawable.img_butsi);
        	mContentTextView.setText(R.string.cathedral_window);
        	break;
        
        }
        
        mMenuDrawer.closeMenu();
    }

    @Override
    protected int getDragMode() {
        return MenuDrawer.MENU_DRAG_CONTENT;
    }

    @Override
    protected Position getDrawerPosition() {
        return Position.START;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_CONTENT_TEXT, mTitleText);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mMenuDrawer.toggleMenu();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        final int drawerState = mMenuDrawer.getDrawerState();
        if (drawerState == MenuDrawer.STATE_OPEN || drawerState == MenuDrawer.STATE_OPENING) {
            mMenuDrawer.closeMenu();
            return;
        }

        super.onBackPressed();
    }
}
