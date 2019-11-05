package com.achijones.profootballcoach;

import PFCpack.League;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

class MainActivity$3
  implements AdapterView.OnItemSelectedListener
{
  MainActivity$3(MainActivity paramMainActivity) {}
  
  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    this$0.currentDivision = this$0.simLeague.findDivision(paramAdapterView.getItemAtPosition(paramInt).toString());
    MainActivity.access$300(this$0);
  }
  
  public void onNothingSelected(AdapterView<?> paramAdapterView) {}
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MainActivity.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */