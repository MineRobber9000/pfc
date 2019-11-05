package com.achijones.profootballcoach;

import PFCpack.League;
import PFCpack.Team;
import PFCpack.Trade;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.util.ArrayList;

class MainActivity$59$1$2
  implements DialogInterface.OnClickListener
{
  MainActivity$59$1$2(MainActivity.59.1 param1) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (this$2.this$1.this$0.currentTrade != null)
    {
      this$2.this$1.this$0.currentTrade.completeTrade();
      this$2.this$1.this$0.simLeague.tradeLog.add(this$2.this$1.this$0.currentTrade);
    }
    this$2.this$1.this$0.currentTrade = null;
    this$2.this$1.this$0.examineTeam(this$2.this$1.this$0.userTeam.name);
    this$2.this$1.this$0.currTab = 1;
    MainActivity.access$000(this$2.this$1.this$0);
    MainActivity.access$100(this$2.this$1.this$0);
    this$2.this$1.val$dialog.dismiss();
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MainActivity.59.1.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */