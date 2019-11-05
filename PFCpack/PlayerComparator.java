// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

import java.util.Comparator;

class PlayerComparator implements Comparator<Player>
{
    @Override
    public int compare(final Player player, final Player player2) {
        if (player.isInjured() || !player2.isInjured()) {
            if (player.isInjured() && !player2.isInjured()) {
                return 1;
            }
            if (player.ratOvr <= player2.ratOvr) {
                if (player.ratOvr == player2.ratOvr) {
                    return 0;
                }
                return 1;
            }
        }
        return -1;
    }
}
