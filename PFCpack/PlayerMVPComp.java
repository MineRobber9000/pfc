package PFCpack;

import java.util.Comparator;

class PlayerMVPComp
  implements Comparator<Player>
{
  public int compare(Player paramPlayer1, Player paramPlayer2)
  {
    if (paramPlayer1.getMVPScore() > paramPlayer2.getMVPScore()) {
      return -1;
    }
    if (paramPlayer1.getMVPScore() == paramPlayer2.getMVPScore()) {
      return 0;
    }
    return 1;
  }
}

/* Location:
 * Qualified Name:     PFCpack.PlayerMVPComp
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */