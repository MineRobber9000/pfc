package com.achijones.profootballcoach;

import PFCpack.League;
import PFCpack.Team;
import PFCpack.Trade;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnShowListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import java.util.ArrayList;

class MainActivity$59
  implements DialogInterface.OnShowListener
{
  MainActivity$59(MainActivity paramMainActivity, AlertDialog paramAlertDialog) {}
  
  public void onShow(DialogInterface paramDialogInterface)
  {
    val$dialog.getButton(-1).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new AlertDialog.Builder(this$0);
        paramAnonymousView.setMessage("Are you sure you want to accept this trade? This cannot be undone.").setPositiveButton("Yes, Accept", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            if (this$0.currentTrade != null)
            {
              this$0.currentTrade.completeTrade();
              this$0.simLeague.tradeLog.add(this$0.currentTrade);
            }
            this$0.currentTrade = null;
            this$0.examineTeam(this$0.userTeam.name);
            this$0.currTab = 1;
            MainActivity.access$000(this$0);
            MainActivity.access$100(this$0);
            val$dialog.dismiss();
          }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
        });
        paramAnonymousView.create().show();
      }
    });
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MainActivity.59
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */