package com.achijones.profootballcoach;

import PFCpack.TeamStrategy;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

class MainActivity$18
  implements AdapterView.OnItemSelectedListener
{
  MainActivity$18(MainActivity paramMainActivity, TeamStrategy[] paramArrayOfTeamStrategy) {}
  
  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    this$0.userTeam.teamStratDef = val$tsDef[paramInt];
    this$0.userTeam.teamStratDefNum = paramInt;
  }
  
  public void onNothingSelected(AdapterView<?> paramAdapterView) {}
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MainActivity.18
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */