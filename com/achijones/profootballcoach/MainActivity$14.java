package com.achijones.profootballcoach;

import PFCpack.League;
import PFCpack.Team;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;

class MainActivity$14
  implements AdapterView.OnItemSelectedListener
{
  MainActivity$14(MainActivity paramMainActivity, ListView paramListView, String[] paramArrayOfString) {}
  
  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if (paramInt == 0) {
      val$potyList.setAdapter(new SeasonAwardsListArrayAdapter(this$0, this$0.simLeague.getMVPCeremonyStr().split(">"), this$0.userTeam.abbr));
    }
    do
    {
      return;
      if (paramInt == 1)
      {
        val$potyList.setAdapter(new SeasonAwardsListArrayAdapter(this$0, val$allPros, this$0.userTeam.abbr));
        return;
      }
      if (paramInt == 2)
      {
        val$potyList.setAdapter(new TeamRankingsListArrayAdapter(this$0, this$0.simLeague.getTeamRankingsStr(1), this$0.userTeam.strRepWithBowlResults()));
        return;
      }
    } while (paramInt != 3);
    val$potyList.setAdapter(new TeamRankingsListArrayAdapter(this$0, this$0.simLeague.getTeamRankingsStr(2), this$0.userTeam.strRepWithBowlResults()));
  }
  
  public void onNothingSelected(AdapterView<?> paramAdapterView) {}
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MainActivity.14
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */