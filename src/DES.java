
public class DES {

    private int[][] StringToBinary(String in) {
        char[] symbols = in.toCharArray();
        int bitarray[][] = new int[symbols.length][8];

        for (int i = 0; i < symbols.length; i++) {
            int value;
            if (symbols[i] == 32 | symbols[i] == 95) {
                value = symbols[i];
            } else {
                value = symbols[i] - 848;
            }
            for (int j = 0, k = 7; j < 8; j++, k--) {
                bitarray[i][k] = value & 0x1;
                value = value >> 1;
            }
        }
        return bitarray;
    }

    private int[][] ItialPermutation(int arr[][]) {
        int x[] = new int[64];
        for (int i = 0, k = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++, k++) {
                x[k] = arr[i][j];
            }
        }
        int IP[] = {58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4,
                    62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48, 40, 32, 24, 16, 8,
                    57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19, 11, 3,
                    61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7};
        int nx[] = new int[x.length];
        for (int i = 0; i < x.length; i++) {
            nx[i] = x[IP[i] - 1];
        }
        for (int i = 0, k = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++, k++) {
                arr[i][j] = nx[k];
            }
        }
        return arr;
    }

    private int[] FinalPermutation(int Larr[][], int Rarr[][]){
        int x[] = new int[64];
        int k = 0;
        for (int i = 0; i < Larr.length; i++) {
            for (int j = 0; j < Larr[i].length; j++, k++) {
                x[k] = Larr[i][j];
            }
        }
        for (int i = 0; i < Larr.length; i++) {
            for (int j = 0; j < Larr[i].length; j++, k++) {
                x[k] = Rarr[i][j];
            }
        }
        int IP[] = {40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47, 15, 55, 23, 63, 31,
                    38, 6, 46, 14, 54, 22, 62, 30, 37, 5, 45, 13, 53, 21, 61, 29,
                    36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19, 59, 27,
                    34, 2, 42, 10, 50, 18, 58, 26, 33, 1, 41, 9, 49, 17, 57, 25};
        int nx[] = new int[x.length];
        for (int i = 0; i < x.length; i++) {
            nx[i] = x[IP[i] - 1];
        }
        return nx;
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

    private int[][] Increase(int arr[][]) {
        int increaseArr[][] = new int[8][6];
        increaseArr[0][0] = arr[7][3];
        increaseArr[7][5] = arr[0][0];
        for (int i = 0; i < 8; i++) {
            if (i == 0) {
                increaseArr[i][5] = arr[i + 1][0];
            } else if (i == 7) {
                increaseArr[i][0] = arr[i - 1][3];
            } else {
                increaseArr[i][0] = arr[i - 1][3];
                increaseArr[i][5] = arr[i + 1][0];
            }
            for (int j = 1; j < 5; j++) {
                increaseArr[i][j] = arr[i][j - 1];
            }
        }
        return increaseArr;
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

    private int[][] SMatrix(int arr[][]) {
        int bitArr[] = new int[8];
        int newArr[][] = new int[8][4];
        int S[][][] = {
                {
                    {14,4,13,1,2,15,11,8,3,10,6,12,5,9,0,7},
                    {0,15,7,4,14,2,13,1,10,6,12,11,9,5,3,8},
                    {4,1,14,8,13,6,2,11,15,12,9,7,3,10,5,0},
                    {15,12,8,2,4,9,1,7,5,11,3,14,10,0,6,13}},
                {
                    {15,1,8,14,6,11,3,4,9,7,2,13,12,0,5,10},
                    {3,13,4,7,15,2,8,14,12,0,1,10,6,9,11,5},
                    {0,14,7,11,10,4,13,1,5,8,12,6,9,3,2,15},
                    {13,8,10,1,3,15,4,2,11,6,7,12,0,5,14,9}},
                {
                    {10,0,9,14,6,3,15,5,1,13,12,7,11,4,2,8},
                    {13,7,0,9,3,4,6,10,2,8,5,14,12,11,15,1},
                    {13,6,4,9,8,15,3,0,11,1,2,12,5,10,14,7},
                    {1,10,13,0,6,9,8,7,4,15,14,3,11,5,2,12}},
                {
                    {7,13,14,3,0,6,9,10,1,2,8,5,11,12,4,15},
                    {13,8,11,5,6,15,0,3,4,7,2,12,1,10,14,9},
                    {10,6,9,0,12,11,7,13,15,1,3,14,5,2,8,4},
                    {3,15,0,6,10,1,13,8,9,4,5,11,12,7,2,14}},
                {
                    {2,12,4,1,7,10,11,6,8,5,3,15,13,0,14,9},
                    {14,11,2,12,4,7,13,1,5,0,15,10,3,9,8,6},
                    {4,2,1,11,10,13,7,8,15,9,12,5,6,3,0,14},
                    {11,8,12,7,1,14,2,13,6,15,0,9,10,4,5,3}},
                {
                    {12,1,10,15,9,2,6,8,0,13,3,4,14,7,5,11},
                    {10,15,4,2,7,12,9,5,6,1,13,14,0,11,3,8},
                    {9,14,15,5,2,8,12,3,7,0,4,10,1,13,11,6},
                    {4,3,2,12,9,5,15,10,11,14,1,7,6,0,8,13}},
                {
                    {4,11,2,14,15,0,8,13,3,12,9,7,5,10,6,1},
                    {13,0,11,7,4,9,1,10,14,3,5,12,2,15,8,6},
                    {1,4,11,13,12,3,7,14,10,15,6,8,0,5,9,2},
                    {6,11,13,8,1,4,10,7,9,5,0,15,14,2,3,12}},
                {
                    {13,2,8,4,6,15,11,1,10,9,3,14,5,0,12,7},
                    {1,15,13,8,10,3,7,4,12,5,6,11,0,14,9,2},
                    {7,11,4,1,9,12,14,2,0,6,10,13,15,3,5,8},
                    {2,1,14,7,4,10,8,13,15,12,9,0,3,5,6,11}}
        };

        for (int i = 0; i < 8; i++) {
            String sline = new String();
            String scolumn = new String();
            sline = sline + String.valueOf(arr[i][0] +""+ arr[i][5]);
            for (int j = 1; j < 5; j++) {
                scolumn = scolumn + String.valueOf(arr[i][j]);
            }
            bitArr[i] = S[0][(Integer.parseInt(sline, 2))][(Integer.parseInt(scolumn, 2))];
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

    private int[][] P_box(int arr[][]){
    int Pbox[] = {16, 7, 20, 21, 29, 12, 28, 17,
                1, 15, 23, 26, 5, 18, 31, 10,
                2, 8, 24, 14, 32, 27, 3, 9,
                19, 13, 30, 6, 22, 11, 4, 25};

    int x[] = new int[32];
        for (int i = 0, k = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++, k++) {
                x[k] = arr[i][j];
            }
        }
    int nx[] = new int[x.length];
        for (int i = 0; i < x.length; i++) {
            nx[i] = x[Pbox[i] - 1];
        }

        for (int i = 0, k = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++, k++) {
                arr[i][j] = nx[k];
            }
        }
    return arr;
    }

    private int[][] KeyGeneration(String in) {
        int result[][] = new int[8][6];
        int P1box[] = {
                57,49,41,33,25,17,9,
                1,58,50,42,34,26,18,
                10,2,59,51,43,35,27,
                19,11,3,60,52,44,36,
                63,55,47,39,31,23,15,
                7,62,54,46,38,30,22,
                14,6,61,53,45,37,29,
                21,13,5,28,20,12,4
        };
        int P2box[] = {
                14,17,11,24,1,5,3,28,
                15,6,21,10,23,19,12,4,
                26,8,16,7,27,20,13,2,
                41,52,31,37,47,55,30,40,
                51,45,33,48,44,49,39,56,
                34,53,46,42,50,36,29,32};
        char[] symbols = in.toCharArray();
        int bitarray[] = new int[symbols.length * 8];

        int k = 0;
        for (int i = 0; i < symbols.length; i++) {
            int value;
            if (symbols[i] == 32 | symbols[i] == 95) {
                value = symbols[i];
            } else {
                value = symbols[i] - 848;
            }
            for (int j = 0, l = 7; j < 8; j++, l--) {
                bitarray[k + l] = value & 0x1;
                value = value >> 1;
            }
            k += 8;
        }

        int p1_transformation[] = new int[56];
        for (int i = 0; i < p1_transformation.length; i++) {
            p1_transformation[i] = bitarray[P1box[i] - 1];
        }

        int newbitarray[] = new int[p1_transformation.length];
        for (int i = 0; i < 27; i++) {
            newbitarray[i] = p1_transformation[i + 1];
        }
        newbitarray[27] = p1_transformation[0];
        for (int i = 28; i < 55; i++) {
            newbitarray[i] = p1_transformation[i + 1];
        }
        newbitarray[55] = p1_transformation[28];
        int p2_transformation[] = new int[48];
        for (int i = 0; i < p2_transformation.length; i++) {
            p2_transformation[i] = newbitarray[P2box[i] - 1];
        }

        for (int i = 0, l = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++, l++) {
                result[i][j] = p2_transformation[l];
            }
        }
        return result;
    }

    public String encrypt(String inText, String inKey) {

        int bitArray[][] = StringToBinary(inText);
        int inPerm[][] = ItialPermutation(bitArray);

        inText = "L1 = ";
        int LArray[][] = BreakIntoBlocks(inPerm, 1);
        for (int i = 0; i < LArray.length; i++) {
            for (int j = 0; j < LArray[i].length; j++) {
                inText = inText + LArray[i][j];
            }
            inText = inText + " ";
        }

        inText = inText + "\nR1 = ";
        int RArray[][] = BreakIntoBlocks(inPerm, 2);
        for (int i = 0; i < RArray.length; i++) {
            for (int j = 0; j < RArray[i].length; j++) {
                inText = inText + RArray[i][j];
            }
            inText = inText + " ";
        }

        int RIncreaseArr[][] = Increase(RArray);
        inText = inText + "\nRр = ";
        for (int i = 0; i < RIncreaseArr.length; i++) {
            for (int j = 0; j < RIncreaseArr[i].length; j++) {
                inText = inText + RIncreaseArr[i][j];
            }
            inText = inText + " ";
        }

        int key[][] = KeyGeneration(inKey);
        inText = inText + "\nK  =  ";
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key[i].length; j++) {
                inText = inText + key[i][j];
            }
            inText = inText + " ";
        }

        int S2[][] = BitSumm(RIncreaseArr, key);
        inText = inText + "\nS2 = ";
        for (int i = 0; i < S2.length; i++) {
            for (int j = 0; j < S2[i].length; j++) {
                inText = inText + S2[i][j];
            }
            inText = inText + " ";
        }

        int Sm[][] = SMatrix(S2);
        inText = inText + "\nSm = ";
        for (int i = 0; i < Sm.length; i++) {
            for (int j = 0; j < Sm[i].length; j++) {
                inText = inText + Sm[i][j];
            }
            inText = inText + " ";
        }

        int Sp[][] = P_box(Sm);
        inText = inText + "\nПерестановка:\nSp = ";
        for (int i = 0; i < Sp.length; i++) {
            for (int j = 0; j < Sp[i].length; j++) {
                inText = inText + Sp[i][j];
            }
            inText = inText + " ";
        }

        inText = inText + "\nR2 = ";
        int R2Array[][] = BitSumm(Sp, LArray);
        int L2Array[][] = RArray;

        for (int i = 0; i < R2Array.length; i++) {
            for (int j = 0; j < R2Array[i].length; j++) {
                inText = inText + R2Array[i][j];
            }
            inText = inText + " ";
        }

        inText = inText + "\n" + "L2 = ";
        for (int i = 0; i < L2Array.length; i++) {
            for (int j = 0; j < L2Array[i].length; j++) {
                inText = inText + L2Array[i][j];
            }
            inText = inText + " ";
        }

        inText = inText + "\nОбьединение и конечная перестановка:\n";
        int finPerm[] = FinalPermutation(L2Array, R2Array);
        for (int i = 0; i < finPerm.length; i++) {
            inText = inText + finPerm[i];
        }
        return inText;
    }
}
