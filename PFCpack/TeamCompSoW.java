// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

import java.util.Comparator;

class TeamCompSoW implements Comparator<Team>
{
    @Override
    public int compare(final Team team, final Team team2) {
        if (team.teamStrengthOfWins > team2.teamStrengthOfWins) {
            return -1;
        }
        if (team.teamStrengthOfWins == team2.teamStrengthOfWins) {
            return 0;
        }
        return 1;
    }
}
