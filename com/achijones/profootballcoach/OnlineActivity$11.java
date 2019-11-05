package com.achijones.profootballcoach;

import PFCpack.Team;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONObject;

class OnlineActivity$11
  implements DialogInterface.OnClickListener
{
  OnlineActivity$11(OnlineActivity paramOnlineActivity, EditText paramEditText) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (val$input.getText().toString().trim().equals(this$0.userTeam.name))
    {
      Toast.makeText(this$0, "Can't schedule your own team!", 0).show();
      return;
    }
    try
    {
      this$0.gettingUserTeam = false;
      this$0.gettingRandTeam = false;
      paramDialogInterface = new JSONObject();
      paramDialogInterface.put("name", val$input.getText().toString().trim());
      new OnlineActivity.GetTeamOnline(this$0).execute(new String[] { paramDialogInterface.toString() });
      this$0.playWeekButton.setText("Play Custom Game");
      return;
    }
    catch (Exception paramDialogInterface)
    {
      paramDialogInterface.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.OnlineActivity.11
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */