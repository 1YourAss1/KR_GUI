
public class HASH {
    private int p = 13, q = 17, n = p*q, H0 = 4; //H0=8
    public int getH0() {
        return H0;
    }

    public int[] hashing(String inText) {
        char cText[] = inText.toCharArray();
        int M[] = new int[cText.length];
        int H[] = new int[M.length];

        for (int i = 0; i < cText.length; i++) {
            if (cText[i] >= 'А' && cText[i] <= 'Е') {
                M[i] = (int)cText[i] - 1039;
            } else if (cText[i] >= 'Ж' && cText[i] <= 'Я') {
                M[i] = (int)cText[i] - 1038;
            }
        }
        H[0] = ((int)Math.pow((double)(H0 + M[0]), 2)) % n;
        for (int i = 1; i < M.length; i++) {
            H[i] = ((int)Math.pow((double)(H[i-1] + M[i]), 2)) % n;
        }

        return H;
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
