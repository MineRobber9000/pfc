package com.achijones.profootballcoach;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class OnlineTeamStatsListArrayAdapter
  extends ArrayAdapter<String>
{
  private final Context context;
  private final String[] values;
  
  public OnlineTeamStatsListArrayAdapter(Context paramContext, String[] paramArrayOfString)
  {
    super(paramContext, 2130968667, paramArrayOfString);
    context = paramContext;
    values = paramArrayOfString;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramView = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2130968667, paramViewGroup, false);
    paramViewGroup = (TextView)paramView.findViewById(2131558695);
    TextView localTextView1 = (TextView)paramView.findViewById(2131558696);
    TextView localTextView2 = (TextView)paramView.findViewById(2131558697);
    Button localButton = (Button)paramView.findViewById(2131558698);
    String[] arrayOfString = values[paramInt].split(",");
    paramViewGroup.setText(arrayOfString[0]);
    localTextView1.setText(arrayOfString[1]);
    localTextView2.setText(arrayOfString[2]);
    localButton.setVisibility(8);
    return paramView;
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.OnlineTeamStatsListArrayAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */