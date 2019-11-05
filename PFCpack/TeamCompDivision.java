// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

import java.util.Comparator;

class TeamCompDivision implements Comparator<Team>
{
    @Override
    public int compare(final Team team, final Team team2) {
        if (!team.divChampion.equals("DW") || team2.divChampion.equals("DW")) {
            if (team2.divChampion.equals("DW") && !team.divChampion.equals("DW")) {
                return 1;
            }
            if (team.wins <= team2.wins) {
                if (team.wins != team2.wins) {
                    return 1;
                }
                if (team2.gameWinsAgainst.contains(team)) {
                    if (!team.gameWinsAgainst.contains(team2)) {
                        return 1;
                    }
                    final int divWins = team.getDivWins();
                    final int divWins2 = team2.getDivWins();
                    if (divWins <= divWins2) {
                        if (divWins == divWins2) {
                            return 0;
                        }
                        return 1;
                    }
                }
            }
        }
        return -1;
    }
}
