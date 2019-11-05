package com.achijones.profootballcoach;

import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

class MainActivity$11
  implements ExpandableListView.OnChildClickListener
{
  MainActivity$11(MainActivity paramMainActivity, ExpandableListAdapterPlayerStats paramExpandableListAdapterPlayerStats) {}
  
  public boolean onChildClick(ExpandableListView paramExpandableListView, View paramView, int paramInt1, int paramInt2, long paramLong)
  {
    if (val$expListAdapterPlayerStats.getGroup(paramInt1).equals("BENCH > BENCH")) {
      this$0.examinePlayer(val$expListAdapterPlayerStats.getChild(paramInt1, paramInt2));
    }
    for (;;)
    {
      return false;
      if (val$expListAdapterPlayerStats.getGroup(paramInt1).equals("DRAFT PICKS > DRAFT PICKS")) {
        this$0.addTradePick(val$expListAdapterPlayerStats.getChild(paramInt1, paramInt2));
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MainActivity.11
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */