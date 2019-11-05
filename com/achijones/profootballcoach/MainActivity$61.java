package com.achijones.profootballcoach;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Spinner;

class MainActivity$61
  implements View.OnClickListener
{
  MainActivity$61(MainActivity paramMainActivity, Spinner paramSpinner, String[] paramArrayOfString) {}
  
  public void onClick(View paramView)
  {
    int j = val$tradeSpinner.getSelectedItemPosition() + 1;
    int i = j;
    if (j > val$teamsSelection.length - 1) {
      i = 0;
    }
    val$tradeSpinner.setSelection(i);
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MainActivity.61
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */