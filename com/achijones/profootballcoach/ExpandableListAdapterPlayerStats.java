package com.achijones.profootballcoach;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;
import java.util.Map;

public class ExpandableListAdapterPlayerStats
  extends BaseExpandableListAdapter
{
  private Activity context;
  private MainActivity mainAct;
  private List<String> players;
  private Map<String, List<String>> playersInfo;
  
  public ExpandableListAdapterPlayerStats(Activity paramActivity, MainActivity paramMainActivity, List<String> paramList, Map<String, List<String>> paramMap)
  {
    context = paramActivity;
    players = paramList;
    playersInfo = paramMap;
    mainAct = paramMainActivity;
  }
  
  private void colorizeRatings(TextView paramTextView, String paramString)
  {
    String[] arrayOfString = paramString.split(" ");
    if ((arrayOfString.length > 0) && (paramString.split(",").length == 1))
    {
      paramString = arrayOfString[(arrayOfString.length - 1)];
      if ((!paramString.equals("A")) && (!paramString.equals("A+"))) {
        break label58;
      }
      paramTextView.setTextColor(Color.parseColor("#006600"));
    }
    label58:
    do
    {
      return;
      if ((paramString.equals("B")) || (paramString.equals("B+")))
      {
        paramTextView.setTextColor(Color.parseColor("#00b300"));
        return;
      }
      if ((paramString.equals("C")) || (paramString.equals("C+")))
      {
        paramTextView.setTextColor(Color.parseColor("#e68a00"));
        return;
      }
      if ((paramString.equals("D")) || (paramString.equals("D+")))
      {
        paramTextView.setTextColor(Color.parseColor("#cc3300"));
        return;
      }
    } while ((!paramString.equals("F")) && (!paramString.equals("F+")));
    paramTextView.setTextColor(Color.parseColor("#990000"));
  }
  
  public String getChild(int paramInt1, int paramInt2)
  {
    return (String)((List)playersInfo.get(players.get(paramInt1))).get(paramInt2);
  }
  
  public long getChildId(int paramInt1, int paramInt2)
  {
    return paramInt2;
  }
  
  public View getChildView(final int paramInt1, int paramInt2, boolean paramBoolean, View paramView, ViewGroup paramViewGroup)
  {
    paramViewGroup = getChild(paramInt1, paramInt2);
    paramView = context.getLayoutInflater().inflate(2130968609, null);
    Object localObject = (TextView)paramView.findViewById(2131558552);
    TextView localTextView1 = (TextView)paramView.findViewById(2131558553);
    TextView localTextView2 = (TextView)paramView.findViewById(2131558554);
    localTextView2.setText("");
    ((TextView)localObject).setText("");
    localTextView1.setText("");
    String[] arrayOfString = paramViewGroup.split(">");
    if (arrayOfString.length == 2) {
      if (paramViewGroup.substring(0, 3).equals("[I]"))
      {
        ((TextView)localObject).setText(arrayOfString[0].substring(3));
        localTextView1.setText(arrayOfString[1]);
        ((TextView)localObject).setTextColor(-65536);
        localTextView1.setTextColor(-65536);
      }
    }
    for (;;)
    {
      paramViewGroup = (Button)paramView.findViewById(2131558555);
      paramViewGroup.setText("View Profile");
      paramViewGroup.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          mainAct.examinePlayer((String)players.get(paramInt1));
        }
      });
      localObject = (Button)paramView.findViewById(2131558556);
      ((Button)localObject).setText("Trade");
      ((Button)localObject).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          mainAct.addTradePlayer((String)players.get(paramInt1));
        }
      });
      if ((paramBoolean) && (!((String)players.get(paramInt1)).equals("BENCH > BENCH")) && (!((String)players.get(paramInt1)).equals("DRAFT PICKS > DRAFT PICKS"))) {
        break;
      }
      paramViewGroup.setVisibility(8);
      ((Button)localObject).setVisibility(8);
      return paramView;
      ((TextView)localObject).setText(arrayOfString[0]);
      localTextView1.setText(arrayOfString[1]);
      colorizeRatings((TextView)localObject, arrayOfString[0]);
      colorizeRatings(localTextView1, arrayOfString[1]);
      continue;
      localTextView2.setText(paramViewGroup);
      ((TextView)localObject).setVisibility(8);
      localTextView1.setVisibility(8);
    }
    paramViewGroup.setVisibility(0);
    ((Button)localObject).setVisibility(0);
    return paramView;
  }
  
  public int getChildrenCount(int paramInt)
  {
    return ((List)playersInfo.get(players.get(paramInt))).size();
  }
  
  public String getGroup(int paramInt)
  {
    return (String)players.get(paramInt);
  }
  
  public int getGroupCount()
  {
    return players.size();
  }
  
  public long getGroupId(int paramInt)
  {
    return paramInt;
  }
  
  public View getGroupView(int paramInt, boolean paramBoolean, View paramView, ViewGroup paramViewGroup)
  {
    paramView = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2130968633, null);
    paramViewGroup = getGroup(paramInt).split(">");
    TextView localTextView1 = (TextView)paramView.findViewById(2131558632);
    localTextView1.setText(paramViewGroup[0]);
    localTextView1.setTypeface(null, 1);
    TextView localTextView2 = (TextView)paramView.findViewById(2131558633);
    localTextView2.setText(paramViewGroup[1]);
    localTextView2.setTypeface(null, 1);
    if ((getGroup(paramInt).equals("BENCH > BENCH")) || (getGroup(paramInt).equals("DRAFT PICKS > DRAFT PICKS")))
    {
      paramInt = 0;
      if (paramInt != 2) {
        break label156;
      }
      localTextView1.setTextColor(Color.parseColor("#FF9933"));
      localTextView2.setTextColor(Color.parseColor("#FF9933"));
    }
    label156:
    while (paramInt != 1)
    {
      return paramView;
      paramInt = mainAct.checkAwardPlayer(getGroup(paramInt));
      break;
    }
    localTextView1.setTextColor(Color.parseColor("#1A75FF"));
    localTextView2.setTextColor(Color.parseColor("#1A75FF"));
    return paramView;
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
 * Qualified Name:     com.achijones.profootballcoach.ExpandableListAdapterPlayerStats
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */