package com.achijones.profootballcoach;

import PFCpack.League;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;

class OffSeasonActivity$3$6
  implements DialogInterface.OnClickListener
{
  OffSeasonActivity$3$6(OffSeasonActivity.3 param3) {}
  
  public void onClick(final DialogInterface paramDialogInterface, int paramInt)
  {
    paramDialogInterface = ProgressDialog.show(this$1.this$0, "", "Simulating Free Agency. Please wait...", true);
    paramDialogInterface.setCancelable(false);
    new Thread(new Runnable()
    {
      public void run()
      {
        this$1.this$0.simLeague.freeAgency();
        this$1.this$0.runOnUiThread(new Runnable()
        {
          public void run()
          {
            val$dialogLoading.dismiss();
            OffSeasonActivity.access$602(this$1.this$0, 2);
            this$1.this$0.simLeague.prepareForDraft();
            OffSeasonActivity.access$900(this$1.this$0).setSelection(0);
            OffSeasonActivity.access$100(this$1.this$0, "All Players");
            OffSeasonActivity.access$1000(this$1.this$0);
            OffSeasonActivity.access$202(this$1.this$0, new OffSeasonActivity.ExpandableListAdapterDraft(this$1.this$0, this$1.this$0, OffSeasonActivity.access$300(this$1.this$0), OffSeasonActivity.access$400(this$1.this$0), true));
            OffSeasonActivity.access$500(this$1.this$0).setAdapter(OffSeasonActivity.access$200(this$1.this$0));
            String str = "Draft: Round " + this$1.this$0.draftRound + ", Pick " + this$1.this$0.draftPickNum;
            OffSeasonActivity.access$1100(this$1.this$0).setText(str);
            OffSeasonActivity.access$800(this$1.this$0).setText("Draft Results");
            this$1.this$0.isDraft = true;
            OffSeasonActivity.access$700(this$1.this$0, false);
          }
        });
      }
    }).start();
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.OffSeasonActivity.3.6
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */