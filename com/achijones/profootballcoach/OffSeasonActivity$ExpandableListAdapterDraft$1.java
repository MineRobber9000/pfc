package com.achijones.profootballcoach;

import PFCpack.Player;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import java.util.ArrayList;

class OffSeasonActivity$ExpandableListAdapterDraft$1
  implements View.OnClickListener
{
  OffSeasonActivity$ExpandableListAdapterDraft$1(OffSeasonActivity.ExpandableListAdapterDraft paramExpandableListAdapterDraft, Player paramPlayer) {}
  
  public void onClick(View paramView)
  {
    paramView = new AlertDialog.Builder(this$1.this$0);
    Object localObject = val$player.getDetailStatsOffseason();
    ((ArrayList)localObject).add(0, "[B]" + val$player.getAgeOvrPot_Str());
    localObject = (String[])((ArrayList)localObject).toArray(new String[((ArrayList)localObject).size()]);
    paramView.setAdapter(new PlayerStatsListArrayAdapter(this$1.this$0, (String[])localObject), null).setTitle(val$player.getPosition() + " " + val$player.getName()).setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    paramView.create().show();
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.OffSeasonActivity.ExpandableListAdapterDraft.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */