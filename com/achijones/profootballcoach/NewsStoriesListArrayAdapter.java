package com.achijones.profootballcoach;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class NewsStoriesListArrayAdapter
  extends ArrayAdapter<String>
{
  private final Context context;
  private final ArrayList<String> values;
  
  public NewsStoriesListArrayAdapter(Context paramContext, ArrayList<String> paramArrayList)
  {
    super(paramContext, 2130968639, paramArrayList);
    context = paramContext;
    values = paramArrayList;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramView = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2130968639, paramViewGroup, false);
    paramViewGroup = (TextView)paramView.findViewById(2131558648);
    TextView localTextView = (TextView)paramView.findViewById(2131558649);
    String[] arrayOfString = ((String)values.get(paramInt)).split(">");
    paramViewGroup.setText(arrayOfString[0]);
    localTextView.setText(arrayOfString[1]);
    return paramView;
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.NewsStoriesListArrayAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */