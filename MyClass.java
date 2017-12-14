import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
public class MyClass
{
  private final List<NameValuePair> params;
  private final HttpPost post;
  private HttpResponse resp;
  private final HttpClient client;
  private int respCode;
  private BufferedReader reader;
  private final StringBuffer result;
  private String line;

  public MyClass(String ID)
  {  
	  
	  
	  //making connection to redcap 
	  params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("token", "xxxxxxxxxxxxxxxxxxxxxxxxx"));
	    params.add(new BasicNameValuePair("content", "record"));
	    params.add(new BasicNameValuePair("format", "csv"));
	    params.add(new BasicNameValuePair("type", "flat"));
	    params.add(new BasicNameValuePair("records", ID));
	    params.add(new BasicNameValuePair("rawOrLabel", "raw"));
	    params.add(new BasicNameValuePair("rawOrLabelHeaders", "raw"));
	    params.add(new BasicNameValuePair("exportCheckboxLabel", "false"));
	    params.add(new BasicNameValuePair("exportSurveyFields", "false"));
	    params.add(new BasicNameValuePair("exportDataAccessGroups", "false"));
	    params.add(new BasicNameValuePair("returnFormat", "csv"));
    post = new HttpPost("https://redcap.core.wits.ac.za/redcap/api/");
    post.setHeader("Content-Type", "application/x-www-form-urlencoded");
     
     
    try
    {
       post.setEntity(new UrlEncodedFormEntity(params));
    }
    catch (final Exception e)
    {
      e.printStackTrace();
    }

    result = new StringBuffer();
    client = HttpClientBuilder.create().build();
    respCode = -1;
    reader = null;
    line = null;
  }


public StringBuffer doPost()
  {
    resp = null;

    try
    {
      resp = client.execute(post);
    }
    catch (final Exception e)
    {
      e.printStackTrace();
    }

    if(resp != null)
    {
      respCode = resp.getStatusLine().getStatusCode();

      try
      {
        reader = new BufferedReader(new InputStreamReader(resp.getEntity().getContent()));
      }
      catch (final Exception e)
      {
        e.printStackTrace();
      }
    }

    if(reader != null)
    {
      try
      {
        while ((line = reader.readLine()) != null)
        {
          result.append(line);
        }
      }
      catch (final Exception e)
      {
        e.printStackTrace();
      }
    }

    //System.out.println("respCode: " + respCode);
    
      return result;  
  
    		
  }
}