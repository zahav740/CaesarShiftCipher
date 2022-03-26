
public class DecryptionMethod {
    public DecryptionMethod() {
    }

    public String Decrypt(String str, int key) {
        int keyIn = Integer.parseInt("-" + key);
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            char charIn = str.charAt(i);
            if (charIn >= 'a' && charIn <= 'z') {
                charIn += keyIn % 26;
                if (charIn < 'a')
                    charIn += 26;
                if (charIn > 'z')
                    charIn -= 26;
            } else if (charIn >= 'A' && charIn <= 'Z') {
                charIn += keyIn % 26;
                if (charIn < 'A')
                    charIn += 26;
                if (charIn > 'Z')
                    charIn -= 26;
            }
            result += charIn;
        }
        System.out.println();
        System.out.println(str + "\n\n" + "Текст расшифрован:" + "\n\n" + result);
        return result;
    }
}
