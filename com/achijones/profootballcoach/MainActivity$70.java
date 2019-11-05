package com.achijones.profootballcoach;

import PFCpack.League;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;

class MainActivity$70
  implements DialogInterface.OnClickListener
{
  MainActivity$70(MainActivity paramMainActivity, String[] paramArrayOfString) {}
  
  public void onClick(DialogInterface paramDialogInterface, final int paramInt)
  {
    if (val$fileInfos[paramInt].equals("EMPTY"))
    {
      this$0.saveLeagueFile = new File(this$0.getFilesDir(), "saveFile" + paramInt + ".cfb");
      this$0.saveTeamsFile = new File(this$0.getFilesDir(), "saveFile" + paramInt + "_teams.cfb");
      this$0.saveScheduleFile = new File(this$0.getFilesDir(), "saveFile" + paramInt + "_schedules.cfb");
      this$0.saveTeamHistFile = new File(this$0.getFilesDir(), "saveFile" + paramInt + "_teamhist.cfb");
      this$0.simLeague.saveLeague(this$0.saveLeagueFile, this$0.saveTeamsFile, this$0.saveScheduleFile, this$0.saveTeamHistFile, false);
      Toast.makeText(this$0, "Saved league!", 0).show();
      paramDialogInterface.dismiss();
      return;
    }
    paramDialogInterface = new AlertDialog.Builder(this$0);
    paramDialogInterface.setMessage("Are you sure you want to overwrite this save file?\n\n" + val$fileInfos[paramInt]).setPositiveButton("Yes, Overwrite", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        this$0.saveLeagueFile = new File(this$0.getFilesDir(), "saveFile" + paramInt + ".cfb");
        this$0.saveTeamsFile = new File(this$0.getFilesDir(), "saveFile" + paramInt + "_teams.cfb");
        this$0.saveScheduleFile = new File(this$0.getFilesDir(), "saveFile" + paramInt + "_schedules.cfb");
        this$0.saveTeamHistFile = new File(this$0.getFilesDir(), "saveFile" + paramInt + "_teamhist.cfb");
        this$0.simLeague.saveLeague(this$0.saveLeagueFile, this$0.saveTeamsFile, this$0.saveScheduleFile, this$0.saveTeamHistFile, false);
        Toast.makeText(this$0, "Saved league!", 0).show();
        paramAnonymousDialogInterface.dismiss();
      }
    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    });
    paramDialogInterface = paramDialogInterface.create();
    paramDialogInterface.show();
    ((TextView)paramDialogInterface.findViewById(16908299)).setTextSize(2, 14.0F);
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MainActivity.70
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */