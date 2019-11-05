// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

import java.util.Iterator;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

public class DraftGenerator
{
    public static ArrayList<Player> generateLeaguePlayers(final League league) {
        final ArrayList<Player> list = new ArrayList<Player>();
        for (int n = 9750 / 46, i = 0; i < n; ++i) {
            final int n2 = (int)(15.0 * Math.random());
            final PlayerQB playerQB = new PlayerQB(league.getRandName(), 2016 - n2);
            playerQB.gamesPlayed = 10;
            for (int j = 0; j < n2; ++j) {
                playerQB.advanceSeasonRatingsAge();
            }
            playerQB.gamesPlayed = 0;
            list.add(playerQB);
        }
        for (int n3 = 13000 / 46, k = 0; k < n3; ++k) {
            final int n4 = (int)(15.0 * Math.random());
            final PlayerRB playerRB = new PlayerRB(league.getRandName(), 2016 - n4);
            playerRB.gamesPlayed = 10;
            for (int l = 0; l < n4; ++l) {
                playerRB.advanceSeasonRatingsAge();
            }
            playerRB.gamesPlayed = 0;
            list.add(playerRB);
        }
        for (int n5 = 19500 / 46, n6 = 0; n6 < n5; ++n6) {
            final int n7 = (int)(15.0 * Math.random());
            final PlayerWR playerWR = new PlayerWR(league.getRandName(), 2016 - n7);
            playerWR.gamesPlayed = 10;
            for (int n8 = 0; n8 < n7; ++n8) {
                playerWR.advanceSeasonRatingsAge();
            }
            playerWR.gamesPlayed = 0;
            list.add(playerWR);
        }
        for (int n9 = 32500 / 46, n10 = 0; n10 < n9; ++n10) {
            final int n11 = (int)(15.0 * Math.random());
            final PlayerOL playerOL = new PlayerOL(league.getRandName(), 2016 - n11);
            playerOL.gamesPlayed = 10;
            for (int n12 = 0; n12 < n11; ++n12) {
                playerOL.advanceSeasonRatingsAge();
            }
            playerOL.gamesPlayed = 0;
            list.add(playerOL);
        }
        for (int n13 = 6500 / 46, n14 = 0; n14 < n13; ++n14) {
            final int n15 = (int)(15.0 * Math.random());
            final PlayerK playerK = new PlayerK(league.getRandName(), 2016 - n15);
            playerK.gamesPlayed = 10;
            for (int n16 = 0; n16 < n15; ++n16) {
                playerK.advanceSeasonRatingsAge();
            }
            playerK.gamesPlayed = 0;
            list.add(playerK);
        }
        for (int n17 = 6500 / 46, n18 = 0; n18 < n17; ++n18) {
            final int n19 = (int)(15.0 * Math.random());
            final PlayerS playerS = new PlayerS(league.getRandName(), 2016 - n19);
            playerS.gamesPlayed = 10;
            for (int n20 = 0; n20 < n19; ++n20) {
                playerS.advanceSeasonRatingsAge();
            }
            playerS.gamesPlayed = 0;
            list.add(playerS);
        }
        for (int n21 = 19500 / 46, n22 = 0; n22 < n21; ++n22) {
            final int n23 = (int)(15.0 * Math.random());
            final PlayerCB playerCB = new PlayerCB(league.getRandName(), 2016 - n23);
            playerCB.gamesPlayed = 10;
            for (int n24 = 0; n24 < n23; ++n24) {
                playerCB.advanceSeasonRatingsAge();
            }
            playerCB.gamesPlayed = 0;
            list.add(playerCB);
        }
        for (int n25 = 26000 / 46, n26 = 0; n26 < n25; ++n26) {
            final int n27 = (int)(15.0 * Math.random());
            final PlayerDL playerDL = new PlayerDL(league.getRandName(), 2016 - n27);
            playerDL.gamesPlayed = 10;
            for (int n28 = 0; n28 < n27; ++n28) {
                playerDL.advanceSeasonRatingsAge();
            }
            playerDL.gamesPlayed = 0;
            list.add(playerDL);
        }
        for (int n29 = 19500 / 46, n30 = 0; n30 < n29; ++n30) {
            final int n31 = (int)(15.0 * Math.random());
            final PlayerLB playerLB = new PlayerLB(league.getRandName(), 2016 - n31);
            playerLB.gamesPlayed = 10;
            for (int n32 = 0; n32 < n31; ++n32) {
                playerLB.advanceSeasonRatingsAge();
            }
            playerLB.gamesPlayed = 0;
            list.add(playerLB);
        }
        Collections.sort((List<Object>)list, (Comparator<? super Object>)new PlayerComparator());
        return list;
    }
    
    public static ArrayList<ArrayList<Player>> generateRookies(final League league) {
        final ArrayList<ArrayList<T>> list = (ArrayList<ArrayList<T>>)new ArrayList<ArrayList<Player>>();
        for (int i = 0; i < 10; ++i) {
            list.add(new ArrayList<Player>());
        }
        for (int n = 700 / 46, j = 0; j < n; ++j) {
            final PlayerQB playerQB = new PlayerQB(league.getRandName(), league.getYear());
            playerQB.setRatingsRookie();
            list.get(1).add(playerQB);
            list.get(0).add(playerQB);
        }
        for (int n2 = 1400 / 46, k = 0; k < n2; ++k) {
            final PlayerRB playerRB = new PlayerRB(league.getRandName(), league.getYear());
            playerRB.setRatingsRookie();
            list.get(2).add(playerRB);
            list.get(0).add(playerRB);
        }
        for (int n3 = 2100 / 46, l = 0; l < n3; ++l) {
            final PlayerWR playerWR = new PlayerWR(league.getRandName(), league.getYear());
            playerWR.setRatingsRookie();
            list.get(3).add(playerWR);
            list.get(0).add(playerWR);
        }
        for (int n4 = 3500 / 46, n5 = 0; n5 < n4; ++n5) {
            final PlayerOL playerOL = new PlayerOL(league.getRandName(), league.getYear());
            playerOL.setRatingsRookie();
            list.get(4).add(playerOL);
            list.get(0).add(playerOL);
        }
        for (int n6 = 700 / 46, n7 = 0; n7 < n6; ++n7) {
            final PlayerK playerK = new PlayerK(league.getRandName(), league.getYear());
            playerK.setRatingsRookie();
            list.get(5).add(playerK);
            list.get(0).add(playerK);
        }
        for (int n8 = 700 / 46, n9 = 0; n9 < n8; ++n9) {
            final PlayerS playerS = new PlayerS(league.getRandName(), league.getYear());
            playerS.setRatingsRookie();
            list.get(6).add(playerS);
            list.get(0).add(playerS);
        }
        for (int n10 = 2100 / 46, n11 = 0; n11 < n10; ++n11) {
            final PlayerCB playerCB = new PlayerCB(league.getRandName(), league.getYear());
            playerCB.setRatingsRookie();
            list.get(7).add(playerCB);
            list.get(0).add(playerCB);
        }
        for (int n12 = 2800 / 46, n13 = 0; n13 < n12; ++n13) {
            final PlayerDL playerDL = new PlayerDL(league.getRandName(), league.getYear());
            playerDL.setRatingsRookie();
            list.get(8).add(playerDL);
            list.get(0).add(playerDL);
        }
        for (int n14 = 2100 / 46, n15 = 0; n15 < n14; ++n15) {
            final PlayerLB playerLB = new PlayerLB(league.getRandName(), league.getYear());
            playerLB.setRatingsRookie();
            list.get(9).add(playerLB);
            list.get(0).add(playerLB);
        }
        final Iterator<ArrayList<Player>> iterator = list.iterator();
        while (iterator.hasNext()) {
            Collections.sort(iterator.next(), new PlayerComparator());
        }
        return (ArrayList<ArrayList<Player>>)list;
    }
}
