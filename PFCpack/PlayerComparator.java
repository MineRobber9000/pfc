package PFCpack;

import java.util.Comparator;

class PlayerComparator
  implements Comparator<Player>
{
  public int compare(Player paramPlayer1, Player paramPlayer2)
  {
    if ((!paramPlayer1.isInjured()) && (paramPlayer2.isInjured())) {}
    do
    {
      return -1;
      if ((paramPlayer1.isInjured()) && (!paramPlayer2.isInjured())) {
        return 1;
      }
    } while (ratOvr > ratOvr);
    if (ratOvr == ratOvr) {
      return 0;
    }
    return 1;
  }
}

/* Location:
 * Qualified Name:     PFCpack.PlayerComparator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */