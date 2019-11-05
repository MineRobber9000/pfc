package com.achijones.profootballcoach;

import PFCpack.Game;
import PFCpack.Team;
import android.view.View;
import android.view.View.OnClickListener;

class GameScheduleListArrayAdapter$2
  implements View.OnClickListener
{
  GameScheduleListArrayAdapter$2(GameScheduleListArrayAdapter paramGameScheduleListArrayAdapter, int paramInt) {}
  
  public void onClick(View paramView)
  {
    if (access$000this$0)[val$position].awayTeam == GameScheduleListArrayAdapter.access$200(this$0))
    {
      GameScheduleListArrayAdapter.access$100(this$0).examineTeam(access$000this$0)[val$position].homeTeam.name);
      return;
    }
    GameScheduleListArrayAdapter.access$100(this$0).examineTeam(access$000this$0)[val$position].awayTeam.name);
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.GameScheduleListArrayAdapter.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */