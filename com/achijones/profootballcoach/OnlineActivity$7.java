package com.achijones.profootballcoach;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

class OnlineActivity$7
  implements DialogInterface.OnClickListener
{
  OnlineActivity$7(OnlineActivity paramOnlineActivity) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    paramDialogInterface = new Intent(this$0, Home.class);
    this$0.startActivity(paramDialogInterface);
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.OnlineActivity.7
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */