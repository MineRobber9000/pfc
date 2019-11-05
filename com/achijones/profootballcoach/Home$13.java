package com.achijones.profootballcoach;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.widget.Toast;

class Home$13
  implements DialogInterface.OnClickListener
{
  Home$13(Home paramHome, String[] paramArrayOfString) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (!val$fileInfos[paramInt].equals("EMPTY"))
    {
      paramDialogInterface = new Intent(this$0, OnlineActivity.class);
      paramDialogInterface.putExtra("ONLINE_TEAM", val$fileInfos[paramInt]);
      this$0.startActivity(paramDialogInterface);
      return;
    }
    Toast.makeText(this$0, "Team doesn't exist!", 0).show();
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.Home.13
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */