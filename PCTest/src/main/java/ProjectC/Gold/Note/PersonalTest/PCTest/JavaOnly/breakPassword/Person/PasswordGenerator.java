package ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.breakPassword.Person;

import java.util.Random;

public class PasswordGenerator {
    public static String generatePassword() {
        byte[] randomByte = new byte[5];
        for (int i = 0; i < randomByte.length; i++) {
            randomByte[i] = (byte) (33 + new Random().nextInt(90));
        }
        return new String(randomByte);
    }

    ;
}
