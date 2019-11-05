package com.achijones.profootballcoach;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

class Home$9
  implements DialogInterface.OnClickListener
{
  Home$9(Home paramHome, int paramInt) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    paramDialogInterface = new Intent(this$0, MainActivity.class);
    paramDialogInterface.putExtra("SAVE_FILE", "NEW,saveFile" + val$saveFileNumber + ".cfb,saveFile" + val$saveFileNumber + "_teams.cfb,saveFile" + val$saveFileNumber + "_schedules.cfb,saveFile" + val$saveFileNumber + "_teamhist.cfb");
    this$0.startActivity(paramDialogInterface);
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.Home.9
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */