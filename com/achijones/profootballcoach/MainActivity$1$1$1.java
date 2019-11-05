package com.achijones.profootballcoach;

import PFCpack.League;
import PFCpack.Team;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.v7.app.ActionBar;
import java.util.ArrayList;

class MainActivity$1$1$1
  implements DialogInterface.OnClickListener
{
  MainActivity$1$1$1(MainActivity.1.1 param1) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    this$2.this$1.this$0.userTeam.userControlled = false;
    this$2.this$1.this$0.userTeam = ((Team)this$2.this$1.this$0.simLeague.teamList.get(paramInt));
    this$2.this$1.this$0.simLeague.userTeam = this$2.this$1.this$0.userTeam;
    this$2.this$1.this$0.userTeam.userControlled = true;
    this$2.this$1.this$0.userTeamStr = this$2.this$1.this$0.userTeam.name;
    this$2.this$1.this$0.currentTeam = this$2.this$1.this$0.userTeam;
    this$2.this$1.this$0.getSupportActionBar().setTitle(this$2.this$1.this$0.userTeam.name + " " + this$2.this$1.this$0.season + " Season");
    this$2.this$1.this$0.examineTeam(this$2.this$1.this$0.currentTeam.name);
    this$2.this$1.this$0.currTab = 1;
    MainActivity.access$000(this$2.this$1.this$0);
    MainActivity.access$100(this$2.this$1.this$0);
    this$2.this$1.this$0.showTutorialTeamRoster();
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MainActivity.1.1.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */