package com.achijones.profootballcoach;

import PFCpack.League;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import java.util.ArrayList;

class MainActivity$31
  implements AdapterView.OnItemSelectedListener
{
  MainActivity$31(MainActivity paramMainActivity, NewsStoriesListArrayAdapter paramNewsStoriesListArrayAdapter) {}
  
  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    paramAdapterView = (ArrayList)this$0.simLeague.newsStories.get(paramInt);
    paramInt = 0;
    if (paramAdapterView.size() == 0)
    {
      paramInt = 1;
      paramAdapterView.add("No news stories.>I guess this week was a bit boring, huh?");
    }
    val$newsStoriesAdapter.clear();
    val$newsStoriesAdapter.addAll(paramAdapterView);
    val$newsStoriesAdapter.notifyDataSetChanged();
    if (paramInt != 0) {
      paramAdapterView.remove(0);
    }
  }
  
  public void onNothingSelected(AdapterView<?> paramAdapterView) {}
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MainActivity.31
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */