package PFCpack;

import java.util.Comparator;

class TradePieceComparator
  implements Comparator<TradePiece>
{
  public int compare(TradePiece paramTradePiece1, TradePiece paramTradePiece2)
  {
    if (((paramTradePiece1 instanceof Player)) && (!(paramTradePiece2 instanceof Player))) {}
    do
    {
      do
      {
        do
        {
          return -1;
          if ((!(paramTradePiece1 instanceof Player)) && ((paramTradePiece2 instanceof Player))) {
            return 1;
          }
          if ((!(paramTradePiece1 instanceof Player)) || (!(paramTradePiece2 instanceof Player))) {
            break;
          }
          paramTradePiece1 = (Player)paramTradePiece1;
          paramTradePiece2 = (Player)paramTradePiece2;
        } while (ratOvr > ratOvr);
        if (ratOvr == ratOvr) {
          return 0;
        }
        return 1;
        if ((!(paramTradePiece1 instanceof DraftPick)) || (!(paramTradePiece2 instanceof DraftPick))) {
          break;
        }
        paramTradePiece1 = (DraftPick)paramTradePiece1;
        paramTradePiece2 = (DraftPick)paramTradePiece2;
      } while (paramTradePiece1.getRound() < paramTradePiece2.getRound());
      if (paramTradePiece1.getRound() != paramTradePiece2.getRound()) {
        break;
      }
    } while (paramTradePiece1.getYear() < paramTradePiece2.getYear());
    if (paramTradePiece1.getYear() == paramTradePiece2.getYear()) {
      return 0;
    }
    return 1;
    return 1;
    return 0;
  }
}

/* Location:
 * Qualified Name:     PFCpack.TradePieceComparator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */