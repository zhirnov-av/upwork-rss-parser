import org.apache.commons.mail.EmailException;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import rss.*;

import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Main {

    public static void main(String[] args){
        System.out.println("Hello world");
        Date maxDate = new Date(0);

        ArrayList<UpworkFeed> upworkFeeds = new ArrayList<>();

        try {
            RSSFeedParser parser = new RSSFeedParser("https://www.upwork.com/ab/feed/jobs/rss?subcategory2=desktop_software_development&sort=renew_time_int+desc&api_params=1&q=&securityToken=c636015b813452f4796e847b381c87e69c9ca269c683f5e832e076f548ad3f8ee69bc2ec44d1b258dbdfea73c7fd6ea3489459d8d6dbe2f794a491f094b57b5a&userUid=819407936049897472&orgUid=819407936054091777");
            Feed feed = parser.readFeed();

            UpworkFeed upworkFeed = new UpworkFeed("1", feed);
            upworkFeed.processMessages();
            upworkFeeds.add(upworkFeed);

            parser = new RSSFeedParser("https://www.upwork.com/ab/feed/jobs/rss?subcategory2=other_software_development&sort=renew_time_int+desc&api_params=1&q=&securityToken=c636015b813452f4796e847b381c87e69c9ca269c683f5e832e076f548ad3f8ee69bc2ec44d1b258dbdfea73c7fd6ea3489459d8d6dbe2f794a491f094b57b5a&userUid=819407936049897472&orgUid=819407936054091777");
            feed = parser.readFeed();

            upworkFeed = new UpworkFeed("2", feed);
            upworkFeed.processMessages();
            upworkFeeds.add(upworkFeed);

            parser = new RSSFeedParser("https://www.upwork.com/ab/feed/jobs/rss?subcategory2=scripts_utilities&sort=renew_time_int+desc&api_params=1&q=&securityToken=c636015b813452f4796e847b381c87e69c9ca269c683f5e832e076f548ad3f8ee69bc2ec44d1b258dbdfea73c7fd6ea3489459d8d6dbe2f794a491f094b57b5a&userUid=819407936049897472&orgUid=819407936054091777");
            feed = parser.readFeed();

            upworkFeed = new UpworkFeed("3", feed);
            upworkFeed.processMessages();
            upworkFeeds.add(upworkFeed);

        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fstream = new FileOutputStream("c:\\tmp\\config.cfg", false);
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(fstream));

            for (UpworkFeed feed:upworkFeeds){
                wr.write(String.format("%s=%s", feed.getId(), feed.getLastDate().toString()));
                wr.newLine();
            }
            wr.flush();

            fstream.flush();
            fstream.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
