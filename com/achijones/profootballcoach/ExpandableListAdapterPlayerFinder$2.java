package com.achijones.profootballcoach;

import PFCpack.Player;
import PFCpack.Team;
import android.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;

class ExpandableListAdapterPlayerFinder$2
  implements View.OnClickListener
{
  ExpandableListAdapterPlayerFinder$2(ExpandableListAdapterPlayerFinder paramExpandableListAdapterPlayerFinder, Player paramPlayer) {}
  
  public void onClick(View paramView)
  {
    ExpandableListAdapterPlayerFinder.access$000(this$0).examineTeamRoster(val$player.getTeam().name);
    ExpandableListAdapterPlayerFinder.access$100(this$0).dismiss();
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.ExpandableListAdapterPlayerFinder.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */