package PFCpack;

import java.util.Comparator;

class TeamCompTODiff
  implements Comparator<Team>
{
  public int compare(Team paramTeam1, Team paramTeam2)
  {
    if (teamTODiff > teamTODiff) {
      return -1;
    }
    if (teamTODiff == teamTODiff) {
      return 0;
    }
    return 1;
  }
}

/* Location:
 * Qualified Name:     PFCpack.TeamCompTODiff
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */