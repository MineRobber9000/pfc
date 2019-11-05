package com.achijones.profootballcoach;

import PFCpack.League;
import PFCpack.Team;
import android.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

class MainActivity$75
  implements View.OnClickListener
{
  MainActivity$75(MainActivity paramMainActivity, EditText paramEditText1, EditText paramEditText2, Spinner paramSpinner1, Spinner paramSpinner2, AlertDialog paramAlertDialog) {}
  
  public void onClick(View paramView)
  {
    paramView = val$onlineNameEditText.getText().toString().trim();
    Object localObject = val$onlineAbbrEditText.getText().toString().trim();
    if ((this$0.simLeague.isNameValidOnline(paramView)) && (this$0.simLeague.isAbbrValidOnline((String)localObject)))
    {
      this$0.onlineTeamDict = new JSONObject();
      try
      {
        this$0.onlineTeamDict.put("name", paramView);
        this$0.onlineTeamDict.put("abbr", localObject);
        this$0.onlineTeamDict.put("off strat", val$stratOffSelectionSpinner.getSelectedItemPosition());
        this$0.onlineTeamDict.put("def strat", val$stratDefSelectionSpinner.getSelectedItemPosition());
        this$0.onlineTeamDict.put("players", this$0.userTeam.getPlayersCSV_Online());
        localObject = new JSONObject();
      }
      catch (JSONException localJSONException)
      {
        try
        {
          ((JSONObject)localObject).put("name", paramView);
          new MainActivity.CheckNameUniqueOnline(this$0).execute(new String[] { ((JSONObject)localObject).toString() });
          val$dialog.dismiss();
          return;
          localJSONException = localJSONException;
          localJSONException.printStackTrace();
        }
        catch (JSONException paramView)
        {
          for (;;)
          {
            paramView.printStackTrace();
          }
        }
      }
    }
    Toast.makeText(this$0, "Invalid name or abbr! No special characters are allowed. Name max len = 20, Abbr max len = 5.", 1).show();
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MainActivity.75
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */