package inference;

import value.qual.*;

public class Arithmetics {
	
	String s = "";
	int x = 10;
	int y = 20;
	
	void addition(int val) {
		// String addition
		@StringVal String ss = s + s;
		@StringVal String sx = x + s;
		@StringVal String xs = s + x;
		
		// Constant addition
		@IntRange(from=30, to=30) int xy = x + y;
		@IntRange(from=30, to=30) int cons = 10 + 20;
		@IntRange(from=30, to=30) int xcons = x + 20;
		@IntRange(from=30, to=30) int ycons = 10 + y;
		
		// Range addition
		@UnknownVal int xval = x + val;
		@UnknownVal int yval = val + y;
		@UnknownVal int vval = val + val;
	}
	
	void subtraction(int val) {
		// Constant addition
		@IntRange(from=-10, to=-10) int xy = x - y;
		@IntRange(from=-10, to=-10) int cons = 10 - 20;
		@IntRange(from=-10, to=-10) int xcons = x - 20;
		@IntRange(from=-10, to=-10) int ycons = 10 - y;
		
		// Range addition
		@UnknownVal int xval = x - val;
		@UnknownVal int yval = val - y;
		@UnknownVal int vval = val - val;
	}
	
	void multiplication(int val) {
		// Constant addition
		@IntRange(from=200, to=200) int xy = x * y;
		@IntRange(from=200, to=200) int cons = 10 * 20;
		@IntRange(from=200, to=200) int xcons = x * 20;
		@IntRange(from=200, to=200) int ycons = 10 * y;
		
		// Range addition
		@UnknownVal int xval = x * val;
		@UnknownVal int yval = val * y;
		@UnknownVal int vval = val * val;
	}
	
	void division(int val) {
		// Constant addition
		@IntRange(from=0, to=0) int xy = x / y;
		@IntRange(from=2, to=2) int cons = 20 / 10;
		@IntRange(from=0, to=0) int xcons = x / 20;
		@IntRange(from=0, to=0) int ycons = 10 / y;
		
		// Range addition
		@UnknownVal int xval = x / val;
		@UnknownVal int yval = val / y;
		@UnknownVal int vval = val / val;
	}
	
	void remainder(int val) {
		// Constant addition
		@IntRange(from=10, to=10) int xy = x % y;
		@IntRange(from=0, to=0) int cons = 20 % 10;
		@IntRange(from=10, to=10) int xcons = x % 20;
		@IntRange(from=10, to=10) int ycons = 10 % y;
		
		// Range addition
		@UnknownVal int xval = x % val;
		@UnknownVal int yval = val % y;
		@UnknownVal int vval = val % val;
	}

}
