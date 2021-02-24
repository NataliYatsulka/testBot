import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.*;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Bot extends TelegramLongPollingBot {

    private static final String TOKEN = "1612312557:AAF_DXowhlWz93kY5ZlrjoCHfwYntGY4yps";
    private static final String USERNAME = "MyTestDBBot";
    private static final String NATA_CHAT = "550240176";
    private static final String GROUP_CHAT = "-479389921";


    public Bot(DefaultBotOptions options) {
        super(options);
    }

    public Bot() {
    }

    public String getBotUsername() {
        return USERNAME;
    }

    public String getBotToken() {
        return TOKEN;
    }

    public void onUpdateReceived(Update update) {
        printMsg("UPDATE");
        System.out.println(update.getMessage().getChatId());
        int count = 0;
        try {
            count = ConnectionDB.connectDb();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (update.hasMessage() && update.getMessage().hasText()) {

            SendMessage message = new SendMessage(update.getMessage().getChatId().toString(),
                    "HELLOOO " + count);
            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }

    public void printMsg(String message) {
        SendMessage sendMessage = new SendMessage(GROUP_CHAT, message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
