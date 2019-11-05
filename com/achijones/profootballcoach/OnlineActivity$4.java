package com.achijones.profootballcoach;

import PFCpack.Team;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import org.json.JSONObject;

class OnlineActivity$4
  implements View.OnClickListener
{
  OnlineActivity$4(OnlineActivity paramOnlineActivity) {}
  
  public void onClick(View paramView)
  {
    if (!this$0.playWeekButton.getText().toString().equals("Schedule Ranked Game"))
    {
      this$0.playGame();
      this$0.playWeekButton.setText("Schedule Ranked Game");
      return;
    }
    this$0.gettingUserTeam = false;
    this$0.gettingRandTeam = true;
    try
    {
      paramView = new JSONObject();
      paramView.put("name", this$0.userTeam.name);
      new OnlineActivity.GetTeamOnline(this$0).execute(new String[] { paramView.toString() });
      this$0.playWeekButton.setText("Play Ranked Game");
      return;
    }
    catch (Exception paramView)
    {
      for (;;)
      {
        paramView.printStackTrace();
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.OnlineActivity.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */