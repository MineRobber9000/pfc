package PFCpack;

import java.util.Comparator;

class PlayerPositionComparator
  implements Comparator<Player>
{
  public int compare(Player paramPlayer1, Player paramPlayer2)
  {
    int i = Player.getPosNumber(position);
    int j = Player.getPosNumber(position);
    if (i < j) {
      return -1;
    }
    if (i == j) {
      return 0;
    }
    return 1;
  }
}

/* Location:
 * Qualified Name:     PFCpack.PlayerPositionComparator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */