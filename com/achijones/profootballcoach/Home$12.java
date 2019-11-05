package com.achijones.profootballcoach;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;

class Home$12
  implements DialogInterface.OnClickListener
{
  Home$12(Home paramHome) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    paramDialogInterface = new Intent();
    paramDialogInterface.setAction("android.intent.action.VIEW");
    paramDialogInterface.addCategory("android.intent.category.BROWSABLE");
    paramDialogInterface.setData(Uri.parse("https://m.reddit.com/r/FootballCoach/wiki/rosters"));
    this$0.startActivity(paramDialogInterface);
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.Home.12
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */