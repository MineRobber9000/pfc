package com.achijones.profootballcoach;

import PFCpack.Contract;
import PFCpack.Player;
import PFCpack.Team;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

class OffSeasonActivity$ExpandableListAdapterDraft$2
  implements View.OnClickListener
{
  OffSeasonActivity$ExpandableListAdapterDraft$2(OffSeasonActivity.ExpandableListAdapterDraft paramExpandableListAdapterDraft, Player paramPlayer) {}
  
  public void onClick(View paramView)
  {
    if ((OffSeasonActivity.access$600(this$1.this$0) == 2) && (this$1.this$0.draftRound <= 7))
    {
      this$1.this$0.draftPlayerUserTeam(val$player);
      return;
    }
    if (OffSeasonActivity.access$600(this$1.this$0) == 0)
    {
      if ((this$1.this$0.userTeam.getSalaryCapRoom() >= Contract.getContractFA(val$player).getMoneyPerYear()) || (Contract.getContractFA(val$player).getMoneyPerYear() <= 0.5D))
      {
        this$1.this$0.signTeamFAPlayerUserTeam(val$player);
        return;
      }
      Toast.makeText(this$1.this$0, "Not enough cap room!", 0).show();
      return;
    }
    if ((this$1.this$0.userTeam.getSalaryCapRoom() >= Contract.getContractFA(val$player).getMoneyPerYear()) || (Contract.getContractFA(val$player).getMoneyPerYear() <= 0.5D))
    {
      this$1.this$0.signFAPlayerUserTeam(val$player);
      return;
    }
    Toast.makeText(this$1.this$0, "Not enough cap room!", 0).show();
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.OffSeasonActivity.ExpandableListAdapterDraft.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */