package com.achijones.profootballcoach;

import PFCpack.League;
import PFCpack.Team;
import android.app.AlertDialog;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

class MainActivity$37
  implements View.OnClickListener
{
  MainActivity$37(MainActivity paramMainActivity, EditText paramEditText, CheckBox paramCheckBox1, CheckBox paramCheckBox2, CheckBox paramCheckBox3, AlertDialog paramAlertDialog) {}
  
  public void onClick(View paramView)
  {
    paramView = val$changeNameEditText.getText().toString().trim();
    if (this$0.simLeague.isNameValid(paramView))
    {
      this$0.userTeam.name = paramView;
      this$0.getSupportActionBar().setTitle(this$0.userTeam.name + " " + this$0.season + " Season");
      this$0.examineTeam(this$0.userTeam.name);
    }
    for (;;)
    {
      this$0.showToasts = val$checkboxShowPopup.isChecked();
      this$0.showInjuryReport = val$checkboxShowInjury.isChecked();
      this$0.showTutorial = val$checkboxShowTutorial.isChecked();
      this$0.userTeam.showPopups = this$0.showToasts;
      val$dialog.dismiss();
      return;
      if (this$0.showToasts) {
        Toast.makeText(this$0, "Invalid name! Name not changed.", 0).show();
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MainActivity.37
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */