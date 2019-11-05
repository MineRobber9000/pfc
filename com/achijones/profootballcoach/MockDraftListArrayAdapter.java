package com.achijones.profootballcoach;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class MockDraftListArrayAdapter
  extends ArrayAdapter<String>
{
  private final Context context;
  private final String userTeamStrRep;
  private final String[] values;
  
  public MockDraftListArrayAdapter(Context paramContext, String[] paramArrayOfString, String paramString)
  {
    super(paramContext, 2130968609, paramArrayOfString);
    context = paramContext;
    values = paramArrayOfString;
    userTeamStrRep = paramString;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramView = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2130968609, paramViewGroup, false);
    paramViewGroup = values[paramInt].split(">");
    TextView localTextView1 = (TextView)paramView.findViewById(2131558552);
    localTextView1.setText(paramInt + 1 + ". " + paramViewGroup[0]);
    TextView localTextView2 = (TextView)paramView.findViewById(2131558553);
    localTextView2.setText(paramViewGroup[1]);
    ((Button)paramView.findViewById(2131558555)).setVisibility(8);
    ((Button)paramView.findViewById(2131558556)).setVisibility(8);
    if (paramViewGroup[1].equals(userTeamStrRep))
    {
      localTextView1.setTextColor(Color.parseColor("#1A75FF"));
      localTextView2.setTextColor(Color.parseColor("#1A75FF"));
    }
    return paramView;
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MockDraftListArrayAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */