package PFCpack;

import java.util.Comparator;

class TeamCompPPG
  implements Comparator<Team>
{
  public int compare(Team paramTeam1, Team paramTeam2)
  {
    if (teamPoints / paramTeam1.numGames() > teamPoints / paramTeam2.numGames()) {
      return -1;
    }
    if (teamPoints / paramTeam1.numGames() == teamPoints / paramTeam2.numGames()) {
      return 0;
    }
    return 1;
  }
}

/* Location:
 * Qualified Name:     PFCpack.TeamCompPPG
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */