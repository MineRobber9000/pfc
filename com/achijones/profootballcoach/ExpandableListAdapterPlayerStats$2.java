package com.achijones.profootballcoach;

import android.view.View;
import android.view.View.OnClickListener;
import java.util.List;

class ExpandableListAdapterPlayerStats$2
  implements View.OnClickListener
{
  ExpandableListAdapterPlayerStats$2(ExpandableListAdapterPlayerStats paramExpandableListAdapterPlayerStats, int paramInt) {}
  
  public void onClick(View paramView)
  {
    ExpandableListAdapterPlayerStats.access$100(this$0).addTradePlayer((String)ExpandableListAdapterPlayerStats.access$000(this$0).get(val$groupPosition));
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.ExpandableListAdapterPlayerStats.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */