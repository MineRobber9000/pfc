package com.achijones.profootballcoach;

import PFCpack.Team;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;

class MainActivity$27
  implements AdapterView.OnItemSelectedListener
{
  MainActivity$27(MainActivity paramMainActivity, Team paramTeam, ListView paramListView) {}
  
  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if (paramInt == 0)
    {
      paramAdapterView = new TeamHistoryListArrayAdapter(this$0, val$examineTeam.getTeamHistoryList());
      val$teamHistoryList.setAdapter(paramAdapterView);
    }
  }
  
  public void onNothingSelected(AdapterView<?> paramAdapterView) {}
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MainActivity.27
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */