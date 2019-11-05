// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import java.util.Collection;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ArrayList;

public class Trade
{
    private ArrayList<TradePiece> aOffer;
    private Team aTeam;
    private ArrayList<TradePiece> bOffer;
    private Team bTeam;
    
    public Trade(final Team aTeam, final Team bTeam) {
        this.aTeam = aTeam;
        this.bTeam = bTeam;
    }
    
    private static void addGoodNonStarters(final Team team, final ArrayList<TradePiece> list) {
        final Player goodNonStarterList = getGoodNonStarterList(team.teamQBs, 1);
        if (goodNonStarterList != null) {
            list.add(goodNonStarterList);
        }
        final Player goodNonStarterList2 = getGoodNonStarterList(team.teamRBs, 2);
        if (goodNonStarterList2 != null) {
            list.add(goodNonStarterList2);
        }
        final Player goodNonStarterList3 = getGoodNonStarterList(team.teamWRs, 2);
        if (goodNonStarterList3 != null) {
            list.add(goodNonStarterList3);
        }
        final Player goodNonStarterList4 = getGoodNonStarterList(team.teamOLs, 3);
        if (goodNonStarterList4 != null) {
            list.add(goodNonStarterList4);
        }
        final Player goodNonStarterList5 = getGoodNonStarterList(team.teamKs, 1);
        if (goodNonStarterList5 != null) {
            list.add(goodNonStarterList5);
        }
        final Player goodNonStarterList6 = getGoodNonStarterList(team.teamSs, 1);
        if (goodNonStarterList6 != null) {
            list.add(goodNonStarterList6);
        }
        final Player goodNonStarterList7 = getGoodNonStarterList(team.teamCBs, 2);
        if (goodNonStarterList7 != null) {
            list.add(goodNonStarterList7);
        }
        final Player goodNonStarterList8 = getGoodNonStarterList(team.teamDLs, 3);
        if (goodNonStarterList8 != null) {
            list.add(goodNonStarterList8);
        }
        final Player goodNonStarterList9 = getGoodNonStarterList(team.teamLBs, 2);
        if (goodNonStarterList9 != null) {
            list.add(goodNonStarterList9);
        }
    }
    
    private static int calculateAvgTalentPosition(final ArrayList<? extends Player> list, final int n, final boolean b) {
        Label_0022: {
            if (b) {
                if (list.size() >= n) {
                    break Label_0022;
                }
            }
            else if (list.size() > n) {
                break Label_0022;
            }
            return 0;
        }
        int n2 = 0;
        int n3 = 0;
        int n4;
        int n5;
        for (int i = 0; i < list.size(); ++i, n3 = n4, n2 = n5) {
            n4 = n3;
            n5 = n2;
            if (i < n + 1) {
                int ratOvr;
                if ((ratOvr = ((Player)list.get(i)).getRatOvr()) > 85) {
                    ratOvr = 85;
                }
                int n6;
                if ((n6 = ratOvr) < 70) {
                    n6 = 70;
                }
                n5 = n2 + n6;
                n4 = n3 + 1;
            }
        }
        return n2 / n3;
    }
    
    public static boolean checkUntradable(final Team team, final Player player) {
        return (player.position.equals("QB") && player.getRatOvr() > 90) || (player.position.equals("RB") && player.getRatOvr() > 92) || (player.position.equals("WR") && player.getRatOvr() > 95);
    }
    
    private static DraftPick findDraftPickClosestValue(final ArrayList<DraftPick> list, final int n, final boolean b, final ArrayList<DraftPick> list2) {
        int n2 = 10000;
        int n3 = 0;
        int n4;
        int n5;
        for (int i = 0; i < list.size(); ++i, n2 = n4, n3 = n5) {
            if (b) {
                n4 = n2;
                n5 = n3;
                if (!list2.contains(list.get(i))) {
                    n4 = n2;
                    n5 = n3;
                    if (n - list.get(i).getTradeValue() > 0) {
                        n4 = n2;
                        n5 = n3;
                        if (n - list.get(i).getTradeValue() <= n2) {
                            n4 = Math.abs(n - list.get(i).getTradeValue());
                            n5 = i;
                        }
                    }
                }
            }
            else {
                n4 = n2;
                n5 = n3;
                if (!list2.contains(list.get(i))) {
                    n4 = n2;
                    n5 = n3;
                    if (list.get(i).getTradeValue() - n > 0) {
                        n4 = n2;
                        n5 = n3;
                        if (list.get(i).getTradeValue() - n <= n2) {
                            n4 = Math.abs(list.get(i).getTradeValue() - n);
                            n5 = i;
                        }
                    }
                }
            }
        }
        if (n2 != 10000) {
            return list.get(n3);
        }
        if (!b) {
            for (final DraftPick draftPick : list) {
                if (!list2.contains(draftPick)) {
                    return draftPick;
                }
            }
            return null;
        }
        return null;
    }
    
    private static Player findPlayerDeepestPosition(final Team team, final Team team2, final ArrayList<Player> list, int i) {
        team2.sortPlayers();
        final int calculateAvgTalentPosition = calculateAvgTalentPosition(team2.teamQBs, 1, false);
        final int calculateAvgTalentPosition2 = calculateAvgTalentPosition(team2.teamRBs, 2, false);
        final int calculateAvgTalentPosition3 = calculateAvgTalentPosition(team2.teamWRs, 3, false);
        final int calculateAvgTalentPosition4 = calculateAvgTalentPosition(team2.teamOLs, 5, false);
        final int calculateAvgTalentPosition5 = calculateAvgTalentPosition(team2.teamKs, 1, false);
        final int calculateAvgTalentPosition6 = calculateAvgTalentPosition(team2.teamSs, 1, false);
        final int calculateAvgTalentPosition7 = calculateAvgTalentPosition(team2.teamCBs, 3, false);
        final int calculateAvgTalentPosition8 = calculateAvgTalentPosition(team2.teamDLs, 4, false);
        final int[] array = { calculateAvgTalentPosition, calculateAvgTalentPosition2, calculateAvgTalentPosition3, calculateAvgTalentPosition4, calculateAvgTalentPosition5, calculateAvgTalentPosition6, calculateAvgTalentPosition7, calculateAvgTalentPosition8, calculateAvgTalentPosition(team2.teamLBs, 3, false) };
        Arrays.sort(array);
        int j = array.length - 1;
        int n = 1;
        Object o = team2.teamKs;
        while (j > 0) {
            if (calculateAvgTalentPosition == array[j]) {
                int n2;
                if ((n2 = n) != 0) {
                    o = team2.teamQBs;
                    n2 = 0;
                }
                final Player playerListClosestOvr = findPlayerListClosestOvr(team2.teamQBs, list, i);
                if (playerListClosestOvr != null) {
                    team2.removePlayer(playerListClosestOvr);
                    team.addPlayer(playerListClosestOvr);
                    return playerListClosestOvr;
                }
                --j;
                n = n2;
            }
            else if (calculateAvgTalentPosition2 == array[j]) {
                int n3;
                if ((n3 = n) != 0) {
                    o = team2.teamRBs;
                    n3 = 0;
                }
                final Player playerListClosestOvr2 = findPlayerListClosestOvr(team2.teamRBs, list, i);
                if (playerListClosestOvr2 != null) {
                    team2.removePlayer(playerListClosestOvr2);
                    team.addPlayer(playerListClosestOvr2);
                    return playerListClosestOvr2;
                }
                --j;
                n = n3;
            }
            else if (calculateAvgTalentPosition3 == array[j]) {
                int n4;
                if ((n4 = n) != 0) {
                    o = team2.teamWRs;
                    n4 = 0;
                }
                final Player playerListClosestOvr3 = findPlayerListClosestOvr(team2.teamWRs, list, i);
                if (playerListClosestOvr3 != null) {
                    team2.removePlayer(playerListClosestOvr3);
                    team.addPlayer(playerListClosestOvr3);
                    return playerListClosestOvr3;
                }
                --j;
                n = n4;
            }
            else if (calculateAvgTalentPosition4 == array[j]) {
                int n5;
                if ((n5 = n) != 0) {
                    o = team2.teamOLs;
                    n5 = 0;
                }
                final Player playerListClosestOvr4 = findPlayerListClosestOvr(team2.teamOLs, list, i);
                if (playerListClosestOvr4 != null) {
                    team2.removePlayer(playerListClosestOvr4);
                    team.addPlayer(playerListClosestOvr4);
                    return playerListClosestOvr4;
                }
                --j;
                n = n5;
            }
            else if (calculateAvgTalentPosition5 == array[j]) {
                int n6;
                if ((n6 = n) != 0) {
                    o = team2.teamKs;
                    n6 = 0;
                }
                final Player playerListClosestOvr5 = findPlayerListClosestOvr(team2.teamKs, list, i);
                if (playerListClosestOvr5 != null) {
                    team2.removePlayer(playerListClosestOvr5);
                    team.addPlayer(playerListClosestOvr5);
                    return playerListClosestOvr5;
                }
                --j;
                n = n6;
            }
            else if (calculateAvgTalentPosition6 == array[j]) {
                int n7;
                if ((n7 = n) != 0) {
                    o = team2.teamSs;
                    n7 = 0;
                }
                final Player playerListClosestOvr6 = findPlayerListClosestOvr(team2.teamSs, list, i);
                if (playerListClosestOvr6 != null) {
                    team2.removePlayer(playerListClosestOvr6);
                    team.addPlayer(playerListClosestOvr6);
                    return playerListClosestOvr6;
                }
                --j;
                n = n7;
            }
            else if (calculateAvgTalentPosition7 == array[j]) {
                int n8;
                if ((n8 = n) != 0) {
                    o = team2.teamCBs;
                    n8 = 0;
                }
                final Player playerListClosestOvr7 = findPlayerListClosestOvr(team2.teamCBs, list, i);
                if (playerListClosestOvr7 != null) {
                    team2.removePlayer(playerListClosestOvr7);
                    team.addPlayer(playerListClosestOvr7);
                    return playerListClosestOvr7;
                }
                --j;
                n = n8;
            }
            else if (calculateAvgTalentPosition8 == array[j]) {
                int n9;
                if ((n9 = n) != 0) {
                    o = team2.teamDLs;
                    n9 = 0;
                }
                final Player playerListClosestOvr8 = findPlayerListClosestOvr(team2.teamDLs, list, i);
                if (playerListClosestOvr8 != null) {
                    team2.removePlayer(playerListClosestOvr8);
                    team.addPlayer(playerListClosestOvr8);
                    return playerListClosestOvr8;
                }
                --j;
                n = n9;
            }
            else {
                int n10;
                if ((n10 = n) != 0) {
                    o = team2.teamLBs;
                    n10 = 0;
                }
                final Player playerListClosestOvr9 = findPlayerListClosestOvr(team2.teamLBs, list, i);
                if (playerListClosestOvr9 != null) {
                    team2.removePlayer(playerListClosestOvr9);
                    team.addPlayer(playerListClosestOvr9);
                    return playerListClosestOvr9;
                }
                --j;
                n = n10;
            }
        }
        Player player;
        for (i = 0; i < ((ArrayList)o).size(); ++i) {
            player = ((ArrayList<Player>)o).get(((ArrayList)o).size() - i - 1);
            if (!list.contains(player)) {
                team2.removePlayer(player);
                team.addPlayer(player);
                return player;
            }
        }
        return null;
    }
    
    private static Player findPlayerListClosestOvr(final ArrayList<? extends Player> list, final ArrayList<Player> list2, final int n) {
        int n2 = 0;
        if (list.size() <= 0) {
            return null;
        }
        final Iterator<Player> iterator = list2.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getPosition().equals(((Player)list.get(0)).getPosition())) {
                ++n2;
            }
        }
        if (n2 >= 2) {
            return null;
        }
        int n3 = 100;
        int n4 = 0;
        int abs;
        int n5;
        for (int i = 0; i < list.size(); ++i, n3 = abs, n4 = n5) {
            abs = n3;
            n5 = n4;
            if (Math.abs(((Player)list.get(i)).getRatOvr() - n) <= n3) {
                abs = n3;
                n5 = n4;
                if (!((Player)list.get(i)).isInjured()) {
                    abs = Math.abs(((Player)list.get(i)).getRatOvr() - n);
                    n5 = i;
                }
            }
        }
        if (list.size() > 0 && !list2.contains(list.get(n4))) {
            return (Player)list.get(n4);
        }
        return null;
    }
    
    private static Player findPlayerWeakestPosition(final Team team, final Team team2, final ArrayList<Player> list, int i) {
        team2.sortPlayers();
        final int calculateAvgTalentPosition = calculateAvgTalentPosition(team2.teamQBs, 1, true);
        final int calculateAvgTalentPosition2 = calculateAvgTalentPosition(team2.teamRBs, 2, true);
        final int calculateAvgTalentPosition3 = calculateAvgTalentPosition(team2.teamWRs, 3, true);
        final int calculateAvgTalentPosition4 = calculateAvgTalentPosition(team2.teamOLs, 5, true);
        final int calculateAvgTalentPosition5 = calculateAvgTalentPosition(team2.teamKs, 1, true);
        final int calculateAvgTalentPosition6 = calculateAvgTalentPosition(team2.teamSs, 1, true);
        final int calculateAvgTalentPosition7 = calculateAvgTalentPosition(team2.teamCBs, 3, true);
        final int calculateAvgTalentPosition8 = calculateAvgTalentPosition(team2.teamDLs, 4, true);
        final int[] array = { calculateAvgTalentPosition, calculateAvgTalentPosition2, calculateAvgTalentPosition3, calculateAvgTalentPosition4, calculateAvgTalentPosition5, calculateAvgTalentPosition6, calculateAvgTalentPosition7, calculateAvgTalentPosition8, calculateAvgTalentPosition(team2.teamLBs, 3, true) };
        Arrays.sort(array);
        int j = 0;
        int n = 1;
        Object o = team.teamOLs;
        while (j < array.length - 1) {
            if (calculateAvgTalentPosition == array[j]) {
                int n2;
                if ((n2 = n) != 0) {
                    o = team.teamQBs;
                    n2 = 0;
                }
                final Player playerListClosestOvr = findPlayerListClosestOvr(team.teamQBs, list, i);
                if (playerListClosestOvr != null) {
                    team.removePlayer(playerListClosestOvr);
                    team2.addPlayer(playerListClosestOvr);
                    return playerListClosestOvr;
                }
                ++j;
                n = n2;
            }
            else if (calculateAvgTalentPosition2 == array[j]) {
                int n3;
                if ((n3 = n) != 0) {
                    o = team.teamRBs;
                    n3 = 0;
                }
                final Player playerListClosestOvr2 = findPlayerListClosestOvr(team.teamRBs, list, i);
                if (playerListClosestOvr2 != null) {
                    team.removePlayer(playerListClosestOvr2);
                    team2.addPlayer(playerListClosestOvr2);
                    return playerListClosestOvr2;
                }
                ++j;
                n = n3;
            }
            else if (calculateAvgTalentPosition3 == array[j]) {
                int n4;
                if ((n4 = n) != 0) {
                    o = team.teamWRs;
                    n4 = 0;
                }
                final Player playerListClosestOvr3 = findPlayerListClosestOvr(team.teamWRs, list, i);
                if (playerListClosestOvr3 != null) {
                    team.removePlayer(playerListClosestOvr3);
                    team2.addPlayer(playerListClosestOvr3);
                    return playerListClosestOvr3;
                }
                ++j;
                n = n4;
            }
            else if (calculateAvgTalentPosition4 == array[j]) {
                int n5;
                if ((n5 = n) != 0) {
                    o = team.teamOLs;
                    n5 = 0;
                }
                final Player playerListClosestOvr4 = findPlayerListClosestOvr(team.teamOLs, list, i);
                if (playerListClosestOvr4 != null) {
                    team.removePlayer(playerListClosestOvr4);
                    team2.addPlayer(playerListClosestOvr4);
                    return playerListClosestOvr4;
                }
                ++j;
                n = n5;
            }
            else if (calculateAvgTalentPosition5 == array[j]) {
                int n6;
                if ((n6 = n) != 0) {
                    o = team.teamKs;
                    n6 = 0;
                }
                final Player playerListClosestOvr5 = findPlayerListClosestOvr(team.teamKs, list, i);
                if (playerListClosestOvr5 != null) {
                    team.removePlayer(playerListClosestOvr5);
                    team2.addPlayer(playerListClosestOvr5);
                    return playerListClosestOvr5;
                }
                ++j;
                n = n6;
            }
            else if (calculateAvgTalentPosition6 == array[j]) {
                int n7;
                if ((n7 = n) != 0) {
                    o = team.teamSs;
                    n7 = 0;
                }
                final Player playerListClosestOvr6 = findPlayerListClosestOvr(team.teamSs, list, i);
                if (playerListClosestOvr6 != null) {
                    team.removePlayer(playerListClosestOvr6);
                    team2.addPlayer(playerListClosestOvr6);
                    return playerListClosestOvr6;
                }
                ++j;
                n = n7;
            }
            else if (calculateAvgTalentPosition7 == array[j]) {
                int n8;
                if ((n8 = n) != 0) {
                    o = team.teamCBs;
                    n8 = 0;
                }
                final Player playerListClosestOvr7 = findPlayerListClosestOvr(team.teamCBs, list, i);
                if (playerListClosestOvr7 != null) {
                    team.removePlayer(playerListClosestOvr7);
                    team2.addPlayer(playerListClosestOvr7);
                    return playerListClosestOvr7;
                }
                ++j;
                n = n8;
            }
            else if (calculateAvgTalentPosition8 == array[j]) {
                int n9;
                if ((n9 = n) != 0) {
                    o = team.teamDLs;
                    n9 = 0;
                }
                final Player playerListClosestOvr8 = findPlayerListClosestOvr(team.teamDLs, list, i);
                if (playerListClosestOvr8 != null) {
                    team.removePlayer(playerListClosestOvr8);
                    team2.addPlayer(playerListClosestOvr8);
                    return playerListClosestOvr8;
                }
                ++j;
                n = n9;
            }
            else {
                int n10;
                if ((n10 = n) != 0) {
                    o = team.teamLBs;
                    n10 = 0;
                }
                final Player playerListClosestOvr9 = findPlayerListClosestOvr(team.teamLBs, list, i);
                if (playerListClosestOvr9 != null) {
                    team.removePlayer(playerListClosestOvr9);
                    team2.addPlayer(playerListClosestOvr9);
                    return playerListClosestOvr9;
                }
                ++j;
                n = n10;
            }
        }
        Player player;
        for (i = 0; i < ((ArrayList)o).size(); ++i) {
            player = ((ArrayList<Player>)o).get(i);
            if (!list.contains(player)) {
                team.removePlayer(player);
                team2.addPlayer(player);
                return player;
            }
        }
        return null;
    }
    
    private static Player getGoodNonStarterList(final ArrayList<? extends Player> list, int i) {
        while (i < list.size()) {
            if (((Player)list.get(i)).getRatOvr() >= 80) {
                return (Player)list.get(i);
            }
            ++i;
        }
        return null;
    }
    
    public static int getTotalValue(final ArrayList<TradePiece> list) {
        int n = 0;
        final Iterator<TradePiece> iterator = list.iterator();
        while (iterator.hasNext()) {
            n += iterator.next().getTradeValue();
        }
        return n;
    }
    
    public static Trade getTradeOfferFromAI(final Team team, final Team team2, final ArrayList<TradePiece> list) {
        final double n = team.wins / 25.0 + 1.2;
        final Trade trade = new Trade(team, team2);
        final ArrayList<Object> list2 = new ArrayList<Object>();
        list2.addAll(list);
        int n2 = 0;
        double n3 = 0.0;
        int n4 = 7;
        int n5 = 0;
        final ArrayList<DraftPick> list3 = new ArrayList<DraftPick>();
        for (final TradePiece tradePiece : list2) {
            if (tradePiece instanceof DraftPick) {
                list3.add((DraftPick)tradePiece);
                final int n6 = n2 + tradePiece.getTradeValue();
                final int round = ((DraftPick)tradePiece).getRound();
                final int n7 = n5 + (8 - round);
                n2 = n6;
                n5 = n7;
                if (round >= n4) {
                    continue;
                }
                n4 = round;
                n2 = n6;
                n5 = n7;
            }
            else {
                if (team.getValueAdded((Player)tradePiece) > 0) {
                    n2 += tradePiece.getTradeValue();
                }
                else {
                    n2 += team.getValueAdded((Player)tradePiece);
                }
                n3 += ((Player)tradePiece).getContract().getMoneyPerYear();
            }
        }
        final ArrayList<Player> list4 = new ArrayList<Player>();
        int n8 = 0;
        int n9 = 0;
        for (final DraftPick draftPick : list2) {
            if (draftPick instanceof Player) {
                final Player player = (Player)draftPick;
                team.addPlayer(player);
                team2.removePlayer(player);
                n8 += player.getRatOvr();
                list4.add(player);
                ++n9;
            }
        }
        int n10;
        if (n9 > 0) {
            n10 = n8 / n9 + n5 / 2;
        }
        else {
            n10 = 81 - (int)(1.5 * n4);
        }
        final ArrayList<Object> list5 = new ArrayList<Object>();
        final ArrayList<Player> list6 = new ArrayList<Player>();
        for (int n11 = 0; n11 < n9 && getTotalValue((ArrayList<TradePiece>)list5) * n < n2; ++n11) {
            final Player playerDeepestPosition = findPlayerDeepestPosition(team2, team, list4, n10);
            if (playerDeepestPosition != null) {
                list5.add(playerDeepestPosition);
                list6.add(playerDeepestPosition);
            }
        }
        if (getTotalValue((ArrayList<TradePiece>)list5) * n < n2) {
            int n12 = 0;
            int n13 = 0;
            final ArrayList<DraftPick> list7 = new ArrayList<DraftPick>();
            while (getTotalValue((ArrayList<TradePiece>)list5) * n < n2) {
                int n14;
                if ((n12 == 0 || n12 == 1) && n13 < 6) {
                    n14 = n13;
                    if (team.draftPicks.size() > 0) {
                        final DraftPick draftPickClosestValue = findDraftPickClosestValue(team.draftPicks, (int)(n2 - getTotalValue((ArrayList<TradePiece>)list5) * n), true, list7);
                        if (draftPickClosestValue != null) {
                            list7.add(draftPickClosestValue);
                            list5.add(draftPickClosestValue);
                        }
                        n14 = n13 + 1;
                    }
                }
                else {
                    final Player playerDeepestPosition2 = findPlayerDeepestPosition(team2, team, list4, n10);
                    n14 = n13;
                    if (playerDeepestPosition2 != null) {
                        list5.add(playerDeepestPosition2);
                        list6.add(playerDeepestPosition2);
                        n14 = n13;
                    }
                }
                final int n15 = n12 + 1;
                n13 = n14;
                if ((n12 = n15) > 2) {
                    n12 = 0;
                    n13 = n14;
                }
            }
        }
        if (getTotalValue((ArrayList<TradePiece>)list5) * n > n2) {
            int n16 = 0;
            int n17 = 0;
            while (getTotalValue((ArrayList<TradePiece>)list5) * n > getTotalValue((ArrayList<TradePiece>)list2)) {
                int n18;
                if ((n16 == 0 || n16 == 1) && n17 < 6) {
                    n18 = n17;
                    if (team2.draftPicks.size() > 0) {
                        final DraftPick draftPickClosestValue2 = findDraftPickClosestValue(team2.draftPicks, (int)(getTotalValue((ArrayList<TradePiece>)list5) * n - getTotalValue((ArrayList<TradePiece>)list2)), false, list3);
                        if (draftPickClosestValue2 != null) {
                            list3.add(draftPickClosestValue2);
                            list2.add(draftPickClosestValue2);
                        }
                        n18 = n17 + 1;
                    }
                }
                else {
                    final Player playerWeakestPosition = findPlayerWeakestPosition(team2, team, list6, n10);
                    n18 = n17;
                    if (playerWeakestPosition != null) {
                        list2.add(playerWeakestPosition);
                        list4.add(playerWeakestPosition);
                        n18 = n17;
                    }
                }
                final int n19 = n16 + 1;
                n17 = n18;
                if ((n16 = n19) > 2) {
                    n16 = 0;
                    n17 = n18;
                }
            }
        }
        Collections.sort(list5, (Comparator<? super Object>)new TradePieceComparator());
        Collections.sort(list2, (Comparator<? super Object>)new TradePieceComparator());
        trade.setOffer(team, (ArrayList<TradePiece>)list5);
        trade.setOffer(team2, (ArrayList<TradePiece>)list2);
        for (final Player player2 : list4) {
            team.removePlayer(player2);
            team2.addPlayer(player2);
        }
        for (final Player player3 : list6) {
            team.addPlayer(player3);
            team2.removePlayer(player3);
        }
        team2.sortPlayers();
        team.sortPlayers();
        return trade;
    }
    
    public static Trade getTradeOfferFromUserTeam(final Team team, final Team team2, final ArrayList<TradePiece> list) {
        final double n = team2.wins / 25.0 + 1.2;
        final Trade trade = new Trade(team, team2);
        final ArrayList<Object> list2 = new ArrayList<Object>();
        list2.addAll(list);
        int n2 = 0;
        double n3 = 0.0;
        int n4 = 7;
        int n5 = 0;
        for (final TradePiece tradePiece : list2) {
            if (tradePiece instanceof DraftPick) {
                final int n6 = n2 + tradePiece.getTradeValue();
                final int round = ((DraftPick)tradePiece).getRound();
                final int n7 = n5 + (8 - round);
                n2 = n6;
                n5 = n7;
                if (round >= n4) {
                    continue;
                }
                n4 = round;
                n2 = n6;
                n5 = n7;
            }
            else {
                n2 += tradePiece.getTradeValue();
                n3 += ((Player)tradePiece).getContract().getMoneyPerYear();
            }
        }
        final ArrayList<Player> list3 = new ArrayList<Player>();
        int n8 = 0;
        int n9 = 0;
        for (final TradePiece tradePiece2 : list2) {
            if (tradePiece2 instanceof Player) {
                final Player player = (Player)tradePiece2;
                team2.removePlayer(player);
                n8 += player.getRatOvr();
                list3.add(player);
                ++n9;
            }
        }
        int n10;
        if (n9 > 0) {
            n10 = n8 / n9 + (int)(n5 / 1.5);
        }
        else {
            n10 = 84 - (int)(1.5 * n4);
        }
        final ArrayList<Object> list4 = (ArrayList<Object>)new ArrayList<DraftPick>();
        final ArrayList<DraftPick> list5 = new ArrayList<DraftPick>();
        for (int n11 = 0; n11 < n9 && getTotalValue((ArrayList<TradePiece>)list4) < n2 * n; ++n11) {
            final Player playerWeakestPosition = findPlayerWeakestPosition(team, team2, list3, n10);
            if (playerWeakestPosition != null) {
                list4.add(playerWeakestPosition);
                list5.add((DraftPick)playerWeakestPosition);
            }
        }
        int n12 = 0;
        int n13 = 0;
        final ArrayList<DraftPick> list6 = new ArrayList<DraftPick>();
        while (getTotalValue((ArrayList<TradePiece>)list4) < n2 * n) {
            int n14;
            if ((n12 == 0 || n12 == 1) && n13 < 6) {
                n14 = n13;
                if (team2.draftPicks.size() > 0) {
                    final DraftPick draftPickClosestValue = findDraftPickClosestValue(team.draftPicks, (int)(n2 * n - getTotalValue((ArrayList<TradePiece>)list4)), false, list6);
                    if (draftPickClosestValue != null) {
                        list6.add(draftPickClosestValue);
                        list4.add(draftPickClosestValue);
                    }
                    n14 = n13 + 1;
                }
            }
            else {
                final Player playerWeakestPosition2 = findPlayerWeakestPosition(team, team2, list3, n10);
                n14 = n13;
                if (playerWeakestPosition2 != null) {
                    list4.add(playerWeakestPosition2);
                    list5.add((DraftPick)playerWeakestPosition2);
                    n14 = n13;
                }
            }
            final int n15 = n12 + 1;
            n13 = n14;
            if ((n12 = n15) > 2) {
                n12 = 0;
                n13 = n14;
            }
        }
        Collections.sort(list4, (Comparator<? super Object>)new TradePieceComparator());
        Collections.sort(list2, (Comparator<? super Object>)new TradePieceComparator());
        trade.setOffer(team, (ArrayList<TradePiece>)list4);
        trade.setOffer(team2, (ArrayList<TradePiece>)list2);
        for (final Player player2 : list3) {
            team2.addPlayer(player2);
            team.removePlayer(player2);
        }
        for (final Player player3 : list5) {
            team.addPlayer(player3);
            team2.removePlayer(player3);
        }
        team2.sortPlayers();
        team.sortPlayers();
        return trade;
    }
    
    private void transferDraftPick(final DraftPick draftPick, final Team team, final Team teamOwner) {
        team.draftPicks.remove(draftPick);
        teamOwner.draftPicks.add(draftPick);
        draftPick.setTeamOwner(teamOwner);
    }
    
    private void transferTradePieces(final ArrayList<TradePiece> list, final Team team, final Team team2) {
        for (final TradePiece tradePiece : list) {
            if (tradePiece instanceof Player) {
                final Player player = (Player)tradePiece;
                team.removePlayer(player);
                team2.addPlayer(player);
                player.setTeam(team2);
                player.addTeamPlayedFor(team2.abbr, team2.league.getYear());
            }
            else {
                if (!(tradePiece instanceof DraftPick)) {
                    continue;
                }
                this.transferDraftPick((DraftPick)tradePiece, team, team2);
            }
        }
        Collections.sort(team2.draftPicks, new DraftPickComparatorYearRound());
        Collections.sort(team.draftPicks, new DraftPickComparatorYearRound());
    }
    
    public void addPieceToOffer(final Team team, final TradePiece tradePiece) {
        if (team == this.aTeam) {
            this.aOffer.add(tradePiece);
            return;
        }
        this.bOffer.add(tradePiece);
    }
    
    public void completeTrade() {
        this.transferTradePieces(this.aOffer, this.aTeam, this.bTeam);
        this.transferTradePieces(this.bOffer, this.bTeam, this.aTeam);
        this.aTeam.tradingBlock.clear();
        this.bTeam.tradingBlock.clear();
        this.aTeam.signUDFAs();
        this.bTeam.signUDFAs();
        this.aTeam.sortPlayers();
        this.bTeam.sortPlayers();
    }
    
    public ArrayList<TradePiece> getAOffer() {
        return this.aOffer;
    }
    
    public Team getATeam() {
        return this.aTeam;
    }
    
    public ArrayList<TradePiece> getBOffer() {
        return this.bOffer;
    }
    
    public Team getBTeam() {
        return this.bTeam;
    }
    
    public void setOffer(final Team team, final ArrayList<TradePiece> list) {
        if (team == this.aTeam) {
            this.aOffer = list;
            return;
        }
        this.bOffer = list;
    }
}
