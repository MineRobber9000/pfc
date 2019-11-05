package com.achijones.profootballcoach;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;
import java.util.Map;

public class OnlineExpandableListAdapterPlayerStats
  extends BaseExpandableListAdapter
{
  private Activity context;
  private List<String> players;
  private Map<String, List<String>> playersInfo;
  
  public OnlineExpandableListAdapterPlayerStats(Activity paramActivity, List<String> paramList, Map<String, List<String>> paramMap)
  {
    context = paramActivity;
    players = paramList;
    playersInfo = paramMap;
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
  
  public View getChildView(int paramInt1, int paramInt2, boolean paramBoolean, View paramView, ViewGroup paramViewGroup)
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
    if (arrayOfString.length == 2)
    {
      ((TextView)localObject).setText(arrayOfString[0]);
      localTextView1.setText(arrayOfString[1]);
      colorizeRatings((TextView)localObject, arrayOfString[0]);
      colorizeRatings(localTextView1, arrayOfString[1]);
    }
    for (;;)
    {
      paramViewGroup = (Button)paramView.findViewById(2131558555);
      localObject = (Button)paramView.findViewById(2131558556);
      paramViewGroup.setVisibility(8);
      ((Button)localObject).setVisibility(8);
      return paramView;
      localTextView2.setText(paramViewGroup);
      ((TextView)localObject).setVisibility(8);
      localTextView1.setVisibility(8);
    }
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
    TextView localTextView = (TextView)paramView.findViewById(2131558632);
    localTextView.setText(paramViewGroup[0]);
    localTextView.setTypeface(null, 1);
    localTextView = (TextView)paramView.findViewById(2131558633);
    localTextView.setText(paramViewGroup[1]);
    localTextView.setTypeface(null, 1);
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
 * Qualified Name:     com.achijones.profootballcoach.OnlineExpandableListAdapterPlayerStats
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */