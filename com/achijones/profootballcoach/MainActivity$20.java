package com.achijones.profootballcoach;

import PFCpack.League;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

class MainActivity$20
  implements AdapterView.OnItemSelectedListener
{
  MainActivity$20(MainActivity paramMainActivity, TeamRankingsListArrayAdapter paramTeamRankingsListArrayAdapter) {}
  
  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    paramAdapterView = this$0.simLeague.getTeamRankingsStr(paramInt);
    val$teamRankingsAdapter.clear();
    val$teamRankingsAdapter.addAll(paramAdapterView);
    val$teamRankingsAdapter.notifyDataSetChanged();
  }
  
  public void onNothingSelected(AdapterView<?> paramAdapterView) {}
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MainActivity.20
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */