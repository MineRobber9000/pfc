package com.achijones.profootballcoach;

import android.view.View;
import android.view.View.OnClickListener;

class OffSeasonActivity$2
  implements View.OnClickListener
{
  OffSeasonActivity$2(OffSeasonActivity paramOffSeasonActivity) {}
  
  public void onClick(View paramView)
  {
    if (OffSeasonActivity.access$600(this$0) == 2) {
      this$0.showDraftSummaryDialog(false);
    }
    do
    {
      return;
      if (OffSeasonActivity.access$600(this$0) == 0)
      {
        this$0.showFreeAgencyResultsDialog(this$0.teamFAResultsList, "Team Free Agency");
        return;
      }
    } while (OffSeasonActivity.access$600(this$0) != 1);
    this$0.showFreeAgencyResultsDialog(this$0.leagueFAResultsList, "League Free Agency");
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.OffSeasonActivity.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */