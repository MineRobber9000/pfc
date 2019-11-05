// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

import java.util.Comparator;

class TeamCompOffTalent implements Comparator<Team>
{
    @Override
    public int compare(final Team team, final Team team2) {
        if (team.teamOffTalent > team2.teamOffTalent) {
            return -1;
        }
        if (team.teamOffTalent == team2.teamOffTalent) {
            return 0;
        }
        return 1;
    }
}
