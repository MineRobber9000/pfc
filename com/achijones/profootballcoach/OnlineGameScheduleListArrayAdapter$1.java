package com.achijones.profootballcoach;

import PFCpack.Game;
import android.view.View;
import android.view.View.OnClickListener;

class OnlineGameScheduleListArrayAdapter$1
  implements View.OnClickListener
{
  OnlineGameScheduleListArrayAdapter$1(OnlineGameScheduleListArrayAdapter paramOnlineGameScheduleListArrayAdapter, int paramInt) {}
  
  public void onClick(View paramView)
  {
    if (access$000this$0)[val$position].hasPlayed)
    {
      OnlineGameScheduleListArrayAdapter.access$100(this$0).showGameDialog(OnlineGameScheduleListArrayAdapter.access$000(this$0)[val$position]);
      return;
    }
    OnlineGameScheduleListArrayAdapter.access$100(this$0).showGameScoutDialog(OnlineGameScheduleListArrayAdapter.access$000(this$0)[val$position]);
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.OnlineGameScheduleListArrayAdapter.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */