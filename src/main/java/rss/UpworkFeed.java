package rss;

import org.apache.commons.mail.EmailException;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpworkFeed {
    Feed feed;
    Date lastDate;
    String id;

    public UpworkFeed(String id, Feed feed){
        this.feed = feed;
        this.id = id;
        this.lastDate = new Date(0);
        FileInputStream fInputStream = null;
        try {
            fInputStream = new FileInputStream("c:\\tmp\\config.cfg");
            BufferedReader rd = new BufferedReader(new InputStreamReader(fInputStream));
            String strLine;
            while ((strLine = rd.readLine()) != null) {
                String[] split = strLine.split("\\=");
                if (split[0].equals(id)) {
                    SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.US);
                    try {
                        this.lastDate = formatter.parse(split[1]);
                    } catch (ParseException | NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            }
            fInputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processMessages(){
        for(int i = feed.getMessages().size() - 1; i >= 0; i--){
            FeedMessage message = feed.getMessages().get(i);
            if (message.getDate().after(lastDate)) {
                lastDate = message.getDate();

                Pattern p = Pattern.compile("(.+JAVA[^S].+)|(.+WEBSERVICE.+)|(.+WEB[\\s\\-]SERVICE.+)|(.+C#.+)");
                Matcher m = p.matcher(message.getDescription().toUpperCase());

                if (m.find()){
                    try {
                        Mailer.sendMail(message.getTitle(), message.getDescription(), message.getLink());
                        System.out.println(String.format("ID: %s, Send message - Title: %s", this.id, message.getTitle()));
                    } catch (EmailException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    public Date getLastDate() {
        return lastDate;
    }

    public String getId() {
        return id;
    }
}
