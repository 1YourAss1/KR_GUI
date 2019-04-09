
public class GOST_28147_89 {

    private int[][] StringToBinary(String in) {
        char[] symbols = in.toCharArray();
        int bitarray[][] = new int[symbols.length][8];

        for (int i = 0; i < symbols.length; i++) {
            int value = symbols[i] - 848;
            for (int j = 0, k = 7; j < 8; j++, k--) {
                bitarray[i][k] = value & 0x1;
                value = value >> 1;
            }
        }
        return bitarray;
    }

    private int[][] BreakIntoBlocks(int arr[][], int f) {
        int newArr[][] = new int[8][4];
        int k = 0;
        int l;
        if (f == 1) {
            l = 0;
        } else l = 4;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                newArr[i][j] = arr[l][k];
                if (k == 7) {
                    k = 0;
                    l++;
                } else k++;
            }
        }
        return newArr;
    }

    private int[][] BitSumm32(int firstArr[][], int secondArr[][]){

        String f = "";
        String s = "";
        for (int i = 0; i < firstArr.length; i++) {
            for (int j = 0; j < firstArr[i].length; j++) {
                f = f + String.valueOf(firstArr[i][j]);
                s = s + String.valueOf(secondArr[i][j]);
            }
        }
        long a = (Long.parseLong(f, 2) + Long.parseLong(s, 2)) % (long)Math.pow(2, 32);
        String str = Long.toBinaryString(a);

        char[] symbols = str.toCharArray();
        int summArr[][] = new int[8][4];
        int k = 0;
        for (int i = 0; i < summArr.length & k < symbols.length; i++) {
            for (int j = 0; j < summArr[i].length; j++) {
                summArr[i][j] = (int)symbols[k] - 48;
                k++;
            }
        }
        return summArr;
    }

    private int[][] SMatrix(int arr[][]) {
        int bitArr[] = new int[8];
        int newArr[][] = new int[8][4];
        int matrix[][] = {
                {1,13,4,6,7,5,14,4},
                {15,11,11,12,13,8,11,10},
                {13,4,10,7,10,1,14,9},
                {0,1,0,1,1,13,12,2},
                {5,3,7,5,0,10,6,13},
                {7,15,2,15,8,3,13,8},
                {10,5,1,13,9,4,15,0},
                {4,9,13,8,15,2,10,14},
                {9,0,3,4,14,14,2,6},
                {2,10,6,10,4,15,3,11},
                {3,14,8,9,6,12,8,1},
                {14,7,5,14,12,7,1,12},
                {6,6,9,0,11,6,0,7},
                {11,8,12,3,2,0,7,15},
                {8,2,15,11,5,9,5,5},
                {12,12,14,2,3,11,9,3}};


        for (int i = 0; i < arr.length; i++) {
            String sline = new String();
            for (int j = 0; j < arr[i].length; j++) {
                sline = sline + String.valueOf(arr[i][j]);
            }
            bitArr[i] = matrix[Integer.parseInt(sline, 2)][i];
        }

        for (int i = 0; i < bitArr.length; i++) {
            int value = bitArr[i];
            for (int j = 0, k = 3; j < 4; j++, k--) {
                newArr[i][k] = value & 0x1;
                value = value >> 1;
            }
        }
        return newArr;
    }

    private int[][] Shift(int arr[][]){
        int newArr[][] = new int[arr.length][arr[0].length];
        int x[] = new int[32];
        int k = 0;
        for (int i = 0; i < arr.length & k < 32; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                x[(k+21)%32] = arr[i][j];
                k++;
            }
        }
        k = 0;
        for (int i = 0; i < arr.length & k < 31; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                newArr[i][j] = x[k];
                k++;
            }
        }
        return newArr;
    }

    private int[][] BitSumm(int firstArr[][], int secondArr[][]) {
        int summArr[][] = new int[firstArr.length][firstArr[0].length];
        for (int i = 0; i < firstArr.length; i++) {
            for (int j = 0; j < firstArr[i].length; j++) {
                summArr[i][j] = firstArr[i][j] ^ secondArr[i][j];
            }
        }
        return summArr;
    }

    public String encrypt(String inText, String inKey) {
        int bitArray[][] = StringToBinary(inText);

        inText = "L1 = ";
        int LArray[][] = BreakIntoBlocks(bitArray, 1);
        for (int i = 0; i < LArray.length; i++) {
            for (int j = 0; j < LArray[i].length; j++) {
                inText = inText + LArray[i][j];
            }
            inText = inText + " ";
        }

        inText = inText + "\nR1 = ";
        int RArray[][] = BreakIntoBlocks(bitArray, 2);
        for (int i = 0; i < RArray.length; i++) {
            for (int j = 0; j < RArray[i].length; j++) {
                inText = inText + RArray[i][j];
            }
            inText = inText + " ";
        }

        int key[][] = StringToBinary(inKey);
        key = BreakIntoBlocks(key, 1);
        inText = inText + "\nK = ";
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key[i].length; j++) {
                inText = inText + key[i][j];
            }
            inText = inText + " ";
        }

        int summ[][] = BitSumm32(RArray, key);
        inText = inText + "\nS = ";
        for (int i = 0; i < summ.length; i++) {
            for (int j = 0; j < summ[i].length; j++) {
                inText = inText + summ[i][j];
            }
            inText = inText + " ";
        }

        int mat[][] = SMatrix(summ);
        inText = inText + "\nSm = ";
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                inText = inText + mat[i][j];
            }
            inText = inText + " ";
        }

        int f[][] = Shift(mat);
        inText = inText + "\nf = ";
        for (int i = 0; i < f.length; i++) {
            for (int j = 0; j < f[i].length; j++) {
                inText = inText + f[i][j];
            }
            inText = inText + " ";
        }

        int R2Array[][] = BitSumm(f,LArray);
        int L2Array[][] = RArray;

        inText = inText + "\n" + "L2 = ";
        for (int i = 0; i < L2Array.length; i++) {
            for (int j = 0; j < L2Array[i].length; j++) {
                inText = inText + L2Array[i][j];
            }
            inText = inText + " ";
        }

        inText = inText + "\nR2 = ";
        for (int i = 0; i < R2Array.length; i++) {
            for (int j = 0; j < R2Array[i].length; j++) {
                inText = inText + R2Array[i][j];
            }
            inText = inText + " ";
        }
        return inText;
    }
}
