package com.achijones.profootballcoach;

import PFCpack.Contract;
import PFCpack.Player;
import PFCpack.Team;
import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExpandableListAdapterPlayerFinder
  extends BaseExpandableListAdapter
{
  private Activity context;
  private AlertDialog dialog;
  private ArrayList<Player> listCurrPlayers;
  private Map<Player, List<String>> listCurrPlayersInfo;
  private MainActivity mainAct;
  
  public ExpandableListAdapterPlayerFinder(Activity paramActivity, MainActivity paramMainActivity, ArrayList<Player> paramArrayList, AlertDialog paramAlertDialog)
  {
    context = paramActivity;
    mainAct = paramMainActivity;
    listCurrPlayers = paramArrayList;
    setPlayerInfoMap();
    dialog = paramAlertDialog;
  }
  
  private void colorizeRatings(TextView paramTextView)
  {
    Object localObject = paramTextView.getText().toString().split(" ");
    if (localObject.length > 0)
    {
      localObject = localObject[(localObject.length - 1)];
      if ((!((String)localObject).equals("A")) && (!((String)localObject).equals("A+"))) {
        break label55;
      }
      paramTextView.setTextColor(Color.parseColor("#006600"));
    }
    label55:
    do
    {
      return;
      if ((((String)localObject).equals("B")) || (((String)localObject).equals("B+")))
      {
        paramTextView.setTextColor(Color.parseColor("#00b300"));
        return;
      }
      if ((((String)localObject).equals("C")) || (((String)localObject).equals("C+")))
      {
        paramTextView.setTextColor(Color.parseColor("#e68a00"));
        return;
      }
      if ((((String)localObject).equals("D")) || (((String)localObject).equals("D+")))
      {
        paramTextView.setTextColor(Color.parseColor("#cc3300"));
        return;
      }
    } while ((!((String)localObject).equals("F")) && (!((String)localObject).equals("F+")));
    paramTextView.setTextColor(Color.parseColor("#990000"));
  }
  
  private String getPlayerDetails(Player paramPlayer)
  {
    String str1 = paramPlayer.getPosition();
    String str2 = Player.getLetterGrade(paramPlayer.getRatFootIQ());
    paramPlayer = paramPlayer.getRatings();
    if (str1.equals("QB")) {
      return "Football IQ: " + str2 + ">Strength: " + Player.getLetterGrade(paramPlayer[0]) + ">Accuracy: " + Player.getLetterGrade(paramPlayer[1]) + ">Evasion: " + Player.getLetterGrade(paramPlayer[2]);
    }
    if (str1.equals("RB")) {
      return "Football IQ: " + str2 + ">Power: " + Player.getLetterGrade(paramPlayer[0]) + ">Speed: " + Player.getLetterGrade(paramPlayer[1]) + ">Evasion: " + Player.getLetterGrade(paramPlayer[2]);
    }
    if (str1.equals("WR")) {
      return "Football IQ: " + str2 + ">Catching: " + Player.getLetterGrade(paramPlayer[0]) + ">Speed: " + Player.getLetterGrade(paramPlayer[1]) + ">Evasion: " + Player.getLetterGrade(paramPlayer[2]);
    }
    if (str1.equals("OL")) {
      return "Football IQ: " + str2 + ">Strength: " + Player.getLetterGrade(paramPlayer[0]) + ">Rush Blk: " + Player.getLetterGrade(paramPlayer[1]) + ">Pass Blk: " + Player.getLetterGrade(paramPlayer[2]);
    }
    if (str1.equals("K")) {
      return "Football IQ: " + str2 + ">Kick Power: " + Player.getLetterGrade(paramPlayer[0]) + ">Accuracy: " + Player.getLetterGrade(paramPlayer[1]) + ">Clumsiness: " + Player.getLetterGrade(paramPlayer[2]);
    }
    if (str1.equals("S")) {
      return "Football IQ: " + str2 + ">Coverage: " + Player.getLetterGrade(paramPlayer[0]) + ">Speed: " + Player.getLetterGrade(paramPlayer[1]) + ">Tackling: " + Player.getLetterGrade(paramPlayer[2]);
    }
    if (str1.equals("CB")) {
      return "Football IQ: " + str2 + ">Coverage: " + Player.getLetterGrade(paramPlayer[0]) + ">Speed: " + Player.getLetterGrade(paramPlayer[1]) + ">Tackling: " + Player.getLetterGrade(paramPlayer[2]);
    }
    if (str1.equals("DL")) {
      return "Football IQ: " + str2 + ">Strength: " + Player.getLetterGrade(paramPlayer[0]) + ">Run Stop: " + Player.getLetterGrade(paramPlayer[1]) + ">Pass Press: " + Player.getLetterGrade(paramPlayer[2]);
    }
    if (str1.equals("LB")) {
      return "Football IQ: " + str2 + ">Tackling: " + Player.getLetterGrade(paramPlayer[0]) + ">Run Stop: " + Player.getLetterGrade(paramPlayer[1]) + ">Pass Cover: " + Player.getLetterGrade(paramPlayer[2]);
    }
    return "ERROR";
  }
  
  private void setPlayerInfoMap()
  {
    listCurrPlayersInfo = new LinkedHashMap();
    Iterator localIterator = listCurrPlayers.iterator();
    while (localIterator.hasNext())
    {
      Player localPlayer = (Player)localIterator.next();
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(getPlayerDetails(localPlayer));
      listCurrPlayersInfo.put(localPlayer, localArrayList);
    }
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
    localTextView5.setText("Contract: " + localPlayer.getContract().toString());
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
      colorizeRatings(localObject1[paramInt1]);
      paramInt1 += 1;
    }
    ((Button)paramViewGroup.findViewById(2131558565)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        mainAct.examinePlayer(localPlayer);
      }
    });
    paramView = (Button)paramViewGroup.findViewById(2131558566);
    paramView.setText("View " + getTeamabbr + " Roster");
    paramView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        mainAct.examineTeamRoster(localPlayergetTeamname);
        dialog.dismiss();
      }
    });
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
    paramViewGroup = ((Player)listCurrPlayers.get(paramInt)).getPosNameYrOvrPot_Split().split(">");
    String str = paramViewGroup[0];
    CharSequence localCharSequence = paramViewGroup[1];
    paramViewGroup = paramView;
    if (paramView == null) {
      paramViewGroup = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2130968634, null);
    }
    paramView = (TextView)paramViewGroup.findViewById(2131558634);
    paramView.setTypeface(null, 1);
    paramView.setText(listCurrPlayers.get(paramInt)).getTeam().abbr + " " + str);
    paramView = (TextView)paramViewGroup.findViewById(2131558636);
    paramView.setTypeface(null, 1);
    paramView.setText(localCharSequence);
    paramView = (TextView)paramViewGroup.findViewById(2131558635);
    paramView.setTypeface(null, 1);
    paramView.setText("");
    return paramViewGroup;
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
 * Qualified Name:     com.achijones.profootballcoach.ExpandableListAdapterPlayerFinder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */