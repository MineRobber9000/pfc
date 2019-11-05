package com.achijones.profootballcoach;

import PFCpack.League;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.Toast;
import java.io.File;

class MainActivity$70$2
  implements DialogInterface.OnClickListener
{
  MainActivity$70$2(MainActivity.70 param70, int paramInt) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    this$1.this$0.saveLeagueFile = new File(this$1.this$0.getFilesDir(), "saveFile" + val$itemy + ".cfb");
    this$1.this$0.saveTeamsFile = new File(this$1.this$0.getFilesDir(), "saveFile" + val$itemy + "_teams.cfb");
    this$1.this$0.saveScheduleFile = new File(this$1.this$0.getFilesDir(), "saveFile" + val$itemy + "_schedules.cfb");
    this$1.this$0.saveTeamHistFile = new File(this$1.this$0.getFilesDir(), "saveFile" + val$itemy + "_teamhist.cfb");
    this$1.this$0.simLeague.saveLeague(this$1.this$0.saveLeagueFile, this$1.this$0.saveTeamsFile, this$1.this$0.saveScheduleFile, this$1.this$0.saveTeamHistFile, false);
    Toast.makeText(this$1.this$0, "Saved league!", 0).show();
    paramDialogInterface.dismiss();
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MainActivity.70.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */