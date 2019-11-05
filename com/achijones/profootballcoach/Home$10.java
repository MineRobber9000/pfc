package com.achijones.profootballcoach;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;
import java.net.URL;

class Home$10
  implements DialogInterface.OnClickListener
{
  Home$10(Home paramHome, EditText paramEditText) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    try
    {
      new URL(val$input.getText().toString()).openConnection();
      new Home.GetRosterFileTask(this$0).execute(new String[] { val$input.getText().toString() });
      return;
    }
    catch (Exception localException)
    {
      Toast.makeText(this$0, "Error! Bad URL or unable to read file.", 0).show();
      paramDialogInterface.cancel();
    }
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.Home.10
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */