package com.achijones.profootballcoach;

import PFCpack.League;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

class MainActivity$35
  implements TextWatcher
{
  String newName;
  
  MainActivity$35(MainActivity paramMainActivity, TextView paramTextView) {}
  
  public void afterTextChanged(Editable paramEditable)
  {
    newName = paramEditable.toString().trim();
    if (!this$0.simLeague.isNameValid(newName))
    {
      val$invalidNameText.setText("Name already in use or has illegal characters!");
      return;
    }
    val$invalidNameText.setText("");
  }
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    newName = paramCharSequence.toString().trim();
    if (!this$0.simLeague.isNameValid(newName))
    {
      val$invalidNameText.setText("Name already in use or has illegal characters!");
      return;
    }
    val$invalidNameText.setText("");
  }
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    newName = paramCharSequence.toString().trim();
    if (!this$0.simLeague.isNameValid(newName))
    {
      val$invalidNameText.setText("Name already in use or has illegal characters!");
      return;
    }
    val$invalidNameText.setText("");
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MainActivity.35
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */