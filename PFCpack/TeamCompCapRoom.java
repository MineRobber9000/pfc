package PFCpack;

import java.util.Comparator;

class TeamCompCapRoom
  implements Comparator<Team>
{
  public int compare(Team paramTeam1, Team paramTeam2)
  {
    if (paramTeam1.getSalaryCapRoom() > paramTeam2.getSalaryCapRoom()) {
      return -1;
    }
    if (paramTeam1.getSalaryCapRoom() == paramTeam2.getSalaryCapRoom()) {
      return 0;
    }
    return 1;
  }
}

/* Location:
 * Qualified Name:     PFCpack.TeamCompCapRoom
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */