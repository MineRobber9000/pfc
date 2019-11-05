package PFCpack;

import java.util.Comparator;

class DraftPickComparatorYearRound
  implements Comparator<DraftPick>
{
  public int compare(DraftPick paramDraftPick1, DraftPick paramDraftPick2)
  {
    if (paramDraftPick1.getYear() < paramDraftPick2.getYear()) {}
    do
    {
      return -1;
      if (paramDraftPick1.getYear() != paramDraftPick2.getYear()) {
        break;
      }
    } while (paramDraftPick1.getRound() < paramDraftPick2.getRound());
    if (paramDraftPick1.getRound() == paramDraftPick2.getRound()) {
      return 0;
    }
    return 1;
    return 1;
  }
}

/* Location:
 * Qualified Name:     PFCpack.DraftPickComparatorYearRound
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */