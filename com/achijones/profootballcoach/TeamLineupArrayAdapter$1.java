package com.achijones.profootballcoach;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import java.util.ArrayList;

class TeamLineupArrayAdapter$1
  implements CompoundButton.OnCheckedChangeListener
{
  TeamLineupArrayAdapter$1(TeamLineupArrayAdapter paramTeamLineupArrayAdapter, int paramInt) {}
  
  public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this$0.playersSelected.add(this$0.players.get(val$position));
      return;
    }
    this$0.playersSelected.remove(this$0.players.get(val$position));
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.TeamLineupArrayAdapter.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */