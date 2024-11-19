package email;

import io.vavr.test.Property;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

import static io.vavr.test.Arbitrary.of;
import static org.assertj.core.api.Assertions.assertThat;

class EncryptionTest {
    private final Encryption encryption = createEncryption();

    @Test
    void encryptString() throws Exception {
        String encryptedText = encryption.encrypt("Unlock Your Potential with the Advent Of Craft Calendar!");
        assertThat(encryptedText)
                .isEqualTo("L7wht/YddOoTvYvrc+wFcZhtXNvZ2cHFxq9ND27h1Ovv/aWLxN8lWv1xMsguM/R4Yodk3rn9cppI+YarggtPjA==");
    }

    @Test
    void encryptDecrypt_shouldReturnOriginalString() {
        // It is a Property-Based test that checks the below property
        // I'm pretty sure we will talk about this concept during our Journey 🎅
        Property.def("decrypt(encrypt(x)) == x for all x")
                .forAll(of("foo", "bar", "baz", "qux"))
                .suchThat(plainText ->
                        encryption.decrypt(
                                encryption.encrypt(plainText)
                        ).equals(plainText)
                )
                .check()
                .assertIsSatisfied();
    }

    @Test
    void decryptEmail() throws Exception {
        String expectedDecryptedEmail = """
        Dear consultant,

        We are facing an unprecedented challenge in Christmas Town.

        The systems that keep our magical operations running smoothly are outdated, fragile, and in dire need of modernization. \nWe urgently require your expertise to ensure Christmas happens this year.
        Our town is located within a mountain circlet at the North Pole, surrounded by high peaks and protected by an advanced communication and shield system to hide it from the outside world.

        You have been selected for your exceptional skills and dedication. \nPlease report to the North Pole immediately. \n
        Enclosed are your travel details and a non-disclosure agreement that you must sign upon arrival.
        Our dwarf friends from the security will receive and escort you in as soon as you check security.
        In the following days, you will receive bracelets to be able to pass through the magic shield.

        Time is of the essence.
        You must arrive before the beginning of December to be able to acclimate yourself with all the systems.

        We are counting on you to help save Christmas.

        Sincerely,

        Santa Claus 🎅""";
        String encryptedEmail = FileUtils.loadFile("EncryptedEmail.txt");
        String decryptedEmail = encryption.decrypt(encryptedEmail);
        assertThat(decryptedEmail).isEqualTo(expectedDecryptedEmail);
    }

    private static Encryption createEncryption() {
        try {
            return new Encryption(
                    new Configuration(convertKey("Advent Of Craft"), convertIv("2024"))
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String convertKey(String key) throws Exception {
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] keyBytes = sha256.digest(key.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(keyBytes);
    }

    private static String convertIv(String iv) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] ivBytes = md5.digest(iv.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(ivBytes);
    }
}
