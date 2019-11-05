package com.achijones.profootballcoach;

import PFCpack.Game;
import PFCpack.League;
import PFCpack.Team;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;

class MainActivity$4
  implements View.OnClickListener
{
  MainActivity$4(MainActivity paramMainActivity, Button paramButton1, Button paramButton2) {}
  
  public void onClick(View paramView)
  {
    if (this$0.simLeague.currentWeek == 20)
    {
      this$0.advanceOffSeasonDialog();
      return;
    }
    val$simGameButton.setEnabled(false);
    int i = this$0.userTeam.gameWLSchedule.size();
    this$0.simLeague.playWeek();
    if (this$0.simLeague.currentWeek == 7)
    {
      paramView = new AlertDialog.Builder(this$0);
      paramView.setMessage("This week is the last week for trades! Once the next week is played, trades will not be allowed.").setPositiveButton("Ok", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.dismiss();
        }
      });
      paramView.create().show();
      if (this$0.simLeague.currentWeek == 16)
      {
        MainActivity.access$500(this$0);
        if (!this$0.simLeague.playoffsNA.contains(this$0.userTeam)) {
          break label585;
        }
        if ((this$0.simLeague.playoffsNA.get(0) != this$0.userTeam) && (this$0.simLeague.playoffsNA.get(1) != this$0.userTeam)) {
          break label443;
        }
        if (this$0.showToasts) {
          Toast.makeText(this$0, this$0.userTeam.abbr + " made the playoffs!\nThey receive a BYE week for Round 1.", 0).show();
        }
      }
      label251:
      if (this$0.userTeam.gameWLSchedule.size() > i)
      {
        if (this$0.showToasts) {
          Toast.makeText(this$0, this$0.userTeam.weekSummaryStr(), 0).show();
        }
        if (this$0.showInjuryReport) {
          this$0.showInjuryReportDialog();
        }
      }
      MainActivity.access$200(this$0);
      MainActivity.access$600(this$0);
      if (this$0.simLeague.currentWeek >= 16) {
        break label902;
      }
      val$simGameButton.setText("Play Week " + (this$0.simLeague.currentWeek + 1));
    }
    for (;;)
    {
      val$simGameButton.setEnabled(true);
      return;
      if (this$0.simLeague.currentWeek < 8) {
        break;
      }
      if (this$0.currTab == 3)
      {
        this$0.currTab = 2;
        MainActivity.access$400(this$0);
      }
      val$teamTradeButton.setEnabled(false);
      break;
      label443:
      if (this$0.userTeam.gameSchedule.get(16)).homeTeam == this$0.userTeam) {}
      for (paramView = this$0.userTeam.gameSchedule.get(16)).awayTeam;; paramView = this$0.userTeam.gameSchedule.get(16)).homeTeam)
      {
        if (!this$0.showToasts) {
          break label583;
        }
        Toast.makeText(this$0, this$0.userTeam.abbr + " made the playoffs!\nThey play " + abbr + " in Round 1.", 0).show();
        break;
      }
      label583:
      break label251;
      label585:
      if (this$0.simLeague.playoffsAM.contains(this$0.userTeam))
      {
        if ((this$0.simLeague.playoffsAM.get(0) == this$0.userTeam) || (this$0.simLeague.playoffsAM.get(1) == this$0.userTeam))
        {
          if (!this$0.showToasts) {
            break label251;
          }
          Toast.makeText(this$0, this$0.userTeam.abbr + " made the playoffs!\nThey receive a BYE week for Round 1.", 0).show();
          break label251;
        }
        if (this$0.userTeam.gameSchedule.get(16)).homeTeam == this$0.userTeam) {}
        for (paramView = this$0.userTeam.gameSchedule.get(16)).awayTeam;; paramView = this$0.userTeam.gameSchedule.get(16)).homeTeam)
        {
          if (!this$0.showToasts) {
            break label848;
          }
          Toast.makeText(this$0, this$0.userTeam.abbr + " made the playoffs!\nThey play " + abbr + " in Round 1.", 0).show();
          break;
        }
        label848:
        break label251;
      }
      if (!this$0.showToasts) {
        break label251;
      }
      Toast.makeText(this$0, this$0.userTeam.abbr + " did not make the playoffs.", 0).show();
      break label251;
      label902:
      if (this$0.simLeague.currentWeek == 16)
      {
        val$simGameButton.setText("Playoffs Round 1");
        this$0.examineTeam(this$0.currentTeam.name);
      }
      else if (this$0.simLeague.currentWeek == 17)
      {
        val$simGameButton.setText("Playoffs Round 2");
        this$0.examineTeam(this$0.currentTeam.name);
      }
      else if (this$0.simLeague.currentWeek == 18)
      {
        val$simGameButton.setText("Play Conf Championships");
      }
      else if (this$0.simLeague.currentWeek == 19)
      {
        val$simGameButton.setText("Play Champions Bowl");
      }
      else
      {
        val$simGameButton.setText("Advance to Offseason");
        this$0.simLeague.curePlayers();
        this$0.simLeague.checkLeagueRecords();
        this$0.showSeasonSummaryDialog();
        MainActivity.access$100(this$0);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MainActivity.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */