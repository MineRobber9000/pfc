package com.achijones.profootballcoach;

import PFCpack.Team;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import java.util.ArrayList;
import java.util.Map;

class OffSeasonActivity$4
  implements View.OnClickListener
{
  OffSeasonActivity$4(OffSeasonActivity paramOffSeasonActivity) {}
  
  public void onClick(View paramView)
  {
    paramView = new AlertDialog.Builder(this$0);
    paramView.setTitle("Current Roster");
    OffSeasonActivity.access$1202(this$0, new int[8]);
    ArrayList localArrayList = new ArrayList();
    this$0.userTeam.sortPlayers();
    localArrayList.add(null);
    localArrayList.addAll(this$0.userTeam.teamQBs);
    OffSeasonActivity.access$1200(this$0)[0] = (this$0.userTeam.teamQBs.size() + 1);
    localArrayList.add(null);
    localArrayList.addAll(this$0.userTeam.teamRBs);
    OffSeasonActivity.access$1200(this$0)[1] = (OffSeasonActivity.access$1200(this$0)[0] + this$0.userTeam.teamRBs.size() + 1);
    localArrayList.add(null);
    localArrayList.addAll(this$0.userTeam.teamWRs);
    OffSeasonActivity.access$1200(this$0)[2] = (OffSeasonActivity.access$1200(this$0)[1] + this$0.userTeam.teamWRs.size() + 1);
    localArrayList.add(null);
    localArrayList.addAll(this$0.userTeam.teamOLs);
    OffSeasonActivity.access$1200(this$0)[3] = (OffSeasonActivity.access$1200(this$0)[2] + this$0.userTeam.teamOLs.size() + 1);
    localArrayList.add(null);
    localArrayList.addAll(this$0.userTeam.teamKs);
    OffSeasonActivity.access$1200(this$0)[4] = (OffSeasonActivity.access$1200(this$0)[3] + this$0.userTeam.teamKs.size() + 1);
    localArrayList.add(null);
    localArrayList.addAll(this$0.userTeam.teamSs);
    OffSeasonActivity.access$1200(this$0)[5] = (OffSeasonActivity.access$1200(this$0)[4] + this$0.userTeam.teamSs.size() + 1);
    localArrayList.add(null);
    localArrayList.addAll(this$0.userTeam.teamCBs);
    OffSeasonActivity.access$1200(this$0)[6] = (OffSeasonActivity.access$1200(this$0)[5] + this$0.userTeam.teamCBs.size() + 1);
    localArrayList.add(null);
    localArrayList.addAll(this$0.userTeam.teamDLs);
    OffSeasonActivity.access$1200(this$0)[7] = (OffSeasonActivity.access$1200(this$0)[6] + this$0.userTeam.teamDLs.size() + 1);
    localArrayList.add(null);
    localArrayList.addAll(this$0.userTeam.teamLBs);
    Map localMap = OffSeasonActivity.access$1300(this$0, localArrayList);
    ExpandableListView localExpandableListView = new ExpandableListView(this$0);
    localExpandableListView.setAdapter(new OffSeasonActivity.ExpandableListAdapterDraft(this$0, this$0, localArrayList, localMap, false));
    paramView.setView(localExpandableListView);
    paramView.setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    });
    paramView.create().show();
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.OffSeasonActivity.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */