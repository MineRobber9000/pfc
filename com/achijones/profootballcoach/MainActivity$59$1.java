package com.achijones.profootballcoach;

import PFCpack.League;
import PFCpack.Team;
import PFCpack.Trade;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import java.util.ArrayList;

class MainActivity$59$1
  implements View.OnClickListener
{
  MainActivity$59$1(MainActivity.59 param59) {}
  
  public void onClick(View paramView)
  {
    paramView = new AlertDialog.Builder(this$1.this$0);
    paramView.setMessage("Are you sure you want to accept this trade? This cannot be undone.").setPositiveButton("Yes, Accept", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        if (this$1.this$0.currentTrade != null)
        {
          this$1.this$0.currentTrade.completeTrade();
          this$1.this$0.simLeague.tradeLog.add(this$1.this$0.currentTrade);
        }
        this$1.this$0.currentTrade = null;
        this$1.this$0.examineTeam(this$1.this$0.userTeam.name);
        this$1.this$0.currTab = 1;
        MainActivity.access$000(this$1.this$0);
        MainActivity.access$100(this$1.this$0);
        this$1.val$dialog.dismiss();
      }
    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    paramView.create().show();
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MainActivity.59.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */