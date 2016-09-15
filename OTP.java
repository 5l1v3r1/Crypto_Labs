import java.io.File;
import java.io.FileNotFoundException;
import java.util.Base64;
import java.util.Random;
import java.util.Scanner;

public class OTP {

    public static void main(String[] args) throws FileNotFoundException {
        File flag = new File("flag.txt");
        Scanner sc = new Scanner(flag);
        String plaintext = sc.nextLine();
        sc.close();

        OTP(plaintext);
    }

    /*
    one time pads are secure!!
    no way you'll be able to crack this
     */
    static void OTP(String plaintext) {
        Random r = new Random();

        //test to make sure our OTP is random!
        //I heard that to make sure your OTP is secure your RNG must be random
        int[] rand = new int[256];
        for (int i = 0; i < 256; i++) {
            int x = r.nextInt();
            System.out.println(x); // DEBUG
            rand[i] = x;
        }

        System.out.println("-------");
        if (stdev(rand) > 1e9) {
            String encrypted = "";
            for (int i = 0; i < plaintext.length(); i++) {
                encrypted += (char) (Math.abs((plaintext.charAt(i) ^ r.nextInt()) % 128));
            }
            System.out.println(Base64.getEncoder().encodeToString(encrypted.getBytes()));
        }
    }

    static double stdev(int[] list) {
        double mean = 0.0;
        for (int x : list) {
            mean += x;
        }
        mean = mean / list.length;
        double sum = 0.0;
        for (int x : list) {
            sum += Math.pow(x - mean, 2);
        }
        return Math.sqrt(sum / list.length);
    }
}
