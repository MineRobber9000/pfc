package com.achijones.profootballcoach;

import PFCpack.Player;
import java.util.Comparator;

class PlayerComparatorNoInjury
  implements Comparator<Player>
{
  public int compare(Player paramPlayer1, Player paramPlayer2)
  {
    if (paramPlayer1.getRatOvr() > paramPlayer2.getRatOvr()) {
      return -1;
    }
    if (paramPlayer1.getRatOvr() == paramPlayer2.getRatOvr()) {
      return 0;
    }
    return 1;
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.PlayerComparatorNoInjury
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */