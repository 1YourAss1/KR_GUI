

public class test {

    private static int[][] StringToBinary(String in) {
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



    public static void main(String[] args) {
        String text = "_";
        /*String text = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ _" +
                "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";*/
        char t[] = text.toCharArray();
        int arr[][] = StringToBinary(text);
        for (int i = 0; i < arr.length; i++) {
            if ((int)t[i] == 32 | (int)t[i] == 95) {
                System.out.print(t[i] + " " + ((int)t[i]) + " ");
            } else {
                System.out.print(t[i] + " " + ((int)t[i] - 848) + " ");
            }

            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }
}
