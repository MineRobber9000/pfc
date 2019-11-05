package com.achijones.profootballcoach;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

class MainActivity$69
  implements DialogInterface.OnClickListener
{
  MainActivity$69(MainActivity paramMainActivity) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    MainActivity.access$100(this$0);
    paramDialogInterface = new Intent(this$0, Home.class);
    this$0.startActivity(paramDialogInterface);
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MainActivity.69
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */