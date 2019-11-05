package com.achijones.profootballcoach;

import PFCpack.League;
import PFCpack.Team;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;

class OffSeasonActivity$3
  implements View.OnClickListener
{
  OffSeasonActivity$3(OffSeasonActivity paramOffSeasonActivity) {}
  
  public void onClick(View paramView)
  {
    if (OffSeasonActivity.access$600(this$0) == 2)
    {
      paramView = new AlertDialog.Builder(this$0);
      paramView.setMessage("Are you sure you want to finish the draft? The rest of your picks will be selected for you:\n\n" + this$0.getUserDraftPicksStr()).setPositiveButton("Yes, simulate the draft", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          OffSeasonActivity.access$700(this$0, true);
        }
      }).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
      });
      paramView.create().show();
    }
    do
    {
      return;
      if (OffSeasonActivity.access$600(this$0) == 0)
      {
        paramView = new AlertDialog.Builder(this$0);
        paramView.setMessage("Are you sure you are done resigning your team free agents?").setPositiveButton("Yes, go to league FA", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            this$0.simLeague.transferTeamFAs();
            OffSeasonActivity.access$602(this$0, 1);
            OffSeasonActivity.access$800(this$0).setText("League FA Results");
            this$0.isDraft = false;
            this$0.simLeague.sortFAs();
            this$0.freeAgencyPlayers = this$0.transferListsFA(this$0.simLeague.freeAgents);
            OffSeasonActivity.access$900(this$0).setSelection(0);
            OffSeasonActivity.access$100(this$0, "All Players");
            OffSeasonActivity.access$1000(this$0);
            OffSeasonActivity.access$202(this$0, new OffSeasonActivity.ExpandableListAdapterDraft(this$0, this$0, OffSeasonActivity.access$300(this$0), OffSeasonActivity.access$400(this$0), true));
            OffSeasonActivity.access$500(this$0).setAdapter(OffSeasonActivity.access$200(this$0));
            OffSeasonActivity.access$200(this$0).notifyDataSetChanged();
            paramAnonymousDialogInterface = "Day " + this$0.dayFreeAgency + ", Free Agency: $" + this$0.userTeam.getSalaryCapRoom() + " mil room";
            OffSeasonActivity.access$1100(this$0).setText(paramAnonymousDialogInterface);
            this$0.showIntroLeagueFADialog();
          }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
        });
        paramView.create().show();
        return;
      }
    } while (OffSeasonActivity.access$600(this$0) != 1);
    paramView = new StringBuilder();
    paramView.append("Are you sure you are done signing free agents? Once you are done you cannot go back.\n\n");
    paramView.append("In the upcoming draft you have the following picks:\n");
    paramView.append(this$0.getUserDraftPicksStr());
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this$0);
    localBuilder.setMessage(paramView.toString()).setPositiveButton("Yes, go to Draft", new DialogInterface.OnClickListener()
    {
      public void onClick(final DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = ProgressDialog.show(this$0, "", "Simulating Free Agency. Please wait...", true);
        paramAnonymousDialogInterface.setCancelable(false);
        new Thread(new Runnable()
        {
          public void run()
          {
            this$0.simLeague.freeAgency();
            this$0.runOnUiThread(new Runnable()
            {
              public void run()
              {
                val$dialogLoading.dismiss();
                OffSeasonActivity.access$602(this$0, 2);
                this$0.simLeague.prepareForDraft();
                OffSeasonActivity.access$900(this$0).setSelection(0);
                OffSeasonActivity.access$100(this$0, "All Players");
                OffSeasonActivity.access$1000(this$0);
                OffSeasonActivity.access$202(this$0, new OffSeasonActivity.ExpandableListAdapterDraft(this$0, this$0, OffSeasonActivity.access$300(this$0), OffSeasonActivity.access$400(this$0), true));
                OffSeasonActivity.access$500(this$0).setAdapter(OffSeasonActivity.access$200(this$0));
                String str = "Draft: Round " + this$0.draftRound + ", Pick " + this$0.draftPickNum;
                OffSeasonActivity.access$1100(this$0).setText(str);
                OffSeasonActivity.access$800(this$0).setText("Draft Results");
                this$0.isDraft = true;
                OffSeasonActivity.access$700(this$0, false);
              }
            });
          }
        }).start();
      }
    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    localBuilder.create().show();
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.OffSeasonActivity.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */