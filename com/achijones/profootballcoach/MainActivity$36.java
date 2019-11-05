package com.achijones.profootballcoach;

import android.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

class MainActivity$36
  implements View.OnClickListener
{
  MainActivity$36(MainActivity paramMainActivity, CheckBox paramCheckBox1, CheckBox paramCheckBox2, CheckBox paramCheckBox3, AlertDialog paramAlertDialog) {}
  
  public void onClick(View paramView)
  {
    this$0.showToasts = val$checkboxShowPopup.isChecked();
    this$0.showInjuryReport = val$checkboxShowInjury.isChecked();
    this$0.showTutorial = val$checkboxShowTutorial.isChecked();
    this$0.userTeam.showPopups = this$0.showToasts;
    val$dialog.dismiss();
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MainActivity.36
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */