package com.achijones.profootballcoach;

import PFCpack.TeamStrategy;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

class MainActivity$33
  implements AdapterView.OnItemSelectedListener
{
  MainActivity$33(MainActivity paramMainActivity, TextView paramTextView, TeamStrategy[] paramArrayOfTeamStrategy) {}
  
  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    val$offStratDescription.setText(val$tsOff[paramInt].getStratDescription());
    this$0.userTeam.teamStratOff = val$tsOff[paramInt];
    this$0.userTeam.teamStratOffNum = paramInt;
  }
  
  public void onNothingSelected(AdapterView<?> paramAdapterView) {}
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MainActivity.33
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */