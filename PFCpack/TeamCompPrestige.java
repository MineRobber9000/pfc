package PFCpack;

import java.util.Comparator;

class TeamCompPrestige
  implements Comparator<Team>
{
  public int compare(Team paramTeam1, Team paramTeam2)
  {
    if (teamPrestige > teamPrestige) {
      return -1;
    }
    if (teamPrestige == teamPrestige) {
      return 0;
    }
    return 1;
  }
}

/* Location:
 * Qualified Name:     PFCpack.TeamCompPrestige
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */