package com.healthcare;

import android.view.View;

/**
 * Created by manojkulkarni on 2/24/17.
 */

public interface RecyclerClickListener {

    public void onItemClick(int position);
    public void onItemLongClick(View v, int position);
    public void onOverFlowMenuClick(View v, int position);
}
