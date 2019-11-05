package com.achijones.profootballcoach;

import PFCpack.League;
import PFCpack.Team;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;

class MainActivity$22
  implements AdapterView.OnItemSelectedListener
{
  MainActivity$22(MainActivity paramMainActivity, ListView paramListView, String[] paramArrayOfString) {}
  
  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if (paramInt == 0)
    {
      paramAdapterView = new LeagueHistoryListArrayAdapter(this$0, this$0.simLeague.getLeagueHistoryStr().split("%"), this$0.userTeam.abbr);
      val$leagueHistoryList.setAdapter(paramAdapterView);
      return;
    }
    if (paramInt == 1)
    {
      paramAdapterView = new LeagueRecordsListArrayAdapter(this$0, this$0.simLeague.getLeagueRecordsStr().split("\n"), this$0.userTeam.abbr);
      val$leagueHistoryList.setAdapter(paramAdapterView);
      return;
    }
    paramAdapterView = new HallOfFameListArrayAdapter(this$0, val$hofPlayers);
    val$leagueHistoryList.setAdapter(paramAdapterView);
  }
  
  public void onNothingSelected(AdapterView<?> paramAdapterView) {}
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MainActivity.22
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */