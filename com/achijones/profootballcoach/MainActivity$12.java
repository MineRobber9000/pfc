package com.achijones.profootballcoach;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ExpandableListView;

class MainActivity$12
  implements AdapterView.OnItemLongClickListener
{
  MainActivity$12(MainActivity paramMainActivity, ExpandableListAdapterPlayerStats paramExpandableListAdapterPlayerStats) {}
  
  public boolean onItemLongClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if (ExpandableListView.getPackedPositionType(paramLong) == 1)
    {
      paramInt = ExpandableListView.getPackedPositionGroup(paramLong);
      int i = ExpandableListView.getPackedPositionChild(paramLong);
      if (val$expListAdapterPlayerStats.getGroup(paramInt).equals("BENCH > BENCH")) {
        this$0.addTradePlayer(val$expListAdapterPlayerStats.getChild(paramInt, i));
      }
      while (!val$expListAdapterPlayerStats.getGroup(paramInt).equals("DRAFT PICKS > DRAFT PICKS")) {
        return true;
      }
      this$0.addTradePick(val$expListAdapterPlayerStats.getChild(paramInt, i));
      return true;
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MainActivity.12
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */