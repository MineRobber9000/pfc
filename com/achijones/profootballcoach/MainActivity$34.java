package com.achijones.profootballcoach;

import PFCpack.TeamStrategy;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

class MainActivity$34
  implements AdapterView.OnItemSelectedListener
{
  MainActivity$34(MainActivity paramMainActivity, TextView paramTextView, TeamStrategy[] paramArrayOfTeamStrategy) {}
  
  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    val$defStratDescription.setText(val$tsDef[paramInt].getStratDescription());
    this$0.userTeam.teamStratDef = val$tsDef[paramInt];
    this$0.userTeam.teamStratDefNum = paramInt;
  }
  
  public void onNothingSelected(AdapterView<?> paramAdapterView) {}
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MainActivity.34
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */