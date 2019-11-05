package com.achijones.profootballcoach;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.Spinner;

class MainActivity$47
  implements View.OnClickListener
{
  MainActivity$47(MainActivity paramMainActivity, Spinner paramSpinner1, Spinner paramSpinner2, Spinner paramSpinner3, Spinner paramSpinner4, Spinner paramSpinner5, Spinner paramSpinner6, Spinner paramSpinner7, CheckBox paramCheckBox) {}
  
  public void onClick(View paramView)
  {
    boolean bool = true;
    this$0.findFilter[0] = 0;
    this$0.findFilter[1] = 50;
    this$0.findFilter[2] = 50;
    this$0.findFilter[3] = 50;
    this$0.findFilter[4] = 50;
    this$0.findFilter[5] = 20;
    this$0.findFilter[6] = 40;
    this$0.findFilter[7] = 0;
    val$filterPosSpinner.setSelection(this$0.findFilter[0]);
    val$filterOvrSpinner.setSelection((this$0.findFilter[1] - 50) / 5);
    val$filterPotSpinner.setSelection((this$0.findFilter[2] - 50) / 10);
    val$filterDurSpinner.setSelection((this$0.findFilter[3] - 50) / 10);
    val$filterFootIQSpinner.setSelection((this$0.findFilter[4] - 50) / 10);
    val$filterMinAgeSpinner.setSelection(this$0.findFilter[5] - 20);
    val$filterMaxAgeSpinner.setSelection(this$0.findFilter[6] - 20);
    paramView = val$filterInjuredPlayers;
    if (this$0.findFilter[7] == 1) {}
    for (;;)
    {
      paramView.setChecked(bool);
      return;
      bool = false;
    }
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MainActivity.47
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */