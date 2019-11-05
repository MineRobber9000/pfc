package com.achijones.profootballcoach;

import PFCpack.Team;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

class OnlineActivity$GetTeamOnline
  extends AsyncTask<String, String, String>
{
  boolean success;
  
  OnlineActivity$GetTeamOnline(OnlineActivity paramOnlineActivity) {}
  
  /* Error */
  protected String doInBackground(String... paramVarArgs)
  {
    // Byte code:
    //   0: aload_1
    //   1: iconst_0
    //   2: aaload
    //   3: astore 10
    //   5: aconst_null
    //   6: astore 7
    //   8: aconst_null
    //   9: astore 9
    //   11: aconst_null
    //   12: astore 6
    //   14: aconst_null
    //   15: astore 8
    //   17: aload 6
    //   19: astore 4
    //   21: aload 9
    //   23: astore_1
    //   24: aload 7
    //   26: astore 5
    //   28: aload_0
    //   29: getfield 18	com/achijones/profootballcoach/OnlineActivity$GetTeamOnline:this$0	Lcom/achijones/profootballcoach/OnlineActivity;
    //   32: getfield 36	com/achijones/profootballcoach/OnlineActivity:gettingRandTeam	Z
    //   35: ifeq +241 -> 276
    //   38: aload 6
    //   40: astore 4
    //   42: aload 9
    //   44: astore_1
    //   45: aload 7
    //   47: astore 5
    //   49: new 38	java/net/URL
    //   52: dup
    //   53: ldc 40
    //   55: invokespecial 43	java/net/URL:<init>	(Ljava/lang/String;)V
    //   58: astore_3
    //   59: aload 6
    //   61: astore 4
    //   63: aload 9
    //   65: astore_1
    //   66: aload 7
    //   68: astore 5
    //   70: aload_3
    //   71: invokevirtual 47	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   74: checkcast 49	java/net/HttpURLConnection
    //   77: astore_3
    //   78: aload 6
    //   80: astore 4
    //   82: aload_3
    //   83: astore_1
    //   84: aload_3
    //   85: astore 5
    //   87: aload_3
    //   88: iconst_1
    //   89: invokevirtual 53	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   92: aload 6
    //   94: astore 4
    //   96: aload_3
    //   97: astore_1
    //   98: aload_3
    //   99: astore 5
    //   101: aload_3
    //   102: ldc 55
    //   104: invokevirtual 58	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   107: aload 6
    //   109: astore 4
    //   111: aload_3
    //   112: astore_1
    //   113: aload_3
    //   114: astore 5
    //   116: aload_3
    //   117: ldc 60
    //   119: ldc 62
    //   121: invokevirtual 66	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   124: aload 6
    //   126: astore 4
    //   128: aload_3
    //   129: astore_1
    //   130: aload_3
    //   131: astore 5
    //   133: aload_3
    //   134: ldc 68
    //   136: ldc 62
    //   138: invokevirtual 66	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   141: aload 6
    //   143: astore 4
    //   145: aload_3
    //   146: astore_1
    //   147: aload_3
    //   148: astore 5
    //   150: new 70	java/io/BufferedWriter
    //   153: dup
    //   154: new 72	java/io/OutputStreamWriter
    //   157: dup
    //   158: aload_3
    //   159: invokevirtual 76	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   162: ldc 78
    //   164: invokespecial 81	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;Ljava/lang/String;)V
    //   167: invokespecial 84	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
    //   170: astore 7
    //   172: aload 6
    //   174: astore 4
    //   176: aload_3
    //   177: astore_1
    //   178: aload_3
    //   179: astore 5
    //   181: aload 7
    //   183: aload 10
    //   185: invokevirtual 89	java/io/Writer:write	(Ljava/lang/String;)V
    //   188: aload 6
    //   190: astore 4
    //   192: aload_3
    //   193: astore_1
    //   194: aload_3
    //   195: astore 5
    //   197: aload 7
    //   199: invokevirtual 92	java/io/Writer:flush	()V
    //   202: aload 6
    //   204: astore 4
    //   206: aload_3
    //   207: astore_1
    //   208: aload_3
    //   209: astore 5
    //   211: aload 7
    //   213: invokevirtual 95	java/io/Writer:close	()V
    //   216: aload 6
    //   218: astore 4
    //   220: aload_3
    //   221: astore_1
    //   222: aload_3
    //   223: astore 5
    //   225: aload_3
    //   226: invokevirtual 99	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   229: astore 9
    //   231: aload 6
    //   233: astore 4
    //   235: aload_3
    //   236: astore_1
    //   237: aload_3
    //   238: astore 5
    //   240: new 101	java/lang/StringBuffer
    //   243: dup
    //   244: invokespecial 102	java/lang/StringBuffer:<init>	()V
    //   247: astore 7
    //   249: aload 9
    //   251: ifnonnull +56 -> 307
    //   254: aload_3
    //   255: ifnull +7 -> 262
    //   258: aload_3
    //   259: invokevirtual 105	java/net/HttpURLConnection:disconnect	()V
    //   262: iconst_0
    //   263: ifeq +11 -> 274
    //   266: new 107	java/lang/NullPointerException
    //   269: dup
    //   270: invokespecial 108	java/lang/NullPointerException:<init>	()V
    //   273: athrow
    //   274: aconst_null
    //   275: areturn
    //   276: aload 6
    //   278: astore 4
    //   280: aload 9
    //   282: astore_1
    //   283: aload 7
    //   285: astore 5
    //   287: new 38	java/net/URL
    //   290: dup
    //   291: ldc 110
    //   293: invokespecial 43	java/net/URL:<init>	(Ljava/lang/String;)V
    //   296: astore_3
    //   297: goto -238 -> 59
    //   300: astore_1
    //   301: aload_1
    //   302: invokevirtual 113	java/io/IOException:printStackTrace	()V
    //   305: aconst_null
    //   306: areturn
    //   307: aload 6
    //   309: astore 4
    //   311: aload_3
    //   312: astore_1
    //   313: aload_3
    //   314: astore 5
    //   316: new 115	java/io/BufferedReader
    //   319: dup
    //   320: new 117	java/io/InputStreamReader
    //   323: dup
    //   324: aload 9
    //   326: invokespecial 120	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   329: invokespecial 123	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   332: astore 6
    //   334: aload 6
    //   336: invokevirtual 127	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   339: astore_1
    //   340: aload_1
    //   341: ifnull +64 -> 405
    //   344: aload 7
    //   346: new 129	java/lang/StringBuilder
    //   349: dup
    //   350: invokespecial 130	java/lang/StringBuilder:<init>	()V
    //   353: aload_1
    //   354: invokevirtual 134	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   357: ldc -120
    //   359: invokevirtual 134	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   362: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   365: invokevirtual 142	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   368: pop
    //   369: goto -35 -> 334
    //   372: astore 7
    //   374: aload 6
    //   376: astore 4
    //   378: aload_3
    //   379: astore_1
    //   380: aload 7
    //   382: invokevirtual 113	java/io/IOException:printStackTrace	()V
    //   385: aload_3
    //   386: ifnull +7 -> 393
    //   389: aload_3
    //   390: invokevirtual 105	java/net/HttpURLConnection:disconnect	()V
    //   393: aload 6
    //   395: ifnull +8 -> 403
    //   398: aload 6
    //   400: invokevirtual 143	java/io/BufferedReader:close	()V
    //   403: aconst_null
    //   404: areturn
    //   405: aload 7
    //   407: invokevirtual 147	java/lang/StringBuffer:length	()I
    //   410: istore_2
    //   411: iload_2
    //   412: ifne +31 -> 443
    //   415: aload_3
    //   416: ifnull +7 -> 423
    //   419: aload_3
    //   420: invokevirtual 105	java/net/HttpURLConnection:disconnect	()V
    //   423: aload 6
    //   425: ifnull +8 -> 433
    //   428: aload 6
    //   430: invokevirtual 143	java/io/BufferedReader:close	()V
    //   433: aconst_null
    //   434: areturn
    //   435: astore_1
    //   436: aload_1
    //   437: invokevirtual 113	java/io/IOException:printStackTrace	()V
    //   440: goto -7 -> 433
    //   443: aload 7
    //   445: invokevirtual 148	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   448: astore_1
    //   449: getstatic 154	java/lang/System:out	Ljava/io/PrintStream;
    //   452: new 129	java/lang/StringBuilder
    //   455: dup
    //   456: invokespecial 130	java/lang/StringBuilder:<init>	()V
    //   459: ldc -100
    //   461: invokevirtual 134	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   464: aload_1
    //   465: invokevirtual 134	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   468: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   471: invokevirtual 161	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   474: new 163	org/json/JSONObject
    //   477: dup
    //   478: aload_1
    //   479: invokespecial 164	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   482: astore 4
    //   484: aload 4
    //   486: ldc -91
    //   488: invokevirtual 169	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   491: checkcast 171	java/lang/Integer
    //   494: invokevirtual 174	java/lang/Integer:intValue	()I
    //   497: ifne +36 -> 533
    //   500: getstatic 154	java/lang/System:out	Ljava/io/PrintStream;
    //   503: ldc -80
    //   505: invokevirtual 161	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   508: aload_0
    //   509: iconst_0
    //   510: putfield 178	com/achijones/profootballcoach/OnlineActivity$GetTeamOnline:success	Z
    //   513: aload_3
    //   514: ifnull +7 -> 521
    //   517: aload_3
    //   518: invokevirtual 105	java/net/HttpURLConnection:disconnect	()V
    //   521: aload 6
    //   523: ifnull +8 -> 531
    //   526: aload 6
    //   528: invokevirtual 143	java/io/BufferedReader:close	()V
    //   531: aload_1
    //   532: areturn
    //   533: aload_0
    //   534: iconst_1
    //   535: putfield 178	com/achijones/profootballcoach/OnlineActivity$GetTeamOnline:success	Z
    //   538: aload_0
    //   539: getfield 18	com/achijones/profootballcoach/OnlineActivity$GetTeamOnline:this$0	Lcom/achijones/profootballcoach/OnlineActivity;
    //   542: getfield 181	com/achijones/profootballcoach/OnlineActivity:gettingUserTeam	Z
    //   545: ifeq +57 -> 602
    //   548: aload_0
    //   549: getfield 18	com/achijones/profootballcoach/OnlineActivity$GetTeamOnline:this$0	Lcom/achijones/profootballcoach/OnlineActivity;
    //   552: new 183	PFCpack/Team
    //   555: dup
    //   556: aload 4
    //   558: invokespecial 186	PFCpack/Team:<init>	(Lorg/json/JSONObject;)V
    //   561: putfield 190	com/achijones/profootballcoach/OnlineActivity:userTeam	LPFCpack/Team;
    //   564: goto -51 -> 513
    //   567: astore 4
    //   569: aload 4
    //   571: invokevirtual 191	java/lang/Exception:printStackTrace	()V
    //   574: goto -61 -> 513
    //   577: astore_1
    //   578: aload 6
    //   580: astore 4
    //   582: aload_3
    //   583: ifnull +7 -> 590
    //   586: aload_3
    //   587: invokevirtual 105	java/net/HttpURLConnection:disconnect	()V
    //   590: aload 4
    //   592: ifnull +8 -> 600
    //   595: aload 4
    //   597: invokevirtual 143	java/io/BufferedReader:close	()V
    //   600: aload_1
    //   601: athrow
    //   602: aload_0
    //   603: getfield 18	com/achijones/profootballcoach/OnlineActivity$GetTeamOnline:this$0	Lcom/achijones/profootballcoach/OnlineActivity;
    //   606: new 183	PFCpack/Team
    //   609: dup
    //   610: aload 4
    //   612: invokespecial 186	PFCpack/Team:<init>	(Lorg/json/JSONObject;)V
    //   615: putfield 194	com/achijones/profootballcoach/OnlineActivity:oppTeam	LPFCpack/Team;
    //   618: goto -105 -> 513
    //   621: astore_3
    //   622: aload_3
    //   623: invokevirtual 113	java/io/IOException:printStackTrace	()V
    //   626: goto -95 -> 531
    //   629: astore_1
    //   630: aload_1
    //   631: invokevirtual 113	java/io/IOException:printStackTrace	()V
    //   634: goto -231 -> 403
    //   637: astore_3
    //   638: aload_3
    //   639: invokevirtual 113	java/io/IOException:printStackTrace	()V
    //   642: goto -42 -> 600
    //   645: astore 5
    //   647: aload_1
    //   648: astore_3
    //   649: aload 5
    //   651: astore_1
    //   652: goto -70 -> 582
    //   655: astore 7
    //   657: aload 8
    //   659: astore 6
    //   661: aload 5
    //   663: astore_3
    //   664: goto -290 -> 374
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	667	0	this	GetTeamOnline
    //   0	667	1	paramVarArgs	String[]
    //   410	2	2	i	int
    //   58	529	3	localObject1	Object
    //   621	2	3	localIOException1	java.io.IOException
    //   637	2	3	localIOException2	java.io.IOException
    //   648	16	3	localObject2	Object
    //   19	538	4	localObject3	Object
    //   567	3	4	localException	Exception
    //   580	31	4	localObject4	Object
    //   26	289	5	localObject5	Object
    //   645	17	5	localObject6	Object
    //   12	648	6	localObject7	Object
    //   6	339	7	localObject8	Object
    //   372	72	7	localIOException3	java.io.IOException
    //   655	1	7	localIOException4	java.io.IOException
    //   15	643	8	localObject9	Object
    //   9	316	9	localInputStream	java.io.InputStream
    //   3	181	10	str	String
    // Exception table:
    //   from	to	target	type
    //   266	274	300	java/io/IOException
    //   334	340	372	java/io/IOException
    //   344	369	372	java/io/IOException
    //   405	411	372	java/io/IOException
    //   443	474	372	java/io/IOException
    //   474	513	372	java/io/IOException
    //   533	564	372	java/io/IOException
    //   569	574	372	java/io/IOException
    //   602	618	372	java/io/IOException
    //   428	433	435	java/io/IOException
    //   474	513	567	java/lang/Exception
    //   533	564	567	java/lang/Exception
    //   602	618	567	java/lang/Exception
    //   334	340	577	finally
    //   344	369	577	finally
    //   405	411	577	finally
    //   443	474	577	finally
    //   474	513	577	finally
    //   533	564	577	finally
    //   569	574	577	finally
    //   602	618	577	finally
    //   526	531	621	java/io/IOException
    //   398	403	629	java/io/IOException
    //   595	600	637	java/io/IOException
    //   28	38	645	finally
    //   49	59	645	finally
    //   70	78	645	finally
    //   87	92	645	finally
    //   101	107	645	finally
    //   116	124	645	finally
    //   133	141	645	finally
    //   150	172	645	finally
    //   181	188	645	finally
    //   197	202	645	finally
    //   211	216	645	finally
    //   225	231	645	finally
    //   240	249	645	finally
    //   287	297	645	finally
    //   316	334	645	finally
    //   380	385	645	finally
    //   28	38	655	java/io/IOException
    //   49	59	655	java/io/IOException
    //   70	78	655	java/io/IOException
    //   87	92	655	java/io/IOException
    //   101	107	655	java/io/IOException
    //   116	124	655	java/io/IOException
    //   133	141	655	java/io/IOException
    //   150	172	655	java/io/IOException
    //   181	188	655	java/io/IOException
    //   197	202	655	java/io/IOException
    //   211	216	655	java/io/IOException
    //   225	231	655	java/io/IOException
    //   240	249	655	java/io/IOException
    //   287	297	655	java/io/IOException
    //   316	334	655	java/io/IOException
  }
  
  protected void onPostExecute(String paramString)
  {
    if (!success)
    {
      Toast.makeText(this$0, "Could not find team.", 0).show();
      this$0.playWeekButton.setText("Schedule Ranked Game");
      return;
    }
    if (!this$0.gettingUserTeam)
    {
      Toast.makeText(this$0, "Found opponent: " + this$0.oppTeam.name + "!", 0).show();
      this$0.scheduleGame(this$0.gettingRandTeam);
    }
    for (;;)
    {
      this$0.updateUI();
      OnlineActivity.access$100(this$0, false);
      this$0.examineTeamSpinner.setSelection(1);
      return;
      if ((this$0.userTeam.wins == 0) && (this$0.userTeam.losses == 0))
      {
        paramString = new AlertDialog.Builder(this$0);
        paramString.setTitle("Welcome to Online Play!").setMessage("Here, you can compete with other teams from around the world!\n\n This multiplayer is asynchronous, which means that you can play against other teams at your own leisure. You can schedule teams to play against, and other players will also schedule your team to play against.\n\n By pressing Schedule Ranked Game, a team will be randomly selected for you to play against. Then you can compare the two teams by viewing their rosters, team stats, and more.\n\n When you are ready, press Play Week to play a game against them. Since you are playing from your device, you will have home field advantage. Each random game is considered Ranked, which means it will affect your ELO ranking.\n\n You can also schedule custom games against any team you like by clicking Custom Game in the menu. These games won't affect ELO.\n\n Be sure to set your default strategy by using the menu as well. This is the strategy that your team will use when other teams play against yours.\n\n Have fun and good luck!").setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
        });
        paramString = paramString.create();
        paramString.show();
        ((TextView)paramString.findViewById(16908299)).setTextSize(2, 14.0F);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.OnlineActivity.GetTeamOnline
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */