
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
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("A")) {

            String stringVal = readFileFromHd("notes3.txt");
            System.out.println("введите ключ:");
            int key = keyRec(); // прописываем положительный ключ
            cypher = Encryption.encrypt(stringVal, key); //передает зашифрованный текст

            FileWriter writer = new FileWriter("test11.txt", false);
            writer.write(cypher); // записываем шифровку в новый файл
            writer.flush(); //записывает то, что передал write
            writer.close();

        }else if (choice.equalsIgnoreCase("B")) {
            System.out.println("введите зашифрованный текст:");

            String stringVal = readFileFromHd("test11.txt");
            System.out.println("введите ключ:");
            int key = keyRec();
            cypher = Decryption.decrypt(stringVal, key);
        }else{
            System.out.println("Вы должны ввести либо А, либо В латиницей!");
        }
    }

    //рекурсия
    public static int keyRec() {
        Scanner scanner = new Scanner(System.in);
        int checkNum;
        while (!scanner.hasNextInt()) {
            System.out.println("Это не число!");
            scanner.next();
        }
        checkNum = scanner.nextInt();
        if (checkNum <= 0) {
            System.out.println("Введите положительное число!");
            return keyRec();
        }
        return checkNum;
    }
    private static String readFileFromHd(String path) throws IOException {
        RandomAccessFile file = new RandomAccessFile(path, "rw");   //читаю из файла
        FileChannel channel = file.getChannel();  // из file открывает канал
        ByteBuffer buffer = ByteBuffer.allocate(60000);  // заполнет буфер тестом
        StringBuilder stringBuilder = new StringBuilder(); // записывает текст в стрингбилдер
        channel.read(buffer); //читает информацию из файла с помощью channel
        buffer.flip(); //из режима записи в чтение
        while (buffer.hasRemaining()) { // читает
            stringBuilder.append((char) buffer.get()); // читает и кастит в чар присоединяет из буфера
            // в стрингбилдер
        }
        buffer.clear(); // грубо говоря, затирает буфер с 0 позиции и зеписывает новую информацию

        return stringBuilder.toString();
    }
}




