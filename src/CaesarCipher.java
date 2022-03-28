import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

public class CaesarCipher {
    public static String cypher;
    public static String cypher2;

    public static void main(String[] args) throws IOException {

        System.out.println("Сделай выбор: A - шифруем ***** B - дешифруем");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();


        if (choice.equalsIgnoreCase("A")) {
            RandomAccessFile file = new RandomAccessFile("notes3.txt", "rw");   //читаю из файла
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
            channel.close();
            System.out.println("введите ключ:");
            int key = keyRec(); // прописываем положительный ключ
            cypher = Encryption.encryption(stringBuilder.toString(), key); //передает зашифрованный текст

            FileWriter writer = new FileWriter("test11.txt", false);
            writer.write(cypher); // записываем шифровку в новый файл
            writer.flush(); //записывает то, что передал write
            writer.close();

            /*
            Извините, но дальше по сути то же самое. Разница только в том, что ДЕШЕФРУЕМ
             */

        }
        if (choice.equalsIgnoreCase("B")) {
            System.out.println("введите зашифрованный текст:");
            RandomAccessFile file2 = new RandomAccessFile("test11.txt", "rw");
            FileChannel c2 = file2.getChannel();

            ByteBuffer buffer1 = ByteBuffer.allocate(60000);
            StringBuilder sb2 = new StringBuilder();
            c2.read(buffer1);
            buffer1.flip();
            while (buffer1.hasRemaining()) {
                sb2.append((char) buffer1.get());
            }
            buffer1.clear();
            System.out.println("введите ключ:");
            int key = keyRec();
            cypher2 = Decryption.decrypt(sb2.toString(), key);
        }
    }

    //рекурсия
    public static int keyRec() {
        Scanner sc3 = new Scanner(System.in);
        int k = sc3.nextInt();
        if (k <= 0) {
            System.out.println("Введите ключ с положительным ключем");
            return keyRec();
        } else {
            int key = k;
            return key;
        }
    }
}



