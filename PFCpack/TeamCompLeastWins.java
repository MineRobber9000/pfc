// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

import java.util.Comparator;

class TeamCompLeastWins implements Comparator<Team>
{
    @Override
    public int compare(final Team team, final Team team2) {
        if (team.wins < team2.wins) {
            return -1;
        }
        if (team.wins == team2.wins) {
            return 0;
        }
        return 1;
    }
}
