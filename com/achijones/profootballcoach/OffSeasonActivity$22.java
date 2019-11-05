package com.achijones.profootballcoach;

import android.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import java.util.ArrayList;

class OffSeasonActivity$22
  implements View.OnClickListener
{
  OffSeasonActivity$22(OffSeasonActivity paramOffSeasonActivity, AlertDialog paramAlertDialog) {}
  
  public void onClick(View paramView)
  {
    int i = 0;
    while (i < OffSeasonActivity.access$300(this$0).size())
    {
      OffSeasonActivity.access$500(this$0).expandGroup(i, false);
      i += 1;
    }
    val$dialog.dismiss();
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.OffSeasonActivity.22
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */