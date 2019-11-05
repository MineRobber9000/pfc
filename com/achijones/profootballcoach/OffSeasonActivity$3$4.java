package com.achijones.profootballcoach;

import PFCpack.League;
import PFCpack.Team;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;

class OffSeasonActivity$3$4
  implements DialogInterface.OnClickListener
{
  OffSeasonActivity$3$4(OffSeasonActivity.3 param3) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    this$1.this$0.simLeague.transferTeamFAs();
    OffSeasonActivity.access$602(this$1.this$0, 1);
    OffSeasonActivity.access$800(this$1.this$0).setText("League FA Results");
    this$1.this$0.isDraft = false;
    this$1.this$0.simLeague.sortFAs();
    this$1.this$0.freeAgencyPlayers = this$1.this$0.transferListsFA(this$1.this$0.simLeague.freeAgents);
    OffSeasonActivity.access$900(this$1.this$0).setSelection(0);
    OffSeasonActivity.access$100(this$1.this$0, "All Players");
    OffSeasonActivity.access$1000(this$1.this$0);
    OffSeasonActivity.access$202(this$1.this$0, new OffSeasonActivity.ExpandableListAdapterDraft(this$1.this$0, this$1.this$0, OffSeasonActivity.access$300(this$1.this$0), OffSeasonActivity.access$400(this$1.this$0), true));
    OffSeasonActivity.access$500(this$1.this$0).setAdapter(OffSeasonActivity.access$200(this$1.this$0));
    OffSeasonActivity.access$200(this$1.this$0).notifyDataSetChanged();
    paramDialogInterface = "Day " + this$1.this$0.dayFreeAgency + ", Free Agency: $" + this$1.this$0.userTeam.getSalaryCapRoom() + " mil room";
    OffSeasonActivity.access$1100(this$1.this$0).setText(paramDialogInterface);
    this$1.this$0.showIntroLeagueFADialog();
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.OffSeasonActivity.3.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */