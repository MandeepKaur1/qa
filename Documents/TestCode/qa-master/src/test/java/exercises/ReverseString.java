package exercises;

import org.testng.annotations.Test;

import static jxl.biff.FormatRecord.logger;

/**
 * Created by mandeep
 */
public class ReverseString {

    private String str = "peednam";

    @Test
    public void reverse_a_string() throws Exception {
        logger.info("Actual String is " + str);
        char ch[] = str.toCharArray();
        String reversedStr = "";
        logger.info("Start the loop to reverse a String");
        for (int i = ch.length - 1; i >= 0; i--) {
            reversedStr += ch[i];
        }
        logger.info("Reversed String is " + reversedStr);
    }
}
