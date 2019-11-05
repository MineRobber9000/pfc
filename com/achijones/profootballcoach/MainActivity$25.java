package com.achijones.profootballcoach;

import PFCpack.League;
import PFCpack.LeagueRecords;
import PFCpack.Team;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;

class MainActivity$25
  implements AdapterView.OnItemSelectedListener
{
  MainActivity$25(MainActivity paramMainActivity, ListView paramListView) {}
  
  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if (paramInt == 0)
    {
      paramAdapterView = new TeamHistoryListArrayAdapter(this$0, this$0.userTeam.getTeamHistoryList());
      val$teamHistoryList.setAdapter(paramAdapterView);
    }
    while (paramInt != 1) {
      return;
    }
    paramAdapterView = new LeagueRecordsListArrayAdapter(this$0, this$0.simLeague.userTeamRecords.getRecordsStr().split("\n"), "---");
    val$teamHistoryList.setAdapter(paramAdapterView);
  }
  
  public void onNothingSelected(AdapterView<?> paramAdapterView) {}
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MainActivity.25
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */