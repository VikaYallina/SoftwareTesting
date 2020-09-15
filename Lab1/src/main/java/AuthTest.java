import org.junit.jupiter.api.Test;

import javax.swing.text.View;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthTest {
    @Test
    public void testValidation(){
        assertTrue(ViewController.validate("loginEX", "pass123"));
    }

    @Test
    public void testIsEmpty(){
        assertTrue(ViewController.isTextFieldEmpty(" "));
        assertTrue(ViewController.isTextFieldEmpty(""));
    }
}
