package Lesson8Ex01;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FunctionSelenide {

    protected boolean assertFormat(String example) {
        Pattern pattern = Pattern.compile("\\d{0,3}\\s\\d{0,3}\\s\\d{0,3}\\.\\d{0,2}\\s\\D");
        Matcher matcher = pattern.matcher(example);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
}

