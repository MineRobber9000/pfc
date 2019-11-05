package com.achijones.profootballcoach;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.widget.Toast;

class Home$4
  implements DialogInterface.OnClickListener
{
  Home$4(Home paramHome, String[] paramArrayOfString) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (!val$fileInfos[paramInt].equals("EMPTY"))
    {
      paramDialogInterface = new Intent(this$0, MainActivity.class);
      paramDialogInterface.putExtra("SAVE_FILE", "LOAD,saveFile" + paramInt + ".cfb,saveFile" + paramInt + "_teams.cfb,saveFile" + paramInt + "_schedules.cfb,saveFile" + paramInt + "_teamhist.cfb");
      this$0.startActivity(paramDialogInterface);
      return;
    }
    Toast.makeText(this$0, "Cannot load empty file!", 0).show();
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.Home.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */