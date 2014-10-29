package com.krizsarah.desserdroid;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.krizsarah.desserdroid.desserts.Desserts;


public class MainActivity extends ListActivity {

    private SamplesAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new SamplesAdapter();

        Class desserts = Desserts.class;
        
        //mAdapter.addHeader("Sliding drawer");
        mAdapter.addSample(R.drawable.ic_food, "Pinoy Desserts", "Enjoy the delicious recipes of Pinoy desserts.", desserts);
        mAdapter.addSample(R.drawable.ic_action_video, "Watch Videos", "Watch Pinoy desserts on YouTube.", desserts);
        mAdapter.addSample(R.drawable.ic_action_about, "About Us", "", desserts);
        mAdapter.addSample(R.drawable.ic_action_share, "Share this on Facebook", "", desserts);
        /*
        mAdapter.addSample("Vegetarian", "", Vegetarian.class);
         */

        setListAdapter(mAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        SampleItem sample = (SampleItem) mAdapter.getItem(position);
        Intent i = new Intent(this, sample.mClazz);
        i.putExtra("pos", // key
        		position); 
        startActivity(i);
    }

    private static class Header {

        String mTitle;

        public Header(String title) {
            mTitle = title;
        }
    }

    private static class SampleItem {

        String mTitle;
        String mSummary;
        Class mClazz;
        int mIcon;

        public SampleItem(int icon, String title, String summary, Class clazz) {
            mIcon = icon;
        	mTitle = title;
            mSummary = summary;
            mClazz = clazz;
        }
    }

    public class SamplesAdapter extends BaseAdapter {

        private List<Object> mItems = new ArrayList<Object>();

        public void addHeader(String title) {
            mItems.add(new Header(title));
        }
        /*
        public void addSample(String title, String summary, Class clazz) {
            mItems.add(new SampleItem(title, summary, clazz));
        }
        */
        public void addSample(int icon, String title, String summary, Class classs) {
        	mItems.add(new SampleItem(icon, title, summary, classs));
        }

        @Override
        public int getCount() {
            return mItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemViewType(int position) {
            return getItem(position) instanceof Header ? 0 : 1;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (getItemViewType(position) == 0) {
                TextView v = (TextView) convertView;
                if (v == null) {
                    v = (TextView) getLayoutInflater().inflate(R.layout.list_row_sample_header, parent, false);
                }

                v.setText(((Header) getItem(position)).mTitle);

                return v;

            } else {
                SampleItem sample = (SampleItem) getItem(position);

                View v = convertView;
                if (v == null) {
                    v = getLayoutInflater().inflate(R.layout.list_row_sample, parent, false);
                }
                
                ((ImageView) v.findViewById(R.id.listicon)).setBackgroundResource(sample.mIcon);
                ((TextView) v.findViewById(R.id.title)).setText(sample.mTitle);
                ((TextView) v.findViewById(R.id.summary)).setText(sample.mSummary);

                return v;
            }
        }
    }
}
