package com.achijones.profootballcoach;

import PFCpack.Division;
import PFCpack.League;
import PFCpack.Team;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.v7.app.ActionBar;
import java.util.ArrayList;

class MainActivity$1
  implements Runnable
{
  MainActivity$1(MainActivity paramMainActivity, boolean paramBoolean, ProgressDialog paramProgressDialog) {}
  
  public void run()
  {
    if (!val$loadedRoster) {
      this$0.simLeague.generatePlayersNewLeague();
    }
    this$0.runOnUiThread(new Runnable()
    {
      public void run()
      {
        val$dialogLoading.dismiss();
        this$0.currentTeam = ((Team)this$0.simLeague.teamList.get(0));
        this$0.currentDivision = ((Division)this$0.simLeague.divisions.get(0));
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this$0);
        localBuilder.setTitle("Choose your team:");
        localBuilder.setItems(this$0.simLeague.getTeamListStr(), new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            this$0.userTeam.userControlled = false;
            this$0.userTeam = ((Team)this$0.simLeague.teamList.get(paramAnonymous2Int));
            this$0.simLeague.userTeam = this$0.userTeam;
            this$0.userTeam.userControlled = true;
            this$0.userTeamStr = this$0.userTeam.name;
            this$0.currentTeam = this$0.userTeam;
            this$0.getSupportActionBar().setTitle(this$0.userTeam.name + " " + this$0.season + " Season");
            this$0.examineTeam(this$0.currentTeam.name);
            this$0.currTab = 1;
            MainActivity.access$000(this$0);
            MainActivity.access$100(this$0);
            this$0.showTutorialTeamRoster();
          }
        });
        localBuilder.create().show();
      }
    });
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MainActivity.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */