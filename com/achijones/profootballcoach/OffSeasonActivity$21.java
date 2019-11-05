package com.achijones.profootballcoach;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.Spinner;

class OffSeasonActivity$21
  implements DialogInterface.OnClickListener
{
  OffSeasonActivity$21(OffSeasonActivity paramOffSeasonActivity) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    OffSeasonActivity.access$002(this$0, OffSeasonActivity.access$900(this$0).getItemAtPosition(OffSeasonActivity.access$900(this$0).getSelectedItemPosition()).toString());
    if (OffSeasonActivity.access$900(this$0).getSelectedItemPosition() == 0) {
      OffSeasonActivity.access$100(this$0, OffSeasonActivity.access$000(this$0));
    }
    for (;;)
    {
      OffSeasonActivity.access$202(this$0, new OffSeasonActivity.ExpandableListAdapterDraft(this$0, this$0, OffSeasonActivity.access$300(this$0), OffSeasonActivity.access$400(this$0), true));
      OffSeasonActivity.access$500(this$0).setAdapter(OffSeasonActivity.access$200(this$0));
      OffSeasonActivity.access$200(this$0).notifyDataSetChanged();
      return;
      OffSeasonActivity.access$100(this$0, OffSeasonActivity.access$000(this$0).substring(0, 2).trim());
    }
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.OffSeasonActivity.21
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */