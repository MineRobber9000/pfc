package com.achijones.profootballcoach;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;

class MainActivity$67
  implements AdapterView.OnItemSelectedListener
{
  MainActivity$67(MainActivity paramMainActivity, ListView paramListView, PlayerStatsListArrayAdapter paramPlayerStatsListArrayAdapter1, PlayerStatsListArrayAdapter paramPlayerStatsListArrayAdapter2) {}
  
  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if (paramInt == 0)
    {
      val$playerList.setAdapter(val$retiringPlayersAdapter);
      return;
    }
    val$playerList.setAdapter(val$teamFAAdapter);
  }
  
  public void onNothingSelected(AdapterView<?> paramAdapterView) {}
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MainActivity.67
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */