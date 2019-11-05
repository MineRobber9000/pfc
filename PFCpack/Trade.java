package PFCpack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Trade
{
  private ArrayList<TradePiece> aOffer;
  private Team aTeam;
  private ArrayList<TradePiece> bOffer;
  private Team bTeam;
  
  public Trade(Team paramTeam1, Team paramTeam2)
  {
    aTeam = paramTeam1;
    bTeam = paramTeam2;
  }
  
  private static void addGoodNonStarters(Team paramTeam, ArrayList<TradePiece> paramArrayList)
  {
    Player localPlayer = getGoodNonStarterList(teamQBs, 1);
    if (localPlayer != null) {
      paramArrayList.add(localPlayer);
    }
    localPlayer = getGoodNonStarterList(teamRBs, 2);
    if (localPlayer != null) {
      paramArrayList.add(localPlayer);
    }
    localPlayer = getGoodNonStarterList(teamWRs, 2);
    if (localPlayer != null) {
      paramArrayList.add(localPlayer);
    }
    localPlayer = getGoodNonStarterList(teamOLs, 3);
    if (localPlayer != null) {
      paramArrayList.add(localPlayer);
    }
    localPlayer = getGoodNonStarterList(teamKs, 1);
    if (localPlayer != null) {
      paramArrayList.add(localPlayer);
    }
    localPlayer = getGoodNonStarterList(teamSs, 1);
    if (localPlayer != null) {
      paramArrayList.add(localPlayer);
    }
    localPlayer = getGoodNonStarterList(teamCBs, 2);
    if (localPlayer != null) {
      paramArrayList.add(localPlayer);
    }
    localPlayer = getGoodNonStarterList(teamDLs, 3);
    if (localPlayer != null) {
      paramArrayList.add(localPlayer);
    }
    paramTeam = getGoodNonStarterList(teamLBs, 2);
    if (paramTeam != null) {
      paramArrayList.add(paramTeam);
    }
  }
  
  private static int calculateAvgTalentPosition(ArrayList<? extends Player> paramArrayList, int paramInt, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if (paramArrayList.size() >= paramInt) {}
    }
    else {
      while (paramArrayList.size() <= paramInt) {
        return 0;
      }
    }
    int m = 0;
    int k = 0;
    int j = 0;
    while (j < paramArrayList.size())
    {
      int n = k;
      int i = m;
      if (j < paramInt + 1)
      {
        n = ((Player)paramArrayList.get(j)).getRatOvr();
        i = n;
        if (n > 85) {
          i = 85;
        }
        n = i;
        if (i < 70) {
          n = 70;
        }
        i = m + n;
        n = k + 1;
      }
      j += 1;
      k = n;
      m = i;
    }
    return m / k;
  }
  
  public static boolean checkUntradable(Team paramTeam, Player paramPlayer)
  {
    if ((position.equals("QB")) && (paramPlayer.getRatOvr() > 90)) {}
    while (((position.equals("RB")) && (paramPlayer.getRatOvr() > 92)) || ((position.equals("WR")) && (paramPlayer.getRatOvr() > 95))) {
      return true;
    }
    return false;
  }
  
  private static DraftPick findDraftPickClosestValue(ArrayList<DraftPick> paramArrayList1, int paramInt, boolean paramBoolean, ArrayList<DraftPick> paramArrayList2)
  {
    int n = 10000;
    int m = 0;
    int i = 0;
    if (i < paramArrayList1.size())
    {
      int j;
      int k;
      if (paramBoolean)
      {
        j = n;
        k = m;
        if (!paramArrayList2.contains(paramArrayList1.get(i)))
        {
          j = n;
          k = m;
          if (paramInt - ((DraftPick)paramArrayList1.get(i)).getTradeValue() > 0)
          {
            j = n;
            k = m;
            if (paramInt - ((DraftPick)paramArrayList1.get(i)).getTradeValue() <= n)
            {
              j = Math.abs(paramInt - ((DraftPick)paramArrayList1.get(i)).getTradeValue());
              k = i;
            }
          }
        }
      }
      for (;;)
      {
        i += 1;
        n = j;
        m = k;
        break;
        j = n;
        k = m;
        if (!paramArrayList2.contains(paramArrayList1.get(i)))
        {
          j = n;
          k = m;
          if (((DraftPick)paramArrayList1.get(i)).getTradeValue() - paramInt > 0)
          {
            j = n;
            k = m;
            if (((DraftPick)paramArrayList1.get(i)).getTradeValue() - paramInt <= n)
            {
              j = Math.abs(((DraftPick)paramArrayList1.get(i)).getTradeValue() - paramInt);
              k = i;
            }
          }
        }
      }
    }
    if (n != 10000) {
      return (DraftPick)paramArrayList1.get(m);
    }
    if (!paramBoolean)
    {
      paramArrayList1 = paramArrayList1.iterator();
      while (paramArrayList1.hasNext())
      {
        DraftPick localDraftPick = (DraftPick)paramArrayList1.next();
        if (!paramArrayList2.contains(localDraftPick)) {
          return localDraftPick;
        }
      }
      return null;
    }
    return null;
  }
  
  private static Player findPlayerDeepestPosition(Team paramTeam1, Team paramTeam2, ArrayList<Player> paramArrayList, int paramInt)
  {
    paramTeam2.sortPlayers();
    int m = calculateAvgTalentPosition(teamQBs, 1, false);
    int n = calculateAvgTalentPosition(teamRBs, 2, false);
    int i1 = calculateAvgTalentPosition(teamWRs, 3, false);
    int i2 = calculateAvgTalentPosition(teamOLs, 5, false);
    int i3 = calculateAvgTalentPosition(teamKs, 1, false);
    int i4 = calculateAvgTalentPosition(teamSs, 1, false);
    int i5 = calculateAvgTalentPosition(teamCBs, 3, false);
    int i6 = calculateAvgTalentPosition(teamDLs, 4, false);
    int i = calculateAvgTalentPosition(teamLBs, 3, false);
    Object localObject = new int[9];
    localObject[0] = m;
    localObject[1] = n;
    localObject[2] = i1;
    localObject[3] = i2;
    localObject[4] = i3;
    localObject[5] = i4;
    localObject[6] = i5;
    localObject[7] = i6;
    localObject[8] = i;
    Arrays.sort((int[])localObject);
    i = localObject.length - 1;
    int j = 1;
    ArrayList localArrayList = teamKs;
    while (i > 0)
    {
      int k;
      Player localPlayer;
      if (m == localObject[i])
      {
        k = j;
        if (j != 0)
        {
          localArrayList = teamQBs;
          k = 0;
        }
        localPlayer = findPlayerListClosestOvr(teamQBs, paramArrayList, paramInt);
        if (localPlayer != null)
        {
          paramTeam2.removePlayer(localPlayer);
          paramTeam1.addPlayer(localPlayer);
          return localPlayer;
        }
        i -= 1;
        j = k;
      }
      else if (n == localObject[i])
      {
        k = j;
        if (j != 0)
        {
          localArrayList = teamRBs;
          k = 0;
        }
        localPlayer = findPlayerListClosestOvr(teamRBs, paramArrayList, paramInt);
        if (localPlayer != null)
        {
          paramTeam2.removePlayer(localPlayer);
          paramTeam1.addPlayer(localPlayer);
          return localPlayer;
        }
        i -= 1;
        j = k;
      }
      else if (i1 == localObject[i])
      {
        k = j;
        if (j != 0)
        {
          localArrayList = teamWRs;
          k = 0;
        }
        localPlayer = findPlayerListClosestOvr(teamWRs, paramArrayList, paramInt);
        if (localPlayer != null)
        {
          paramTeam2.removePlayer(localPlayer);
          paramTeam1.addPlayer(localPlayer);
          return localPlayer;
        }
        i -= 1;
        j = k;
      }
      else if (i2 == localObject[i])
      {
        k = j;
        if (j != 0)
        {
          localArrayList = teamOLs;
          k = 0;
        }
        localPlayer = findPlayerListClosestOvr(teamOLs, paramArrayList, paramInt);
        if (localPlayer != null)
        {
          paramTeam2.removePlayer(localPlayer);
          paramTeam1.addPlayer(localPlayer);
          return localPlayer;
        }
        i -= 1;
        j = k;
      }
      else if (i3 == localObject[i])
      {
        k = j;
        if (j != 0)
        {
          localArrayList = teamKs;
          k = 0;
        }
        localPlayer = findPlayerListClosestOvr(teamKs, paramArrayList, paramInt);
        if (localPlayer != null)
        {
          paramTeam2.removePlayer(localPlayer);
          paramTeam1.addPlayer(localPlayer);
          return localPlayer;
        }
        i -= 1;
        j = k;
      }
      else if (i4 == localObject[i])
      {
        k = j;
        if (j != 0)
        {
          localArrayList = teamSs;
          k = 0;
        }
        localPlayer = findPlayerListClosestOvr(teamSs, paramArrayList, paramInt);
        if (localPlayer != null)
        {
          paramTeam2.removePlayer(localPlayer);
          paramTeam1.addPlayer(localPlayer);
          return localPlayer;
        }
        i -= 1;
        j = k;
      }
      else if (i5 == localObject[i])
      {
        k = j;
        if (j != 0)
        {
          localArrayList = teamCBs;
          k = 0;
        }
        localPlayer = findPlayerListClosestOvr(teamCBs, paramArrayList, paramInt);
        if (localPlayer != null)
        {
          paramTeam2.removePlayer(localPlayer);
          paramTeam1.addPlayer(localPlayer);
          return localPlayer;
        }
        i -= 1;
        j = k;
      }
      else if (i6 == localObject[i])
      {
        k = j;
        if (j != 0)
        {
          localArrayList = teamDLs;
          k = 0;
        }
        localPlayer = findPlayerListClosestOvr(teamDLs, paramArrayList, paramInt);
        if (localPlayer != null)
        {
          paramTeam2.removePlayer(localPlayer);
          paramTeam1.addPlayer(localPlayer);
          return localPlayer;
        }
        i -= 1;
        j = k;
      }
      else
      {
        k = j;
        if (j != 0)
        {
          localArrayList = teamLBs;
          k = 0;
        }
        localPlayer = findPlayerListClosestOvr(teamLBs, paramArrayList, paramInt);
        if (localPlayer != null)
        {
          paramTeam2.removePlayer(localPlayer);
          paramTeam1.addPlayer(localPlayer);
          return localPlayer;
        }
        i -= 1;
        j = k;
      }
    }
    paramInt = 0;
    while (paramInt < localArrayList.size())
    {
      localObject = (Player)localArrayList.get(localArrayList.size() - paramInt - 1);
      if (!paramArrayList.contains(localObject))
      {
        paramTeam2.removePlayer((Player)localObject);
        paramTeam1.addPlayer((Player)localObject);
        return (Player)localObject;
      }
      paramInt += 1;
    }
    return null;
  }
  
  private static Player findPlayerListClosestOvr(ArrayList<? extends Player> paramArrayList, ArrayList<Player> paramArrayList1, int paramInt)
  {
    int i = 0;
    if (paramArrayList.size() > 0)
    {
      Iterator localIterator = paramArrayList1.iterator();
      while (localIterator.hasNext()) {
        if (((Player)localIterator.next()).getPosition().equals(((Player)paramArrayList.get(0)).getPosition())) {
          i += 1;
        }
      }
    }
    return null;
    if (i >= 2) {
      return null;
    }
    int m = 100;
    int j = 0;
    i = 0;
    while (i < paramArrayList.size())
    {
      int n = m;
      int k = j;
      if (Math.abs(((Player)paramArrayList.get(i)).getRatOvr() - paramInt) <= m)
      {
        n = m;
        k = j;
        if (!((Player)paramArrayList.get(i)).isInjured())
        {
          n = Math.abs(((Player)paramArrayList.get(i)).getRatOvr() - paramInt);
          k = i;
        }
      }
      i += 1;
      m = n;
      j = k;
    }
    if ((paramArrayList.size() > 0) && (!paramArrayList1.contains(paramArrayList.get(j)))) {
      return (Player)paramArrayList.get(j);
    }
    return null;
  }
  
  private static Player findPlayerWeakestPosition(Team paramTeam1, Team paramTeam2, ArrayList<Player> paramArrayList, int paramInt)
  {
    paramTeam2.sortPlayers();
    int m = calculateAvgTalentPosition(teamQBs, 1, true);
    int n = calculateAvgTalentPosition(teamRBs, 2, true);
    int i1 = calculateAvgTalentPosition(teamWRs, 3, true);
    int i2 = calculateAvgTalentPosition(teamOLs, 5, true);
    int i3 = calculateAvgTalentPosition(teamKs, 1, true);
    int i4 = calculateAvgTalentPosition(teamSs, 1, true);
    int i5 = calculateAvgTalentPosition(teamCBs, 3, true);
    int i6 = calculateAvgTalentPosition(teamDLs, 4, true);
    int i = calculateAvgTalentPosition(teamLBs, 3, true);
    Object localObject = new int[9];
    localObject[0] = m;
    localObject[1] = n;
    localObject[2] = i1;
    localObject[3] = i2;
    localObject[4] = i3;
    localObject[5] = i4;
    localObject[6] = i5;
    localObject[7] = i6;
    localObject[8] = i;
    Arrays.sort((int[])localObject);
    i = 0;
    int j = 1;
    ArrayList localArrayList = teamOLs;
    while (i < localObject.length - 1)
    {
      int k;
      Player localPlayer;
      if (m == localObject[i])
      {
        k = j;
        if (j != 0)
        {
          localArrayList = teamQBs;
          k = 0;
        }
        localPlayer = findPlayerListClosestOvr(teamQBs, paramArrayList, paramInt);
        if (localPlayer != null)
        {
          paramTeam1.removePlayer(localPlayer);
          paramTeam2.addPlayer(localPlayer);
          return localPlayer;
        }
        i += 1;
        j = k;
      }
      else if (n == localObject[i])
      {
        k = j;
        if (j != 0)
        {
          localArrayList = teamRBs;
          k = 0;
        }
        localPlayer = findPlayerListClosestOvr(teamRBs, paramArrayList, paramInt);
        if (localPlayer != null)
        {
          paramTeam1.removePlayer(localPlayer);
          paramTeam2.addPlayer(localPlayer);
          return localPlayer;
        }
        i += 1;
        j = k;
      }
      else if (i1 == localObject[i])
      {
        k = j;
        if (j != 0)
        {
          localArrayList = teamWRs;
          k = 0;
        }
        localPlayer = findPlayerListClosestOvr(teamWRs, paramArrayList, paramInt);
        if (localPlayer != null)
        {
          paramTeam1.removePlayer(localPlayer);
          paramTeam2.addPlayer(localPlayer);
          return localPlayer;
        }
        i += 1;
        j = k;
      }
      else if (i2 == localObject[i])
      {
        k = j;
        if (j != 0)
        {
          localArrayList = teamOLs;
          k = 0;
        }
        localPlayer = findPlayerListClosestOvr(teamOLs, paramArrayList, paramInt);
        if (localPlayer != null)
        {
          paramTeam1.removePlayer(localPlayer);
          paramTeam2.addPlayer(localPlayer);
          return localPlayer;
        }
        i += 1;
        j = k;
      }
      else if (i3 == localObject[i])
      {
        k = j;
        if (j != 0)
        {
          localArrayList = teamKs;
          k = 0;
        }
        localPlayer = findPlayerListClosestOvr(teamKs, paramArrayList, paramInt);
        if (localPlayer != null)
        {
          paramTeam1.removePlayer(localPlayer);
          paramTeam2.addPlayer(localPlayer);
          return localPlayer;
        }
        i += 1;
        j = k;
      }
      else if (i4 == localObject[i])
      {
        k = j;
        if (j != 0)
        {
          localArrayList = teamSs;
          k = 0;
        }
        localPlayer = findPlayerListClosestOvr(teamSs, paramArrayList, paramInt);
        if (localPlayer != null)
        {
          paramTeam1.removePlayer(localPlayer);
          paramTeam2.addPlayer(localPlayer);
          return localPlayer;
        }
        i += 1;
        j = k;
      }
      else if (i5 == localObject[i])
      {
        k = j;
        if (j != 0)
        {
          localArrayList = teamCBs;
          k = 0;
        }
        localPlayer = findPlayerListClosestOvr(teamCBs, paramArrayList, paramInt);
        if (localPlayer != null)
        {
          paramTeam1.removePlayer(localPlayer);
          paramTeam2.addPlayer(localPlayer);
          return localPlayer;
        }
        i += 1;
        j = k;
      }
      else if (i6 == localObject[i])
      {
        k = j;
        if (j != 0)
        {
          localArrayList = teamDLs;
          k = 0;
        }
        localPlayer = findPlayerListClosestOvr(teamDLs, paramArrayList, paramInt);
        if (localPlayer != null)
        {
          paramTeam1.removePlayer(localPlayer);
          paramTeam2.addPlayer(localPlayer);
          return localPlayer;
        }
        i += 1;
        j = k;
      }
      else
      {
        k = j;
        if (j != 0)
        {
          localArrayList = teamLBs;
          k = 0;
        }
        localPlayer = findPlayerListClosestOvr(teamLBs, paramArrayList, paramInt);
        if (localPlayer != null)
        {
          paramTeam1.removePlayer(localPlayer);
          paramTeam2.addPlayer(localPlayer);
          return localPlayer;
        }
        i += 1;
        j = k;
      }
    }
    paramInt = 0;
    while (paramInt < localArrayList.size())
    {
      localObject = (Player)localArrayList.get(paramInt);
      if (!paramArrayList.contains(localObject))
      {
        paramTeam1.removePlayer((Player)localObject);
        paramTeam2.addPlayer((Player)localObject);
        return (Player)localObject;
      }
      paramInt += 1;
    }
    return null;
  }
  
  private static Player getGoodNonStarterList(ArrayList<? extends Player> paramArrayList, int paramInt)
  {
    while (paramInt < paramArrayList.size())
    {
      if (((Player)paramArrayList.get(paramInt)).getRatOvr() >= 80) {
        return (Player)paramArrayList.get(paramInt);
      }
      paramInt += 1;
    }
    return null;
  }
  
  public static int getTotalValue(ArrayList<TradePiece> paramArrayList)
  {
    int i = 0;
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext()) {
      i += ((TradePiece)paramArrayList.next()).getTradeValue();
    }
    return i;
  }
  
  public static Trade getTradeOfferFromAI(Team paramTeam1, Team paramTeam2, ArrayList<TradePiece> paramArrayList)
  {
    double d2 = wins / 25.0D + 1.2D;
    Trade localTrade = new Trade(paramTeam1, paramTeam2);
    Object localObject1 = new ArrayList();
    ((ArrayList)localObject1).addAll(paramArrayList);
    int i = 0;
    double d1 = 0.0D;
    int k = 7;
    int j = 0;
    paramArrayList = new ArrayList();
    Object localObject2 = ((ArrayList)localObject1).iterator();
    int i1;
    while (((Iterator)localObject2).hasNext())
    {
      localObject3 = (TradePiece)((Iterator)localObject2).next();
      if ((localObject3 instanceof DraftPick))
      {
        paramArrayList.add((DraftPick)localObject3);
        m = i + ((TradePiece)localObject3).getTradeValue();
        i1 = ((DraftPick)localObject3).getRound();
        n = j + (8 - i1);
        i = m;
        j = n;
        if (i1 < k)
        {
          k = i1;
          i = m;
          j = n;
        }
      }
      else
      {
        if (paramTeam1.getValueAdded((Player)localObject3) > 0) {
          i += ((TradePiece)localObject3).getTradeValue();
        }
        for (;;)
        {
          d1 += ((Player)localObject3).getContract().getMoneyPerYear();
          break;
          i += paramTeam1.getValueAdded((Player)localObject3);
        }
      }
    }
    localObject2 = new ArrayList();
    int n = 0;
    int m = 0;
    Object localObject3 = ((ArrayList)localObject1).iterator();
    Object localObject4;
    while (((Iterator)localObject3).hasNext())
    {
      localObject4 = (TradePiece)((Iterator)localObject3).next();
      if ((localObject4 instanceof Player))
      {
        localObject4 = (Player)localObject4;
        paramTeam1.addPlayer((Player)localObject4);
        paramTeam2.removePlayer((Player)localObject4);
        n += ((Player)localObject4).getRatOvr();
        ((ArrayList)localObject2).add(localObject4);
        m += 1;
      }
    }
    if (m > 0) {}
    Object localObject5;
    for (k = n / m + j / 2;; k = 81 - (int)(1.5D * k))
    {
      localObject4 = new ArrayList();
      localObject3 = new ArrayList();
      j = 0;
      while ((j < m) && (getTotalValue((ArrayList)localObject4) * d2 < i))
      {
        localObject5 = findPlayerDeepestPosition(paramTeam2, paramTeam1, (ArrayList)localObject2, k);
        if (localObject5 != null)
        {
          ((ArrayList)localObject4).add(localObject5);
          ((ArrayList)localObject3).add(localObject5);
        }
        j += 1;
      }
    }
    if (getTotalValue((ArrayList)localObject4) * d2 < i)
    {
      j = 0;
      m = 0;
      localObject5 = new ArrayList();
      if (getTotalValue((ArrayList)localObject4) * d2 < i)
      {
        Object localObject6;
        if (((j == 0) || (j == 1)) && (m < 6))
        {
          n = m;
          if (draftPicks.size() > 0)
          {
            localObject6 = findDraftPickClosestValue(draftPicks, (int)(i - getTotalValue((ArrayList)localObject4) * d2), true, (ArrayList)localObject5);
            if (localObject6 != null)
            {
              ((ArrayList)localObject5).add(localObject6);
              ((ArrayList)localObject4).add(localObject6);
            }
            n = m + 1;
          }
        }
        for (;;)
        {
          i1 = j + 1;
          m = n;
          j = i1;
          if (i1 <= 2) {
            break;
          }
          j = 0;
          m = n;
          break;
          localObject6 = findPlayerDeepestPosition(paramTeam2, paramTeam1, (ArrayList)localObject2, k);
          n = m;
          if (localObject6 != null)
          {
            ((ArrayList)localObject4).add(localObject6);
            ((ArrayList)localObject3).add(localObject6);
            n = m;
          }
        }
      }
    }
    if (getTotalValue((ArrayList)localObject4) * d2 > i)
    {
      i = 0;
      j = 0;
      if (getTotalValue((ArrayList)localObject4) * d2 > getTotalValue((ArrayList)localObject1))
      {
        if (((i == 0) || (i == 1)) && (j < 6))
        {
          m = j;
          if (draftPicks.size() > 0)
          {
            localObject5 = findDraftPickClosestValue(draftPicks, (int)(getTotalValue((ArrayList)localObject4) * d2 - getTotalValue((ArrayList)localObject1)), false, paramArrayList);
            if (localObject5 != null)
            {
              paramArrayList.add(localObject5);
              ((ArrayList)localObject1).add(localObject5);
            }
            m = j + 1;
          }
        }
        for (;;)
        {
          n = i + 1;
          j = m;
          i = n;
          if (n <= 2) {
            break;
          }
          i = 0;
          j = m;
          break;
          localObject5 = findPlayerWeakestPosition(paramTeam2, paramTeam1, (ArrayList)localObject3, k);
          m = j;
          if (localObject5 != null)
          {
            ((ArrayList)localObject1).add(localObject5);
            ((ArrayList)localObject2).add(localObject5);
            m = j;
          }
        }
      }
    }
    Collections.sort((List)localObject4, new TradePieceComparator());
    Collections.sort((List)localObject1, new TradePieceComparator());
    localTrade.setOffer(paramTeam1, (ArrayList)localObject4);
    localTrade.setOffer(paramTeam2, (ArrayList)localObject1);
    paramArrayList = ((ArrayList)localObject2).iterator();
    while (paramArrayList.hasNext())
    {
      localObject1 = (Player)paramArrayList.next();
      paramTeam1.removePlayer((Player)localObject1);
      paramTeam2.addPlayer((Player)localObject1);
    }
    paramArrayList = ((ArrayList)localObject3).iterator();
    while (paramArrayList.hasNext())
    {
      localObject1 = (Player)paramArrayList.next();
      paramTeam1.addPlayer((Player)localObject1);
      paramTeam2.removePlayer((Player)localObject1);
    }
    paramTeam2.sortPlayers();
    paramTeam1.sortPlayers();
    return localTrade;
  }
  
  public static Trade getTradeOfferFromUserTeam(Team paramTeam1, Team paramTeam2, ArrayList<TradePiece> paramArrayList)
  {
    double d2 = wins / 25.0D + 1.2D;
    Trade localTrade = new Trade(paramTeam1, paramTeam2);
    Object localObject1 = new ArrayList();
    ((ArrayList)localObject1).addAll(paramArrayList);
    int i = 0;
    double d1 = 0.0D;
    int k = 7;
    int j = 0;
    paramArrayList = ((ArrayList)localObject1).iterator();
    int i1;
    while (paramArrayList.hasNext())
    {
      localObject2 = (TradePiece)paramArrayList.next();
      if ((localObject2 instanceof DraftPick))
      {
        m = i + ((TradePiece)localObject2).getTradeValue();
        i1 = ((DraftPick)localObject2).getRound();
        n = j + (8 - i1);
        i = m;
        j = n;
        if (i1 < k)
        {
          k = i1;
          i = m;
          j = n;
        }
      }
      else
      {
        i += ((TradePiece)localObject2).getTradeValue();
        d1 += ((Player)localObject2).getContract().getMoneyPerYear();
      }
    }
    paramArrayList = new ArrayList();
    int n = 0;
    int m = 0;
    Object localObject2 = ((ArrayList)localObject1).iterator();
    Object localObject3;
    while (((Iterator)localObject2).hasNext())
    {
      localObject3 = (TradePiece)((Iterator)localObject2).next();
      if ((localObject3 instanceof Player))
      {
        localObject3 = (Player)localObject3;
        paramTeam2.removePlayer((Player)localObject3);
        n += ((Player)localObject3).getRatOvr();
        paramArrayList.add(localObject3);
        m += 1;
      }
    }
    if (m > 0) {}
    for (k = n / m + (int)(j / 1.5D);; k = 84 - (int)(1.5D * k))
    {
      localObject3 = new ArrayList();
      localObject2 = new ArrayList();
      j = 0;
      while ((j < m) && (getTotalValue((ArrayList)localObject3) < i * d2))
      {
        localObject4 = findPlayerWeakestPosition(paramTeam1, paramTeam2, paramArrayList, k);
        if (localObject4 != null)
        {
          ((ArrayList)localObject3).add(localObject4);
          ((ArrayList)localObject2).add(localObject4);
        }
        j += 1;
      }
    }
    j = 0;
    m = 0;
    Object localObject4 = new ArrayList();
    if (getTotalValue((ArrayList)localObject3) < i * d2)
    {
      Object localObject5;
      if (((j == 0) || (j == 1)) && (m < 6))
      {
        n = m;
        if (draftPicks.size() > 0)
        {
          localObject5 = findDraftPickClosestValue(draftPicks, (int)(i * d2 - getTotalValue((ArrayList)localObject3)), false, (ArrayList)localObject4);
          if (localObject5 != null)
          {
            ((ArrayList)localObject4).add(localObject5);
            ((ArrayList)localObject3).add(localObject5);
          }
          n = m + 1;
        }
      }
      for (;;)
      {
        i1 = j + 1;
        m = n;
        j = i1;
        if (i1 <= 2) {
          break;
        }
        j = 0;
        m = n;
        break;
        localObject5 = findPlayerWeakestPosition(paramTeam1, paramTeam2, paramArrayList, k);
        n = m;
        if (localObject5 != null)
        {
          ((ArrayList)localObject3).add(localObject5);
          ((ArrayList)localObject2).add(localObject5);
          n = m;
        }
      }
    }
    Collections.sort((List)localObject3, new TradePieceComparator());
    Collections.sort((List)localObject1, new TradePieceComparator());
    localTrade.setOffer(paramTeam1, (ArrayList)localObject3);
    localTrade.setOffer(paramTeam2, (ArrayList)localObject1);
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      localObject1 = (Player)paramArrayList.next();
      paramTeam2.addPlayer((Player)localObject1);
      paramTeam1.removePlayer((Player)localObject1);
    }
    paramArrayList = ((ArrayList)localObject2).iterator();
    while (paramArrayList.hasNext())
    {
      localObject1 = (Player)paramArrayList.next();
      paramTeam1.addPlayer((Player)localObject1);
      paramTeam2.removePlayer((Player)localObject1);
    }
    paramTeam2.sortPlayers();
    paramTeam1.sortPlayers();
    return localTrade;
  }
  
  private void transferDraftPick(DraftPick paramDraftPick, Team paramTeam1, Team paramTeam2)
  {
    draftPicks.remove(paramDraftPick);
    draftPicks.add(paramDraftPick);
    paramDraftPick.setTeamOwner(paramTeam2);
  }
  
  private void transferTradePieces(ArrayList<TradePiece> paramArrayList, Team paramTeam1, Team paramTeam2)
  {
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      Object localObject = (TradePiece)paramArrayList.next();
      if ((localObject instanceof Player))
      {
        localObject = (Player)localObject;
        paramTeam1.removePlayer((Player)localObject);
        paramTeam2.addPlayer((Player)localObject);
        ((Player)localObject).setTeam(paramTeam2);
        ((Player)localObject).addTeamPlayedFor(abbr, league.getYear());
      }
      else if ((localObject instanceof DraftPick))
      {
        transferDraftPick((DraftPick)localObject, paramTeam1, paramTeam2);
      }
    }
    Collections.sort(draftPicks, new DraftPickComparatorYearRound());
    Collections.sort(draftPicks, new DraftPickComparatorYearRound());
  }
  
  public void addPieceToOffer(Team paramTeam, TradePiece paramTradePiece)
  {
    if (paramTeam == aTeam)
    {
      aOffer.add(paramTradePiece);
      return;
    }
    bOffer.add(paramTradePiece);
  }
  
  public void completeTrade()
  {
    transferTradePieces(aOffer, aTeam, bTeam);
    transferTradePieces(bOffer, bTeam, aTeam);
    aTeam.tradingBlock.clear();
    bTeam.tradingBlock.clear();
    aTeam.signUDFAs();
    bTeam.signUDFAs();
    aTeam.sortPlayers();
    bTeam.sortPlayers();
  }
  
  public ArrayList<TradePiece> getAOffer()
  {
    return aOffer;
  }
  
  public Team getATeam()
  {
    return aTeam;
  }
  
  public ArrayList<TradePiece> getBOffer()
  {
    return bOffer;
  }
  
  public Team getBTeam()
  {
    return bTeam;
  }
  
  public void setOffer(Team paramTeam, ArrayList<TradePiece> paramArrayList)
  {
    if (paramTeam == aTeam)
    {
      aOffer = paramArrayList;
      return;
    }
    bOffer = paramArrayList;
  }
}

/* Location:
 * Qualified Name:     PFCpack.Trade
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */