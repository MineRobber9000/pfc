package com.achijones.profootballcoach;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import java.io.PrintStream;

class OnlineActivity$5
  implements AdapterView.OnItemSelectedListener
{
  OnlineActivity$5(OnlineActivity paramOnlineActivity) {}
  
  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    System.out.println("selected in spinner " + paramInt);
    if (paramInt == 0)
    {
      OnlineActivity.access$100(this$0, true);
      return;
    }
    OnlineActivity.access$100(this$0, false);
  }
  
  public void onNothingSelected(AdapterView<?> paramAdapterView) {}
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.OnlineActivity.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */