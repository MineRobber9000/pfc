package com.achijones.profootballcoach;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class TeamRankingsListArrayAdapter
  extends ArrayAdapter<String>
{
  private final Context context;
  private final String userTeamStrRep;
  private final ArrayList<String> values;
  
  public TeamRankingsListArrayAdapter(Context paramContext, ArrayList<String> paramArrayList, String paramString)
  {
    super(paramContext, 2130968666, paramArrayList);
    context = paramContext;
    values = paramArrayList;
    userTeamStrRep = paramString;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramView = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2130968666, paramViewGroup, false);
    paramViewGroup = (TextView)paramView.findViewById(2131558691);
    TextView localTextView1 = (TextView)paramView.findViewById(2131558693);
    TextView localTextView2 = (TextView)paramView.findViewById(2131558694);
    String[] arrayOfString = ((String)values.get(paramInt)).split(",");
    paramViewGroup.setText(arrayOfString[0]);
    localTextView1.setText(arrayOfString[1]);
    localTextView2.setText(arrayOfString[2]);
    if (arrayOfString[1].equals(userTeamStrRep))
    {
      paramViewGroup.setTypeface(paramViewGroup.getTypeface(), 1);
      paramViewGroup.setTextColor(Color.parseColor("#1A75FF"));
      localTextView1.setTypeface(localTextView1.getTypeface(), 1);
      localTextView1.setTextColor(Color.parseColor("#1A75FF"));
      localTextView2.setTypeface(localTextView2.getTypeface(), 1);
      localTextView2.setTextColor(Color.parseColor("#1A75FF"));
    }
    return paramView;
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.TeamRankingsListArrayAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */