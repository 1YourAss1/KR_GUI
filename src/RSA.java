import java.util.Random;

public class RSA {
    private int publicKey;
    private int privateKey;
    private int p = 13, q = 17, n = p * q, f = (p - 1)*(q - 1); //13 19

    public int getP(){
        return p;
    }
    public int getQ() {
        return q;
    }
    public int getN() {
        return n;
    }
    public int getF() {
        return f;
    }
    public int getD() {return privateKey;}
    public int getE() {return publicKey;}
    public int[] getPublicKey() {
        int key[] = {publicKey, n};
        return key;
    }

    public int[] getPrivateKey() {
        int key[] = {privateKey, n};
        return key;
    }

    public void GenerateKeys(int d) {
        if (d == 0) {
            Random random = new Random();
            privateKey = random.nextInt(f);
            while (gcd(privateKey, f) != 1) {
                privateKey = random.nextInt(f);
            }
        } else privateKey = d;
        while (publicKey < f & (privateKey*publicKey - 1) % f != 0){
            publicKey++;
        }
    }

    private static int gcd(int a, int b) {
        int t;
        while(b != 0){
            t = a;
            a = b;
            b = t%b;
        }
        return a;
    }

    public long[] EncryptText(String inText) {
        char cText[] = inText.toCharArray();
        long c[] = new long[cText.length];
        long encrText[] = new long[cText.length];
        for (int i = 0; i < cText.length; i++) {
            if (cText[i] >= 'А' && cText[i] <= 'Е') {
                c[i] = (int)cText[i] - 1039;
            } else if (cText[i] >= 'Ж' && cText[i] <= 'Я') {
                c[i] = (int)cText[i] - 1038;
            }
        }
        for (int i = 0; i < encrText.length; i++) {
            encrText[i] = modexp(c[i], publicKey, n);
        }
        return encrText;
    }

    public String DecryptText(long inText[]) {
        String decrText = "";
        for (int i = 0; i < inText.length; i++) {
            decrText = decrText + (char) modexp(inText[i], privateKey, n);
        }
        return decrText;
    }

    public long modexp(long x, long y, long N)  {
        if (y == 0) return 1;
        long z = modexp(x, y / 2, N);
        if (y % 2 == 0)
            return (z*z) % N;
        else
            return (x*z*z) % N;
    }
}
