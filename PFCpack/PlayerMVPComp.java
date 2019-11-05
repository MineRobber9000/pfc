// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

import java.util.Comparator;

class PlayerMVPComp implements Comparator<Player>
{
    @Override
    public int compare(final Player player, final Player player2) {
        if (player.getMVPScore() > player2.getMVPScore()) {
            return -1;
        }
        if (player.getMVPScore() == player2.getMVPScore()) {
            return 0;
        }
        return 1;
    }
}
