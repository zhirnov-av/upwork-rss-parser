package telegrambot;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MyTestBot extends TelegramLongPollingBot {

    TypedMessages helloMessages = new TypedMessages();
    TypedMessages azureMessages = new TypedMessages();
    TypedMessages boreMessages = new TypedMessages();
    TypedMessages beerMessages = new TypedMessages();
    TypedMessages aboutMeMessages = new TypedMessages();
    TypedMessages dumbAnswerMessages = new TypedMessages();


    HashMap<String, Date>  userGreetings = new HashMap<>();
    String lastMessageFrom;


    Date lastMessageDate;

    public MyTestBot(DefaultBotOptions opt){
        super(opt);

        HttpHost proxy = new HttpHost("10.2.3.10", 8080);
        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();

        getOptions().setRequestConfig(config);


        helloMessages.addMessage("О-о-о блин! %s, чувак, ты тоже здесь!");
        helloMessages.addMessage("Здорова боярин!");
        helloMessages.addMessage("Ну наконец-то!!! Хоть кто-то тут живой, здорова, %s");
        helloMessages.addMessage("Доброго времени суток, %s");
        helloMessages.addMessage("Привет, %s");
        helloMessages.addMessage("Сколько лет, сколько зим ?!, %s");
        helloMessages.addMessage("Привет, Сири..., ой сорри, %s");

        azureMessages.addMessage("А когда на лазурку? Хотя на филах было круче... может на филы?!");
        azureMessages.addMessage("Ура-а-а-а на Лазурку!!!!!");
        azureMessages.addMessage("Опять бухать... :/");
        azureMessages.addMessage("Дорога - дорога, осталось так много...");
        azureMessages.addMessage("Едем, едем на дискотеку...");

        beerMessages.addMessage("А меня с собой возьмете?!");
        beerMessages.addMessage("Вот бы кто меня позвал...");
        beerMessages.addMessage("А вы все пьете... и пьете... тьфу на вас!!!");
        beerMessages.addMessage("Бухло детектед!!!");
        beerMessages.addMessage("ХЪЭЭ/:*:?*;№;)_(+_*(;;");

        aboutMeMessages.addMessage("А чего сразу я - то?!");
        aboutMeMessages.addMessage("Да, да... Я тут... Я все слышу...");
        aboutMeMessages.addMessage("...");
        aboutMeMessages.addMessage("Но-но поосторожней мне тут!");
        aboutMeMessages.addMessage("Бот в первом поколении, зовут @Subchik, версия 1.0");

        boreMessages.addMessage("О! Тут даже кто-то есть живой?! ;)");
        boreMessages.addMessage("Хто здесь!?");
        boreMessages.addMessage("Тут так было тихо и спокойно...");

        dumbAnswerMessages.addMessage("Я че-то не врубаюсь... Ты чего хотел-то?");
        dumbAnswerMessages.addMessage("Сколько заплатишь?");
        dumbAnswerMessages.addMessage("Бляха, опять че-то спрашивают... Яж бот, яж не понимать!");
        dumbAnswerMessages.addMessage("Вполне возможно, затрудняюсь ответить...");
        dumbAnswerMessages.addMessage("О! блин. Хз, сейчас в инете поищем....");
        dumbAnswerMessages.addMessage("Извините, сударь, но к великому сожалению, я не понял вашего вопроса.");
        dumbAnswerMessages.addMessage("А это, ты бы у сири лучше спросил, бабы они такие, мож быстрей ответят :)");


        lastMessageFrom = "";
        lastMessageDate = new Date();
    }

    private void sendGreetingMessage(Update update){
        String person = update.getMessage().getFrom().getFirstName();
        String message = String.format(helloMessages.getRandomMessage(), person);

        SendMessage message1 = new SendMessage() // Create a SendMessage object with mandatory fields
                .setChatId(update.getMessage().getChatId())
                .setText(message);
        try {
            execute(message1);
            userGreetings.put(update.getMessage().getFrom().getId().toString(), new Date());
            //sendMessage(message1); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendAzureMessage(Update update){
        SendMessage message1 = new SendMessage() // Create a SendMessage object with mandatory fields
                .setChatId(update.getMessage().getChatId())
                .setText(azureMessages.getRandomMessage());
        try {
            execute(message1);
            //sendMessage(message1); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    private void sendDumbAnswerMessage(Update update){
        SendMessage message1 = new SendMessage() // Create a SendMessage object with mandatory fields
                .setChatId(update.getMessage().getChatId())
                .setText(dumbAnswerMessages.getRandomMessage());
        try {
            execute(message1);
            //sendMessage(message1); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }


    private void sendBoreMessage(Update update){
        SendMessage message1 = new SendMessage() // Create a SendMessage object with mandatory fields
                .setChatId(update.getMessage().getChatId())
                .setText(boreMessages.getRandomMessage());
        try {
            execute(message1);
            //sendMessage(message1); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    private void sendBeerMessage(Update update){
        SendMessage message1 = new SendMessage() // Create a SendMessage object with mandatory fields
                .setChatId(update.getMessage().getChatId())
                .setText(beerMessages.getRandomMessage());
        try {
            execute(message1);
            //sendMessage(message1); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    private void sendAboutMeMessage(Update update){
        SendMessage message1 = new SendMessage() // Create a SendMessage object with mandatory fields
                .setChatId(update.getMessage().getChatId())
                .setText(aboutMeMessages.getRandomMessage());
        try {
            execute(message1);
            //sendMessage(message1); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            boolean f = false;

            if (update.getMessage().isReply() && update.getMessage().getReplyToMessage().getFrom().getBot()){
                if (update.getMessage().getText().contains("?")){
                    sendDumbAnswerMessage(update);
                }else {
                    sendAboutMeMessage(update);
                }
                f = true;
            }


            if ( userGreetings.get(update.getMessage().getFrom().getId().toString()) == null ){
                sendGreetingMessage(update);
                f = true;
            }

            if (update.getMessage().getText().toUpperCase().contains("ЛАЗУРК")){
                sendAzureMessage(update);
                f = true;
            }

            if (update.getMessage().getText().toUpperCase().contains("ПИВО")
                    || update.getMessage().getText().toUpperCase().contains("ПИВКА")
                    || update.getMessage().getText().toUpperCase().contains("ПИВА")
                    || update.getMessage().getText().toUpperCase().contains("ВЫПИ")
                    || update.getMessage().getText().toUpperCase().contains("БАР")
                    || update.getMessage().getText().toUpperCase().contains("ВОДК")
                    || update.getMessage().getText().toUpperCase().contains("ПЛАНЫ")
                    || update.getMessage().getText().toUpperCase().contains("ВИСКАР")
                    || update.getMessage().getText().toUpperCase().contains("ВИСКИ")) {


                if ((int) (Math.random() * 2) == 0) {
                    sendBeerMessage(update);
                    f = true;
                }
            }

            if (update.getMessage().getText().toUpperCase().contains("СУБЧИК")
                    || update.getMessage().getText().toUpperCase().contains("БОТ")
                    || update.getMessage().getText().toUpperCase().contains("@")
                    || update.getMessage().getText().toUpperCase().contains("SUBCH")) {


                if ((int) (Math.random() * 2) == 0) {
                    sendAboutMeMessage(update);
                    f = true;
                }
            }


            if (update.getMessage().getText().toUpperCase().contains("ТЫ") && lastMessageFrom.equals("BOT")){
                sendAboutMeMessage(update);
                f = true;
            }

            if ((new Date().getTime() - lastMessageDate.getTime() > 1000000) && (!f)){
                sendBoreMessage(update);
                f = true;
            }

            lastMessageDate = new Date();
            if (f)
                lastMessageFrom = "BOT";
            else
                lastMessageFrom = update.getMessage().getFrom().getId().toString();

            /*
            try {
                execute(message2);
                //sendMessage(message2); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            */
        }
    }

    @Override
    public String getBotUsername() {
        return "Subchik";
    }

    @Override
    public String getBotToken() {
        return "473405530:AAEPQM5ctz_jAfj1xF5nXNSBUJlchl7hvbk";
    }
}
