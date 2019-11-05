package com.achijones.profootballcoach;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.TextView;

class Home$6
  implements DialogInterface.OnClickListener
{
  Home$6(Home paramHome, String[] paramArrayOfString) {}
  
  public void onClick(DialogInterface paramDialogInterface, final int paramInt)
  {
    if (val$fileInfos[paramInt].equals("EMPTY"))
    {
      this$0.beginNewLeagueDialog(paramInt);
      return;
    }
    paramDialogInterface = new AlertDialog.Builder(this$0);
    paramDialogInterface.setMessage("Are you sure you want to use this save file? It will overwrite the league currently saved.\n\n" + val$fileInfos[paramInt]).setPositiveButton("Yes, Overwrite", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        this$0.beginNewLeagueDialog(paramInt);
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
 * Qualified Name:     com.achijones.profootballcoach.Home.6
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */