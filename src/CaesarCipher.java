
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

public class CaesarCipher {
    public static String cypher;

    public static void main(String[] args) throws IOException {
        System.out.println("Сделай выбор: A - шифруем ***** B - дешифруем");
        makeChoice();

        FileWriter writer = new FileWriter("test11.txt", false);
        writer.write(cypher); // записываем шифровку в новый файл
        writer.flush(); //записывает то, что передал write
        writer.close();
    }

    //рекурсия
    public static int keyRecursion() {
        Scanner scanner = new Scanner(System.in);
        int checkNum;
        while (!scanner.hasNextInt()) {
            System.out.println("Это не число!");
            scanner.next();
        }
        checkNum = scanner.nextInt();
        if (checkNum <= 0) {
            System.out.println("Введите положительное число!");
            return keyRecursion();
        }
        return checkNum;
    }

    private static String readFileFromHd(String path) throws IOException {

        RandomAccessFile fileToRead = new RandomAccessFile(path, "rw");   //читаю из файла
        FileChannel openChannel = fileToRead.getChannel();  // из fileToRead открывает канал
        ByteBuffer buffer = ByteBuffer.allocate(60000);  // заполнет буфер тестом
        StringBuilder stringBuilder = new StringBuilder(); // записывает текст в стрингбилдер
        openChannel.read(buffer); //читает информацию из файла с помощью OpenChannel
        buffer.flip(); //из режима записи в чтение
        while (buffer.hasRemaining()) { // читает
            stringBuilder.append((char) buffer.get()); // читает и кастит в чар присоединяет из буфера
            // в стрингбилдер
        }
        buffer.clear(); // грубо говоря, затирает буфер с 0 позиции и зеписывает новую информацию
        openChannel.close();
        fileToRead.close();
        return stringBuilder.toString();
    }

    public static String makeChoice() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String buttonChoice = scanner.nextLine();
        if (buttonChoice.equalsIgnoreCase("A")) {

            String stringVal = readFileFromHd("notes3.txt");
            System.out.println("Введите текст:" + "\n\n" + stringVal + "\n");
            System.out.println("введите ключ:");
            int key = keyRecursion(); // прописываем положительный ключ
            cypher = Encryption.encryptText(stringVal, key); //передает зашифрованный текст
        }
        if (buttonChoice.equalsIgnoreCase("B")) {

            String stringVal = readFileFromHd("test11.txt");
            System.out.println("Введён зашифрованный текст:" + "\n\n" + stringVal + "\n");
            System.out.println("введите ключ:");
            int key = keyRecursion();
            cypher = Decryption.textDecrypt(stringVal, key);
        }
        if (!buttonChoice.equalsIgnoreCase("A") && !buttonChoice.equalsIgnoreCase("B")) {
            System.out.println("Вы должны ввести либо А, либо В латиницей!");
            return makeChoice();
        }
        return buttonChoice;
    }
}




