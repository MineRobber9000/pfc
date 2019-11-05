package com.achijones.profootballcoach;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class TeamStatsListArrayAdapter
  extends ArrayAdapter<String>
{
  private final Context context;
  private final MainActivity mainAct;
  private final String[] values;
  
  public TeamStatsListArrayAdapter(Context paramContext, MainActivity paramMainActivity, String[] paramArrayOfString)
  {
    super(paramContext, 2130968667, paramArrayOfString);
    context = paramContext;
    values = paramArrayOfString;
    mainAct = paramMainActivity;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramView = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2130968667, paramViewGroup, false);
    paramViewGroup = (TextView)paramView.findViewById(2131558695);
    TextView localTextView1 = (TextView)paramView.findViewById(2131558696);
    TextView localTextView2 = (TextView)paramView.findViewById(2131558697);
    Button localButton = (Button)paramView.findViewById(2131558698);
    if (!values[paramInt].equals("PROFILE_BUTTON"))
    {
      String[] arrayOfString = values[paramInt].split(",");
      paramViewGroup.setText(arrayOfString[0]);
      localTextView1.setText(arrayOfString[1]);
      localTextView2.setText(arrayOfString[2]);
      localButton.setVisibility(8);
      return paramView;
    }
    paramViewGroup.setVisibility(8);
    localTextView1.setVisibility(8);
    localTextView2.setVisibility(8);
    localButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        mainAct.showCurrTeamProfile();
      }
    });
    return paramView;
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.TeamStatsListArrayAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */