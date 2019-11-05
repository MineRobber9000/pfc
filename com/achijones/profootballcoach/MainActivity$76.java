package com.achijones.profootballcoach;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.TextView;

class MainActivity$76
  implements DialogInterface.OnClickListener
{
  MainActivity$76(MainActivity paramMainActivity, String[] paramArrayOfString) {}
  
  public void onClick(DialogInterface paramDialogInterface, final int paramInt)
  {
    if (val$onlineTeams[paramInt].equals("EMPTY"))
    {
      this$0.insertTeamOnline(paramInt, "");
      return;
    }
    paramDialogInterface = new AlertDialog.Builder(this$0);
    paramDialogInterface.setMessage("Are you sure you want to use this? It will replace the team currently online:\n\n" + val$onlineTeams[paramInt]).setPositiveButton("Yes, Overwrite", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        this$0.insertTeamOnline(paramInt, val$onlineTeams[paramInt]);
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
 * Qualified Name:     com.achijones.profootballcoach.MainActivity.76
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */