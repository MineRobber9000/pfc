package com.achijones.profootballcoach;

import android.view.View;
import android.view.View.OnClickListener;

class TradingBlockListArrayAdapter$1
  implements View.OnClickListener
{
  TradingBlockListArrayAdapter$1(TradingBlockListArrayAdapter paramTradingBlockListArrayAdapter, int paramInt) {}
  
  public void onClick(View paramView)
  {
    TradingBlockListArrayAdapter.access$100(this$0).removeTradePiece(TradingBlockListArrayAdapter.access$000(this$0)[val$position]);
    this$0.notifyDataSetChanged();
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.TradingBlockListArrayAdapter.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */