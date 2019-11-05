// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

import java.util.Iterator;
import java.util.Arrays;
import java.util.Collection;
import java.util.ArrayList;

public class PlayerPickerLogic
{
    public static Player draftPlayer(final Team team, final ArrayList<Player> list, final boolean b) {
        Player player = list.get(0);
        final String neededPosition = team.getNeededPosition(b);
        for (int n = 0; n < list.size() && n < 10; ++n) {
            player = list.get(n);
            if (player.position.equals(neededPosition) || n == list.size() - 1) {
                team.addPlayer(player);
                player.setTeam(team);
                player.addTeamPlayedFor(team.abbr, team.league.getYear());
                return player;
            }
        }
        if (b) {
            team.addPlayer(list.get(0));
            list.get(0).setTeam(team);
            list.get(0).addTeamPlayedFor(team.abbr, team.league.getYear());
            return list.get(0);
        }
        for (int i = 0; i < list.size(); ++i) {
            player = list.get(i);
            if (!player.position.equals("K")) {
                break;
            }
        }
        team.addPlayer(player);
        player.setTeam(team);
        player.addTeamPlayedFor(team.abbr, team.league.getYear());
        return player;
    }
    
    public static boolean signFreeAgentForTeam(final ArrayList<Team> list, final Player player, final ArrayList<String> list2, int i, int j, final boolean b) {
        if ((player.getRatOvr() < i && player.getAge() <= 30) || (player.getRatOvr() < j && player.getAge() > 30)) {
            return false;
        }
        final Contract contractFA = Contract.getContractFA(player);
        final ArrayList<Team> list3 = new ArrayList<Team>();
        list3.addAll(list);
        if (!b) {
            for (final Team team : list3) {
                if (team.userControlled) {
                    list3.remove(team);
                    break;
                }
            }
        }
        i = 0;
        while (i == 0) {
            j = 0;
            while (j < list3.size()) {
                if (contractFA.getMoneyPerYear() > list3.get(j).getSalaryCapRoom() && contractFA.getMoneyPerYear() != 0.5) {
                    list3.remove(j);
                }
                else {
                    ++j;
                }
            }
            if (list3.isEmpty()) {
                contractFA.decreaseMoneyAndYears();
                list3.clear();
                list3.addAll(list);
                if (b) {
                    continue;
                }
                for (final Team team2 : list3) {
                    if (team2.userControlled) {
                        list3.remove(team2);
                        break;
                    }
                }
            }
            else {
                i = 1;
            }
        }
        int[] array;
        for (array = new int[list3.size()], i = 0; i < array.length; ++i) {
            array[i] = list3.get(i).getValueAdded(player);
        }
        Arrays.sort(array);
        for (final Team team3 : list3) {
            if (team3.getValueAdded(player) == array[array.length - 1]) {
                System.out.println(team3.abbr + " signs " + player.getPosNameYrOvrPot_OneLine() + " for $" + contractFA.getMoneyPerYear() + "/yr, valueAdded = " + array[array.length - 1]);
                player.setContract(contractFA);
                player.setTeam(team3);
                team3.addPlayer(player);
                player.addTeamPlayedFor(team3.abbr, team3.league.getYear());
                if (list2 != null) {
                    list2.add(team3.abbr + " signs " + player.getPosNameAge_Str() + "\n\t\t" + player.getOvrPotDurFootIQ_Str() + "\n\t\tSigned to a " + contractFA.toString() + " contract");
                }
                return true;
            }
        }
        return false;
    }
}
