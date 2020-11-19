package methods;

class SecurityManager {
    private final String password;
    private int index;
    private final int len;

    SecurityManager(String p) {
        password = p;
        index = 0;
        len = password.length();
    }

    int getPermutation() {//1,2,3
        //pasword.length() % 3 + 1;
        int sum = 0;
        int i;

        //sum of ASCII values of characters of password
        for (i = 0; i < len; i++)
            sum += (int) password.charAt(i);

        return sum % 3 + 1;
    }

    int primaryCrypto(int x) {
        int y = x ^ (int) password.charAt(index);
        index = (index + 1) % len;
        return y;
    }

}
