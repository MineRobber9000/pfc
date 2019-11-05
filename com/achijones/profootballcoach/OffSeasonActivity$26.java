package com.achijones.profootballcoach;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

class OffSeasonActivity$26
  implements AdapterView.OnItemSelectedListener
{
  OffSeasonActivity$26(OffSeasonActivity paramOffSeasonActivity) {}
  
  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    this$0.setMinFootIQ(paramInt * 10 + 50);
  }
  
  public void onNothingSelected(AdapterView<?> paramAdapterView) {}
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.OffSeasonActivity.26
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */