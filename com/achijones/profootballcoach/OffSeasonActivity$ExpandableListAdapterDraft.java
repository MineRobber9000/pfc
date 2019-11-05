package com.achijones.profootballcoach;

import PFCpack.Contract;
import PFCpack.Player;
import PFCpack.Team;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OffSeasonActivity$ExpandableListAdapterDraft
  extends BaseExpandableListAdapter
{
  private Activity context;
  private boolean isDraftOrFA;
  private ArrayList<Player> listCurrPlayers;
  private Map<Player, List<String>> listCurrPlayersInfo;
  
  public OffSeasonActivity$ExpandableListAdapterDraft(Activity paramActivity, ArrayList<Player> paramArrayList, Map<Player, List<String>> paramMap, boolean paramBoolean)
  {
    context = paramArrayList;
    listCurrPlayers = paramMap;
    listCurrPlayersInfo = paramBoolean;
    boolean bool;
    isDraftOrFA = bool;
  }
  
  public String getChild(int paramInt1, int paramInt2)
  {
    return (String)((List)listCurrPlayersInfo.get(listCurrPlayers.get(paramInt1))).get(paramInt2);
  }
  
  public long getChildId(int paramInt1, int paramInt2)
  {
    return paramInt2;
  }
  
  public View getChildView(int paramInt1, int paramInt2, boolean paramBoolean, View paramView, ViewGroup paramViewGroup)
  {
    Object localObject1 = getChild(paramInt1, paramInt2);
    final Player localPlayer = getGroup(paramInt1);
    Object localObject2 = context.getLayoutInflater();
    paramViewGroup = paramView;
    if (paramView == null) {
      paramViewGroup = ((LayoutInflater)localObject2).inflate(2130968610, null);
    }
    paramView = (TextView)paramViewGroup.findViewById(2131558557);
    localObject2 = (TextView)paramViewGroup.findViewById(2131558559);
    TextView localTextView1 = (TextView)paramViewGroup.findViewById(2131558561);
    TextView localTextView2 = (TextView)paramViewGroup.findViewById(2131558558);
    TextView localTextView3 = (TextView)paramViewGroup.findViewById(2131558560);
    TextView localTextView4 = (TextView)paramViewGroup.findViewById(2131558562);
    TextView localTextView5 = (TextView)paramViewGroup.findViewById(2131558563);
    paramView.setText("Potential: " + Player.getLetterGrade(localPlayer.getRatPot()));
    ((TextView)localObject2).setText("Durability: " + Player.getLetterGrade(localPlayer.getRatDur()));
    localObject1 = ((String)localObject1).split(">");
    localTextView1.setText(localObject1[0]);
    localTextView2.setText(localObject1[1]);
    localTextView3.setText(localObject1[2]);
    localTextView4.setText(localObject1[3]);
    if (localObject1.length > 4) {
      localTextView5.setText(localObject1[4]);
    }
    for (;;)
    {
      localObject1 = new TextView[6];
      localObject1[0] = paramView;
      localObject1[1] = localObject2;
      localObject1[2] = localTextView1;
      localObject1[3] = localTextView2;
      localObject1[4] = localTextView3;
      localObject1[5] = localTextView4;
      paramInt2 = localObject1.length;
      paramInt1 = 0;
      while (paramInt1 < paramInt2)
      {
        paramView = localObject1[paramInt1];
        OffSeasonActivity.access$1800(this$0, paramView);
        paramInt1 += 1;
      }
      localTextView5.setVisibility(8);
    }
    paramView = (Button)paramViewGroup.findViewById(2131558565);
    if ((OffSeasonActivity.access$600(this$0) != 2) || (!isDraftOrFA))
    {
      paramView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = new AlertDialog.Builder(this$0);
          Object localObject = localPlayer.getDetailStatsOffseason();
          ((ArrayList)localObject).add(0, "[B]" + localPlayer.getAgeOvrPot_Str());
          localObject = (String[])((ArrayList)localObject).toArray(new String[((ArrayList)localObject).size()]);
          paramAnonymousView.setAdapter(new PlayerStatsListArrayAdapter(this$0, (String[])localObject), null).setTitle(localPlayer.getPosition() + " " + localPlayer.getName()).setPositiveButton("OK", new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
          });
          paramAnonymousView.create().show();
        }
      });
      if (!isDraftOrFA) {
        break label509;
      }
      localObject1 = (Button)paramViewGroup.findViewById(2131558566);
      if (OffSeasonActivity.access$600(this$0) != 2) {
        break label499;
      }
    }
    label499:
    for (paramView = Contract.getContractDraft(localPlayer, this$0.draftPickNum);; paramView = Contract.getContractFA(localPlayer))
    {
      ((Button)localObject1).setText("Sign for " + paramView.getYearsLeft() + " yrs, $" + paramView.getMoneyPerYear() + "mil/yr");
      ((Button)localObject1).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if ((OffSeasonActivity.access$600(this$0) == 2) && (this$0.draftRound <= 7))
          {
            this$0.draftPlayerUserTeam(localPlayer);
            return;
          }
          if (OffSeasonActivity.access$600(this$0) == 0)
          {
            if ((this$0.userTeam.getSalaryCapRoom() >= Contract.getContractFA(localPlayer).getMoneyPerYear()) || (Contract.getContractFA(localPlayer).getMoneyPerYear() <= 0.5D))
            {
              this$0.signTeamFAPlayerUserTeam(localPlayer);
              return;
            }
            Toast.makeText(this$0, "Not enough cap room!", 0).show();
            return;
          }
          if ((this$0.userTeam.getSalaryCapRoom() >= Contract.getContractFA(localPlayer).getMoneyPerYear()) || (Contract.getContractFA(localPlayer).getMoneyPerYear() <= 0.5D))
          {
            this$0.signFAPlayerUserTeam(localPlayer);
            return;
          }
          Toast.makeText(this$0, "Not enough cap room!", 0).show();
        }
      });
      return paramViewGroup;
      paramView.setVisibility(8);
      break;
    }
    label509:
    ((Button)paramViewGroup.findViewById(2131558566)).setVisibility(8);
    return paramViewGroup;
  }
  
  public int getChildrenCount(int paramInt)
  {
    if (listCurrPlayers.get(paramInt) != null) {
      return ((List)listCurrPlayersInfo.get(listCurrPlayers.get(paramInt))).size();
    }
    return 0;
  }
  
  public Player getGroup(int paramInt)
  {
    return (Player)listCurrPlayers.get(paramInt);
  }
  
  public int getGroupCount()
  {
    return listCurrPlayers.size();
  }
  
  public long getGroupId(int paramInt)
  {
    return paramInt;
  }
  
  public View getGroupView(int paramInt, boolean paramBoolean, View paramView, ViewGroup paramViewGroup)
  {
    if (listCurrPlayers.size() > 0)
    {
      Object localObject1;
      Object localObject2;
      TextView localTextView;
      if ((isDraftOrFA) && (listCurrPlayers.get(paramInt) != null))
      {
        paramViewGroup = ((Player)listCurrPlayers.get(paramInt)).getPosNameYrOvrPotImprove_Str().split(">");
        localObject1 = paramViewGroup[0];
        localObject2 = paramViewGroup[1];
        paramViewGroup = paramView;
        if (paramView == null) {
          paramViewGroup = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2130968634, null);
        }
        localTextView = (TextView)paramViewGroup.findViewById(2131558634);
        localTextView.setTypeface(null, 1);
        localTextView.setText((CharSequence)localObject1);
        localObject1 = (TextView)paramViewGroup.findViewById(2131558636);
        ((TextView)localObject1).setTypeface(null, 1);
        ((TextView)localObject1).setText((CharSequence)localObject2);
        paramView = (TextView)paramViewGroup.findViewById(2131558635);
        paramView.setTypeface(null, 1);
        paramView.setText("");
        paramView = paramViewGroup;
        if (!isDraftOrFA)
        {
          paramView = paramViewGroup;
          if (listCurrPlayers.get(paramInt)).isStarter)
          {
            localTextView.setTextColor(Color.parseColor("#1A75FF"));
            ((TextView)localObject1).setTextColor(Color.parseColor("#1A75FF"));
            paramView = paramViewGroup;
          }
        }
      }
      for (;;)
      {
        return paramView;
        paramViewGroup = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2130968634, null);
        if (paramInt == 0)
        {
          paramView = (TextView)paramViewGroup.findViewById(2131558635);
          paramView.setTypeface(null, 1);
          paramView.setText("Quarterback (1 starter)");
          paramView = paramViewGroup;
        }
        else if (paramInt == OffSeasonActivity.access$1200(this$0)[0])
        {
          paramView = (TextView)paramViewGroup.findViewById(2131558635);
          paramView.setTypeface(null, 1);
          paramView.setText("Runningback (2 starters)");
          paramView = paramViewGroup;
        }
        else if (paramInt == OffSeasonActivity.access$1200(this$0)[1])
        {
          paramView = (TextView)paramViewGroup.findViewById(2131558635);
          paramView.setTypeface(null, 1);
          paramView.setText("Wide Receiver (3 starters)");
          paramView = paramViewGroup;
        }
        else if (paramInt == OffSeasonActivity.access$1200(this$0)[2])
        {
          paramView = (TextView)paramViewGroup.findViewById(2131558635);
          paramView.setTypeface(null, 1);
          paramView.setText("Offensive Line (5 starters)");
          paramView = paramViewGroup;
        }
        else if (paramInt == OffSeasonActivity.access$1200(this$0)[3])
        {
          paramView = (TextView)paramViewGroup.findViewById(2131558635);
          paramView.setTypeface(null, 1);
          paramView.setText("Kicker (1 starter)");
          paramView = paramViewGroup;
        }
        else if (paramInt == OffSeasonActivity.access$1200(this$0)[4])
        {
          paramView = (TextView)paramViewGroup.findViewById(2131558635);
          paramView.setTypeface(null, 1);
          paramView.setText("Safety (1 starter)");
          paramView = paramViewGroup;
        }
        else if (paramInt == OffSeasonActivity.access$1200(this$0)[5])
        {
          paramView = (TextView)paramViewGroup.findViewById(2131558635);
          paramView.setTypeface(null, 1);
          paramView.setText("Cornerback (3 starters)");
          paramView = paramViewGroup;
        }
        else if (paramInt == OffSeasonActivity.access$1200(this$0)[6])
        {
          paramView = (TextView)paramViewGroup.findViewById(2131558635);
          paramView.setTypeface(null, 1);
          paramView.setText("Defensive Line (4 starters)");
          paramView = paramViewGroup;
        }
        else if (paramInt == OffSeasonActivity.access$1200(this$0)[7])
        {
          paramView = (TextView)paramViewGroup.findViewById(2131558635);
          paramView.setTypeface(null, 1);
          paramView.setText("Linebacker (3 starters)");
          paramView = paramViewGroup;
        }
        else
        {
          paramView = paramViewGroup;
          if (listCurrPlayers.get(paramInt) != null)
          {
            localObject1 = ((Player)listCurrPlayers.get(paramInt)).getPosNameYrOvrPotImprove_Str().split(">");
            paramView = localObject1[0];
            localTextView = localObject1[1];
            localObject1 = (TextView)paramViewGroup.findViewById(2131558634);
            ((TextView)localObject1).setTypeface(null, 1);
            ((TextView)localObject1).setText(paramView);
            localObject2 = (TextView)paramViewGroup.findViewById(2131558636);
            ((TextView)localObject2).setTypeface(null, 1);
            ((TextView)localObject2).setText(localTextView);
            paramView = (TextView)paramViewGroup.findViewById(2131558635);
            paramView.setTypeface(null, 1);
            paramView.setText("");
            paramView = paramViewGroup;
            if (listCurrPlayers.get(paramInt)).isStarter)
            {
              ((TextView)localObject1).setTextColor(Color.parseColor("#1A75FF"));
              ((TextView)localObject2).setTextColor(Color.parseColor("#1A75FF"));
              paramView = paramViewGroup;
            }
          }
        }
      }
    }
    return null;
  }
  
  public boolean hasStableIds()
  {
    return true;
  }
  
  public boolean isChildSelectable(int paramInt1, int paramInt2)
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.OffSeasonActivity.ExpandableListAdapterDraft
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */