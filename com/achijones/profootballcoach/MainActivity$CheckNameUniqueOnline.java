package com.achijones.profootballcoach;

import android.os.AsyncTask;
import android.widget.Toast;

class MainActivity$CheckNameUniqueOnline
  extends AsyncTask<String, String, String>
{
  boolean unique = false;
  
  MainActivity$CheckNameUniqueOnline(MainActivity paramMainActivity) {}
  
  /* Error */
  protected String doInBackground(String... paramVarArgs)
  {
    // Byte code:
    //   0: aload_1
    //   1: iconst_0
    //   2: aaload
    //   3: astore 8
    //   5: aconst_null
    //   6: astore 6
    //   8: aconst_null
    //   9: astore 4
    //   11: aconst_null
    //   12: astore 7
    //   14: aconst_null
    //   15: astore 9
    //   17: aload 7
    //   19: astore 5
    //   21: new 35	java/net/URL
    //   24: dup
    //   25: ldc 37
    //   27: invokespecial 40	java/net/URL:<init>	(Ljava/lang/String;)V
    //   30: invokevirtual 44	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   33: checkcast 46	java/net/HttpURLConnection
    //   36: astore_1
    //   37: aload 7
    //   39: astore 5
    //   41: aload_1
    //   42: astore 4
    //   44: aload_1
    //   45: astore 6
    //   47: aload_1
    //   48: iconst_1
    //   49: invokevirtual 50	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   52: aload 7
    //   54: astore 5
    //   56: aload_1
    //   57: astore 4
    //   59: aload_1
    //   60: astore 6
    //   62: aload_1
    //   63: ldc 52
    //   65: invokevirtual 55	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   68: aload 7
    //   70: astore 5
    //   72: aload_1
    //   73: astore 4
    //   75: aload_1
    //   76: astore 6
    //   78: aload_1
    //   79: ldc 57
    //   81: ldc 59
    //   83: invokevirtual 63	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   86: aload 7
    //   88: astore 5
    //   90: aload_1
    //   91: astore 4
    //   93: aload_1
    //   94: astore 6
    //   96: aload_1
    //   97: ldc 65
    //   99: ldc 59
    //   101: invokevirtual 63	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   104: aload 7
    //   106: astore 5
    //   108: aload_1
    //   109: astore 4
    //   111: aload_1
    //   112: astore 6
    //   114: new 67	java/io/BufferedWriter
    //   117: dup
    //   118: new 69	java/io/OutputStreamWriter
    //   121: dup
    //   122: aload_1
    //   123: invokevirtual 73	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   126: ldc 75
    //   128: invokespecial 78	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;Ljava/lang/String;)V
    //   131: invokespecial 81	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
    //   134: astore 10
    //   136: aload 7
    //   138: astore 5
    //   140: aload_1
    //   141: astore 4
    //   143: aload_1
    //   144: astore 6
    //   146: aload 10
    //   148: aload 8
    //   150: invokevirtual 86	java/io/Writer:write	(Ljava/lang/String;)V
    //   153: aload 7
    //   155: astore 5
    //   157: aload_1
    //   158: astore 4
    //   160: aload_1
    //   161: astore 6
    //   163: aload 10
    //   165: invokevirtual 89	java/io/Writer:flush	()V
    //   168: aload 7
    //   170: astore 5
    //   172: aload_1
    //   173: astore 4
    //   175: aload_1
    //   176: astore 6
    //   178: aload 10
    //   180: invokevirtual 92	java/io/Writer:close	()V
    //   183: aload 7
    //   185: astore 5
    //   187: aload_1
    //   188: astore 4
    //   190: aload_1
    //   191: astore 6
    //   193: aload_1
    //   194: invokevirtual 96	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   197: astore 10
    //   199: aload 7
    //   201: astore 5
    //   203: aload_1
    //   204: astore 4
    //   206: aload_1
    //   207: astore 6
    //   209: new 98	java/lang/StringBuffer
    //   212: dup
    //   213: invokespecial 99	java/lang/StringBuffer:<init>	()V
    //   216: astore 8
    //   218: aload 10
    //   220: ifnonnull +32 -> 252
    //   223: aload_1
    //   224: ifnull +7 -> 231
    //   227: aload_1
    //   228: invokevirtual 102	java/net/HttpURLConnection:disconnect	()V
    //   231: iconst_0
    //   232: ifeq +11 -> 243
    //   235: new 104	java/lang/NullPointerException
    //   238: dup
    //   239: invokespecial 105	java/lang/NullPointerException:<init>	()V
    //   242: athrow
    //   243: aconst_null
    //   244: areturn
    //   245: astore_1
    //   246: aload_1
    //   247: invokevirtual 108	java/io/IOException:printStackTrace	()V
    //   250: aconst_null
    //   251: areturn
    //   252: aload 7
    //   254: astore 5
    //   256: aload_1
    //   257: astore 4
    //   259: aload_1
    //   260: astore 6
    //   262: new 110	java/io/BufferedReader
    //   265: dup
    //   266: new 112	java/io/InputStreamReader
    //   269: dup
    //   270: aload 10
    //   272: invokespecial 115	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   275: invokespecial 118	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   278: astore 7
    //   280: aload 7
    //   282: invokevirtual 122	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   285: astore 4
    //   287: aload 4
    //   289: ifnull +66 -> 355
    //   292: aload 8
    //   294: new 124	java/lang/StringBuilder
    //   297: dup
    //   298: invokespecial 125	java/lang/StringBuilder:<init>	()V
    //   301: aload 4
    //   303: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   306: ldc -125
    //   308: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   311: invokevirtual 134	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   314: invokevirtual 137	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   317: pop
    //   318: goto -38 -> 280
    //   321: astore 8
    //   323: aload 7
    //   325: astore 5
    //   327: aload_1
    //   328: astore 4
    //   330: aload 8
    //   332: invokevirtual 108	java/io/IOException:printStackTrace	()V
    //   335: aload_1
    //   336: ifnull +7 -> 343
    //   339: aload_1
    //   340: invokevirtual 102	java/net/HttpURLConnection:disconnect	()V
    //   343: aload 7
    //   345: ifnull +8 -> 353
    //   348: aload 7
    //   350: invokevirtual 138	java/io/BufferedReader:close	()V
    //   353: aconst_null
    //   354: areturn
    //   355: aload 8
    //   357: invokevirtual 142	java/lang/StringBuffer:length	()I
    //   360: istore_2
    //   361: iload_2
    //   362: ifne +31 -> 393
    //   365: aload_1
    //   366: ifnull +7 -> 373
    //   369: aload_1
    //   370: invokevirtual 102	java/net/HttpURLConnection:disconnect	()V
    //   373: aload 7
    //   375: ifnull +8 -> 383
    //   378: aload 7
    //   380: invokevirtual 138	java/io/BufferedReader:close	()V
    //   383: aconst_null
    //   384: areturn
    //   385: astore_1
    //   386: aload_1
    //   387: invokevirtual 108	java/io/IOException:printStackTrace	()V
    //   390: goto -7 -> 383
    //   393: aload 8
    //   395: invokevirtual 143	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   398: astore 4
    //   400: getstatic 149	java/lang/System:out	Ljava/io/PrintStream;
    //   403: new 124	java/lang/StringBuilder
    //   406: dup
    //   407: invokespecial 125	java/lang/StringBuilder:<init>	()V
    //   410: ldc -105
    //   412: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   415: aload 4
    //   417: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   420: invokevirtual 134	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   423: invokevirtual 156	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   426: new 158	org/json/JSONObject
    //   429: dup
    //   430: aload 4
    //   432: invokespecial 159	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   435: astore 5
    //   437: getstatic 149	java/lang/System:out	Ljava/io/PrintStream;
    //   440: new 124	java/lang/StringBuilder
    //   443: dup
    //   444: invokespecial 125	java/lang/StringBuilder:<init>	()V
    //   447: ldc -95
    //   449: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   452: aload 5
    //   454: ldc -94
    //   456: invokevirtual 166	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   459: checkcast 168	java/lang/Integer
    //   462: invokevirtual 171	java/lang/Integer:intValue	()I
    //   465: invokevirtual 174	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   468: invokevirtual 134	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   471: invokevirtual 156	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   474: aload 5
    //   476: ldc -94
    //   478: invokevirtual 166	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   481: checkcast 168	java/lang/Integer
    //   484: invokevirtual 171	java/lang/Integer:intValue	()I
    //   487: iconst_1
    //   488: if_icmpne +31 -> 519
    //   491: iconst_1
    //   492: istore_3
    //   493: aload_0
    //   494: iload_3
    //   495: putfield 21	com/achijones/profootballcoach/MainActivity$CheckNameUniqueOnline:unique	Z
    //   498: aload_1
    //   499: ifnull +7 -> 506
    //   502: aload_1
    //   503: invokevirtual 102	java/net/HttpURLConnection:disconnect	()V
    //   506: aload 7
    //   508: ifnull +8 -> 516
    //   511: aload 7
    //   513: invokevirtual 138	java/io/BufferedReader:close	()V
    //   516: aload 4
    //   518: areturn
    //   519: iconst_0
    //   520: istore_3
    //   521: goto -28 -> 493
    //   524: astore 5
    //   526: aload 5
    //   528: invokevirtual 175	java/lang/Exception:printStackTrace	()V
    //   531: goto -33 -> 498
    //   534: astore 4
    //   536: aload 7
    //   538: astore 5
    //   540: aload_1
    //   541: ifnull +7 -> 548
    //   544: aload_1
    //   545: invokevirtual 102	java/net/HttpURLConnection:disconnect	()V
    //   548: aload 5
    //   550: ifnull +8 -> 558
    //   553: aload 5
    //   555: invokevirtual 138	java/io/BufferedReader:close	()V
    //   558: aload 4
    //   560: athrow
    //   561: astore_1
    //   562: aload_1
    //   563: invokevirtual 108	java/io/IOException:printStackTrace	()V
    //   566: goto -50 -> 516
    //   569: astore_1
    //   570: aload_1
    //   571: invokevirtual 108	java/io/IOException:printStackTrace	()V
    //   574: goto -221 -> 353
    //   577: astore_1
    //   578: aload_1
    //   579: invokevirtual 108	java/io/IOException:printStackTrace	()V
    //   582: goto -24 -> 558
    //   585: astore 6
    //   587: aload 4
    //   589: astore_1
    //   590: aload 6
    //   592: astore 4
    //   594: goto -54 -> 540
    //   597: astore 8
    //   599: aload 9
    //   601: astore 7
    //   603: aload 6
    //   605: astore_1
    //   606: goto -283 -> 323
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	609	0	this	CheckNameUniqueOnline
    //   0	609	1	paramVarArgs	String[]
    //   360	2	2	i	int
    //   492	29	3	bool	boolean
    //   9	508	4	localObject1	Object
    //   534	54	4	localObject2	Object
    //   592	1	4	localObject3	Object
    //   19	456	5	localObject4	Object
    //   524	3	5	localException	Exception
    //   538	16	5	localObject5	Object
    //   6	255	6	arrayOfString	String[]
    //   585	19	6	localObject6	Object
    //   12	590	7	localObject7	Object
    //   3	290	8	localObject8	Object
    //   321	73	8	localIOException1	java.io.IOException
    //   597	1	8	localIOException2	java.io.IOException
    //   15	585	9	localObject9	Object
    //   134	137	10	localObject10	Object
    // Exception table:
    //   from	to	target	type
    //   235	243	245	java/io/IOException
    //   280	287	321	java/io/IOException
    //   292	318	321	java/io/IOException
    //   355	361	321	java/io/IOException
    //   393	426	321	java/io/IOException
    //   426	491	321	java/io/IOException
    //   493	498	321	java/io/IOException
    //   526	531	321	java/io/IOException
    //   378	383	385	java/io/IOException
    //   426	491	524	java/lang/Exception
    //   493	498	524	java/lang/Exception
    //   280	287	534	finally
    //   292	318	534	finally
    //   355	361	534	finally
    //   393	426	534	finally
    //   426	491	534	finally
    //   493	498	534	finally
    //   526	531	534	finally
    //   511	516	561	java/io/IOException
    //   348	353	569	java/io/IOException
    //   553	558	577	java/io/IOException
    //   21	37	585	finally
    //   47	52	585	finally
    //   62	68	585	finally
    //   78	86	585	finally
    //   96	104	585	finally
    //   114	136	585	finally
    //   146	153	585	finally
    //   163	168	585	finally
    //   178	183	585	finally
    //   193	199	585	finally
    //   209	218	585	finally
    //   262	280	585	finally
    //   330	335	585	finally
    //   21	37	597	java/io/IOException
    //   47	52	597	java/io/IOException
    //   62	68	597	java/io/IOException
    //   78	86	597	java/io/IOException
    //   96	104	597	java/io/IOException
    //   114	136	597	java/io/IOException
    //   146	153	597	java/io/IOException
    //   163	168	597	java/io/IOException
    //   178	183	597	java/io/IOException
    //   193	199	597	java/io/IOException
    //   209	218	597	java/io/IOException
    //   262	280	597	java/io/IOException
  }
  
  protected void onPostExecute(String paramString)
  {
    if (unique)
    {
      this$0.insertTeamOnlineDialog();
      return;
    }
    Toast.makeText(this$0, "Team name not unique, or could not connect.", 0).show();
    this$0.goOnline();
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MainActivity.CheckNameUniqueOnline
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */