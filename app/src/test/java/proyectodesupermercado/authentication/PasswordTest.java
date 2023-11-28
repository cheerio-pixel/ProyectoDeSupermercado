package proyectodesupermercado.authentication;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordTest {
    @Test
    public void whenUsingHasher_thenValid() {
        char[] unHashed = "test".toCharArray();
        Password password = new HashPasswordFactory.PBKDF2HashPasswordFactory().createPassword(unHashed);
        assertTrue(password.checkPassword(unHashed));
    }

    @Test
    public void whenSerializingPassword_thenPasswordIsTheSame() throws IOException, ClassNotFoundException {
        char[] unHashed = "test".toCharArray();
        Password password = new HashPasswordFactory.PBKDF2HashPasswordFactory().createPassword(unHashed);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("test.txt"));
        objectOutputStream.writeObject(password);
        objectOutputStream.flush();
        objectOutputStream.close();


        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("test.txt"));
        Password password1 = (Password) objectInputStream.readObject();
        objectInputStream.close();

        assertTrue(password1.checkPassword(unHashed));
    }
}
