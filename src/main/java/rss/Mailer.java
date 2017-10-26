package rss;

import org.apache.commons.mail.*;

public class Mailer {

    public static void sendMail(String title, String body, String link) throws EmailException {
        ImageHtmlEmail email = new ImageHtmlEmail();
        email.setHostName("smtp.yandex.ru");
        email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("zhirnov-av@yandex.ru", "tomohawk200401"));
        email.setAuthentication("zhirnov-av@yandex.ru", "tomohawk200401");
        email.setSSLCheckServerIdentity(true);
        email.setSSLOnConnect(true);
        email.setFrom("zhirnov-av@yandex.ru");
        email.setSubject("[my-upwork-bot]" + title);
        email.setHtmlMsg(String.format("<a href=\"%s\">%s</a><br><br><br>%s", link, title, body) );
        email.addTo("zhirnov-av@yandex.ru");
        email.send();

    }
}
