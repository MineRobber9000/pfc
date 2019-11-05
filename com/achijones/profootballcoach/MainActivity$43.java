package com.achijones.profootballcoach;

import PFCpack.Team;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

class MainActivity$43
  implements View.OnClickListener
{
  MainActivity$43(MainActivity paramMainActivity, Spinner paramSpinner, TeamLineupArrayAdapter paramTeamLineupArrayAdapter, int[] paramArrayOfInt, ArrayList paramArrayList, TextView paramTextView, String[] paramArrayOfString) {}
  
  public void onClick(View paramView)
  {
    int i = val$teamLineupPositionSpinner.getSelectedItemPosition();
    if (val$teamLineupAdapter.playersSelected.size() == val$teamLineupAdapter.playersRequired)
    {
      this$0.userTeam.setStarters(val$teamLineupAdapter.playersSelected, i);
      MainActivity.access$1000(this$0, i, val$teamLineupAdapter, val$positionNumberRequired, val$positionPlayers, val$textLineupPositionDescription);
      Toast.makeText(this$0, "Saved lineup for " + val$positionSelection[i] + "!", 0).show();
      return;
    }
    Toast.makeText(this$0, val$teamLineupAdapter.playersSelected.size() + " players selected.\nNot the correct number of starters (" + val$teamLineupAdapter.playersRequired + ")", 0).show();
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MainActivity.43
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */