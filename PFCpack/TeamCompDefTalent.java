// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

import java.util.Comparator;

class TeamCompDefTalent implements Comparator<Team>
{
    @Override
    public int compare(final Team team, final Team team2) {
        if (team.teamDefTalent > team2.teamDefTalent) {
            return -1;
        }
        if (team.teamDefTalent == team2.teamDefTalent) {
            return 0;
        }
        return 1;
    }
}
