package christmas;

public class ExceptionHandler {

    public static void triggerArgException(ErrorMessage message){
        OutputView.printErrorMessage(message);
        throw new IllegalArgumentException(message.getMessage());
    }
}
