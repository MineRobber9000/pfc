package com.achijones.profootballcoach;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

class MainActivity$49
  implements AdapterView.OnItemSelectedListener
{
  MainActivity$49(MainActivity paramMainActivity) {}
  
  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    this$0.findFilter[1] = (paramInt * 5 + 50);
  }
  
  public void onNothingSelected(AdapterView<?> paramAdapterView) {}
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MainActivity.49
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */