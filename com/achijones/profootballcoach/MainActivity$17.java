package com.achijones.profootballcoach;

import PFCpack.TeamStrategy;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

class MainActivity$17
  implements AdapterView.OnItemSelectedListener
{
  MainActivity$17(MainActivity paramMainActivity, TeamStrategy[] paramArrayOfTeamStrategy) {}
  
  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    this$0.userTeam.teamStratOff = val$tsOff[paramInt];
    this$0.userTeam.teamStratOffNum = paramInt;
  }
  
  public void onNothingSelected(AdapterView<?> paramAdapterView) {}
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MainActivity.17
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */