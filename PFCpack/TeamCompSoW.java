package PFCpack;

import java.util.Comparator;

class TeamCompSoW
  implements Comparator<Team>
{
  public int compare(Team paramTeam1, Team paramTeam2)
  {
    if (teamStrengthOfWins > teamStrengthOfWins) {
      return -1;
    }
    if (teamStrengthOfWins == teamStrengthOfWins) {
      return 0;
    }
    return 1;
  }
}

/* Location:
 * Qualified Name:     PFCpack.TeamCompSoW
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */