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


        Encryption encryptionMethod = new Encryption();
        Decryption decryptionMethod = new Decryption();
        System.out.println("Сделай выбор: A - шифруем ***** B - дешифруем");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("A")) {

            RandomAccessFile file = new RandomAccessFile("notes3.txt", "rw");
            FileChannel channel = file.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(6000);
            StringBuilder stringBuilder = new StringBuilder();
            channel.read(buffer);
            buffer.flip();
            while (buffer.hasRemaining()) {
                stringBuilder.append((char) buffer.get());
            }
            buffer.clear();
            System.out.println("введите ключ:");
            Scanner sc1 = new Scanner(System.in);
            int key = sc1.nextInt();

            cypher = encryptionMethod.encryption(stringBuilder.toString(), key);

            FileWriter writer = new FileWriter("test11.txt", false);
            writer.write(cypher);
            writer.flush();
        }
        if (choice.equalsIgnoreCase("B")) {
            System.out.println("введите зашифрованный текст:");
            RandomAccessFile file2 = new RandomAccessFile("test11.txt", "rw");
            FileChannel c2 = file2.getChannel();

            ByteBuffer buffer1 = ByteBuffer.allocate(6000);
            StringBuilder sb2 = new StringBuilder();
            c2.read(buffer1);
            buffer1.flip();
            while (buffer1.hasRemaining()) {
                sb2.append((char) buffer1.get());
            }
            buffer1.clear();
            System.out.println("введите ключ:");
                Scanner sc2 = new Scanner(System.in);
                int keyDec = sc2.nextInt();
            cypher2 = decryptionMethod.decrypt(sb2.toString(), keyDec);
        }
    }
}


