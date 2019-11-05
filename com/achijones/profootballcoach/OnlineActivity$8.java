package com.achijones.profootballcoach;

import PFCpack.Team;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import org.json.JSONObject;

class OnlineActivity$8
  implements DialogInterface.OnClickListener
{
  OnlineActivity$8(OnlineActivity paramOnlineActivity) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    try
    {
      paramDialogInterface = new JSONObject();
      paramDialogInterface.put("name", this$0.userTeam.name);
      paramDialogInterface.put("off_strat", this$0.userTeam.teamStratOffNum);
      paramDialogInterface.put("def_strat", this$0.userTeam.teamStratDefNum);
      new OnlineActivity.UpdateTeamStratsOnline(this$0).execute(new String[] { paramDialogInterface.toString() });
      return;
    }
    catch (Exception paramDialogInterface)
    {
      paramDialogInterface.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.OnlineActivity.8
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */