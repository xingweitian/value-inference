import value.qual.*;

public class Test {
    int zero = 0;
    String one = "one";
    boolean no = false;
    int two = 2;

    public int add() {
        int sum = 2;
        sum = -6 * sum;
        return sum;
    }

    public int testing(int value) {
        if (two + 2 == 4){
        	return 0;
        }
        return 1;
    }

    public String testing2(int value) {
        return one + 2;
    }

    public int testing4(int value) {
    	two = two + 2;
    	return two;
    }

    public int setx() {
    	int x = 2;
        x = x + x;
        x++;
        x += x;
        x++;
        x = x + x;
    	return x;
    }

    public byte casting(@IntRange(from = 0, to = 255)int value) {
    	return (byte)(value);
    }

    void refineParam(int x, int y) {
        x = 0;
        x = 1;
        y = x;
    }

    int forloop() {
        int x;
        int sum = 0;
        int max = 5;
        for (x = 0; sum < max; x++) {
            sum++;
        }
        return sum;
    }
    
    int whileloop(int max) {
        int x = 0;
        int sum = 0;
        while ( x < max ) {
            sum++;
            x++;
        }
        return sum;
    }
}
