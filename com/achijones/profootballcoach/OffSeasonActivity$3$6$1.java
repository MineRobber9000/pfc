package com.achijones.profootballcoach;

import PFCpack.League;
import android.app.ProgressDialog;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;

class OffSeasonActivity$3$6$1
  implements Runnable
{
  OffSeasonActivity$3$6$1(OffSeasonActivity.3.6 param6, ProgressDialog paramProgressDialog) {}
  
  public void run()
  {
    this$2.this$1.this$0.simLeague.freeAgency();
    this$2.this$1.this$0.runOnUiThread(new Runnable()
    {
      public void run()
      {
        val$dialogLoading.dismiss();
        OffSeasonActivity.access$602(this$2.this$1.this$0, 2);
        this$2.this$1.this$0.simLeague.prepareForDraft();
        OffSeasonActivity.access$900(this$2.this$1.this$0).setSelection(0);
        OffSeasonActivity.access$100(this$2.this$1.this$0, "All Players");
        OffSeasonActivity.access$1000(this$2.this$1.this$0);
        OffSeasonActivity.access$202(this$2.this$1.this$0, new OffSeasonActivity.ExpandableListAdapterDraft(this$2.this$1.this$0, this$2.this$1.this$0, OffSeasonActivity.access$300(this$2.this$1.this$0), OffSeasonActivity.access$400(this$2.this$1.this$0), true));
        OffSeasonActivity.access$500(this$2.this$1.this$0).setAdapter(OffSeasonActivity.access$200(this$2.this$1.this$0));
        String str = "Draft: Round " + this$2.this$1.this$0.draftRound + ", Pick " + this$2.this$1.this$0.draftPickNum;
        OffSeasonActivity.access$1100(this$2.this$1.this$0).setText(str);
        OffSeasonActivity.access$800(this$2.this$1.this$0).setText("Draft Results");
        this$2.this$1.this$0.isDraft = true;
        OffSeasonActivity.access$700(this$2.this$1.this$0, false);
      }
    });
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.OffSeasonActivity.3.6.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */