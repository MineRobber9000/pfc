package PFCpack;

import java.util.Comparator;

class TeamCompLeastWins
  implements Comparator<Team>
{
  public int compare(Team paramTeam1, Team paramTeam2)
  {
    if (wins < wins) {
      return -1;
    }
    if (wins == wins) {
      return 0;
    }
    return 1;
  }
}

/* Location:
 * Qualified Name:     PFCpack.TeamCompLeastWins
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */