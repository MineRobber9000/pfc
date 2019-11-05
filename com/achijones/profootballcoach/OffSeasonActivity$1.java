package com.achijones.profootballcoach;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ExpandableListView;

class OffSeasonActivity$1
  implements AdapterView.OnItemSelectedListener
{
  OffSeasonActivity$1(OffSeasonActivity paramOffSeasonActivity) {}
  
  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    OffSeasonActivity.access$002(this$0, paramAdapterView.getItemAtPosition(paramInt).toString());
    if (paramInt == 0) {
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
  
  public void onNothingSelected(AdapterView<?> paramAdapterView) {}
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.OffSeasonActivity.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */