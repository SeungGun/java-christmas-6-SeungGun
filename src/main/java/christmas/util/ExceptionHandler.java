package christmas.util;

import christmas.ui.OutputView;
import christmas.util.ErrorMessage;

public class ExceptionHandler {

    public static void triggerArgException(ErrorMessage message){
        OutputView.printErrorMessage(message);
        throw new IllegalArgumentException(message.getMessage());
    }
}
