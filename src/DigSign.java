
public class DigSign {
    private int H;
    private int publicKey[] = new int[2];
    private int privateKey[] = new int[2];
    private HASH hash;
    private RSA rsa;

    public int getH() {
        return H;
    }

    public int[] getPrKey() {
        return privateKey;
    }

    public int creatSign(String inText, int d){
        int s;
        hash = new HASH();
        H = hash.hashing(inText)[hash.hashing(inText).length - 1];

        rsa = new RSA();
        rsa.GenerateKeys(d);
        privateKey = rsa.getPrivateKey();
        publicKey = rsa.getPublicKey();

        s = (int)rsa.modexp(H, privateKey[0], privateKey[1]);
        return s;
    }
}
