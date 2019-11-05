package com.achijones.profootballcoach;

import PFCpack.League;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

class MainActivity$2
  implements AdapterView.OnItemSelectedListener
{
  MainActivity$2(MainActivity paramMainActivity) {}
  
  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    this$0.currentTeam = this$0.simLeague.findTeam(paramAdapterView.getItemAtPosition(paramInt).toString());
    MainActivity.access$200(this$0);
  }
  
  public void onNothingSelected(AdapterView<?> paramAdapterView) {}
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MainActivity.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */