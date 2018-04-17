package stotextn.example.com.speechtotextn;

/**
 * Created by ADMIN on 12/11/2017.
 */


        import java.text.SimpleDateFormat;
        import java.util.Calendar;
/**
 * Created by ADMIN on 12/8/2017.
 */

public class CommandHandler {
    boolean handleText(String text,MainActivity ma)
    {
        if (text.contains("how are you today") || text.contains("hi how are you") || text.contains("how are you doing"))
        {
            ma.speakOut("I am doing good. Thanks for asking. How are you");
            ma.txtSpeechInput.setText("I am doing good. Thanks for asking. How are you");
            return true;
        }
        else if (text.contains("now , what is the time") || text.contains("what is the time") || text.contains("what is the time in Texas"))
        {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
            String strDate = "The time is  " + mdformat.format(calendar.getTime());
            ma.speakOut(strDate);
            return true;
        }
        else if (text.contains("how old are you") || text.contains("what is your age"))
        {
            ma.speakOut("I am 2months old");
            ma.txtSpeechInput.setText("I am 2months old");
            return true;
        }
        else if (text.contains("do you ever get tired") || text.contains("do you feel tired"))
        {
            ma.speakOut("not at all, I can help you at any time");
            ma.txtSpeechInput.setText("not at all, I can help you at any time");
            return true;
        }
        else if (text.contains("Where is Texas A&M University") || text.contains("Where is Texas A&M"))
        {
            ma.speakOut("There are 11 Universities in A&M System, This is in Commerce");
            ma.txtSpeechInput.setText("There are 11 Universities in A&M System, This is in Commerce");
            return true;
        }
        else if (text.contains("who was your first crush") || text.contains(" your first crush"))
        {
            ma.speakOut("of course it’s you");
            ma.txtSpeechInput.setText("of course it’s you");
            return true;
        }
        else if (text.contains("do you like siri"))
        {
            ma.speakOut("of course I do like it");
            ma.txtSpeechInput.setText("of course I do like it");
            return true;
        }
        else if (text.contains("what is your purpose") || text.contains("your purpose") || text.contains("what you meant for"))
        {
            ma.speakOut("my purpose is to serve you");
            ma.txtSpeechInput.setText("my purpose is to serve you");
            return true;
        }
        else if (text.contains("what do you look like") || text.contains("what you are like"))
        {
            ma.speakOut("Imagine anything beautiful and that’s me");
            ma.txtSpeechInput.setText("Imagine anything beautiful and that’s me");
            return true;
        }
        else if (text.contains("where do you live") || text.contains("who do you stay"))
        {
            ma.speakOut("I live inside your mobile");
            ma.txtSpeechInput.setText("I live inside your mobile");
            return true;
        }
        else if (text.contains("what is speech recognition") || text.contains("speech recognition means"))
        {
            ma.speakOut("Speech recognition is the ability of a computer to identify and respond to the sounds produced in human speech");
            ma.txtSpeechInput.setText("Speech recognition is the ability of a computer to identify and respond to the sounds produced in human speech");
            return true;
        }
        else if (text.contains("what is voice recognition") || text.contains("voice recognition means"))
        {
            ma.speakOut("Voice recognition is the ability of a machine or program to receive and interpret dictation, or to understand and carry out spoken commands");
            ma.txtSpeechInput.setText("Voice recognition is the ability of a machine or program to receive and interpret dictation, or to understand and carry out spoken commands");
            return true;
        }
        else if (text.contains("what is the difference between speech recognition and voice recognition") || text.contains("Difference between speech and voice recognition"))
        {
            ma.speakOut("both can be used interchangeably, It is the ability of a computer to identify and respond to the sounds produced in human speech");
            ma.txtSpeechInput.setText("both can be used interchangeably, It is the ability of a computer to identify and respond to the sounds produced in human speech");
            return true;
        }
        else if (text.contains("who are your friends") || text.contains("name your friends"))
        {
            ma.speakOut("I have many like Siri , Google assistant , Alexa and so many that includes you too");
            ma.txtSpeechInput.setText("I have many like Siri , Google assistant , Alexa and so many that includes you too");
            return true;
        }
        else if (text.contains("what is your name") || text.contains("what are you called"))
        {
            ma.speakOut("I am called Shona your assistant");
            ma.txtSpeechInput.setText("I am called Shona your assistant");
            return true;
        }
        else if (text.contains("what can I call you") || text.contains("what are you called") || text.contains("what are you"))
        {
            ma.speakOut("You can call me Shona");
            ma.txtSpeechInput.setText("You can call me Shona");
            return true;
        }
        else if (text.contains("what is the meaning of your name") || text.contains("your name means") || text.contains("Shona means")|| text.contains("what is the meaning of Shona"))
        {
            ma.speakOut("My name has different meaning in simple terms it is something that you love");
            ma.txtSpeechInput.setText("My name has different meaning in simple terms it is something that you love");
            return true;
        }
        else if (text.contains("what are the other voice assistants") || text.contains("Do you know other voice assistants"))
        {
            ma.speakOut("Few Important voice assistants are siri developed by Apple.Google assistant developed by Google and alexa developed by Amazon");
            ma.txtSpeechInput.setText("Few Important voice assistants are siri developed by Apple.Google assistant developed by Google and alexa developed by Amazon");
            return true;
        }

        return false;

    }

}
