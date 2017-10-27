package telegrambot;

import java.util.ArrayList;

public class TypedMessages {
    ArrayList<String> messages = new ArrayList<>();

    public int addMessage(String message){
        messages.add(message);
        return messages.size();
    }

    public String getRandomMessage(){
        int messageIndex = (int)(Math.random() * messages.size());
        return messages.get(messageIndex);
    }
}
