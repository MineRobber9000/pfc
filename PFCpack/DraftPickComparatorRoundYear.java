package PFCpack;

import java.util.Comparator;

class DraftPickComparatorRoundYear
  implements Comparator<DraftPick>
{
  public int compare(DraftPick paramDraftPick1, DraftPick paramDraftPick2)
  {
    if (paramDraftPick1.getRound() < paramDraftPick2.getRound()) {}
    do
    {
      return -1;
      if (paramDraftPick1.getRound() != paramDraftPick2.getRound()) {
        break;
      }
    } while (paramDraftPick1.getYear() < paramDraftPick2.getYear());
    if (paramDraftPick1.getYear() == paramDraftPick2.getYear()) {
      return 0;
    }
    return 1;
    return 1;
  }
}

/* Location:
 * Qualified Name:     PFCpack.DraftPickComparatorRoundYear
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */