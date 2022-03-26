public class EncryptionMethod {
    public EncryptionMethod() {
    }
    public String Encryption(String str, int key) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            char charIn = str.charAt(i);
            if (charIn >= 'a' && charIn <= 'z') {
                charIn += key % 26;
                if (charIn < 'a')
                    charIn += 26;
                if (charIn > 'z')
                    charIn -= 26;
            } else if (charIn >= 'A' && charIn <= 'Z') {
                charIn += key % 26;
                if (charIn < 'A')
                    charIn += 26;
                if (charIn > 'Z')
                    charIn -= 26;
            }
            result += charIn;
        }
        System.out.println(str + "\n\n" + "Текст зашифрован:" + "\n\n" + result);
        return result;
    }
}
