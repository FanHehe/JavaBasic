import java.util.Arrays;

public final class MyStringBuffer implements java.io.Serializable {

	private char[] value;

	private int count = 0;

	public MyStringBuffer() {
		this(16);
	}

	public MyStringBuffer(int capacity) {
		value = new char[capacity];
	}

	public MyStringBuffer(String str) {
		this(str.length() + 16);
		append(str);
	}


	public MyStringBuffer append(String str) {

		if (null == str) {
			return this.append("null");
		}

		int len = str.length();

		if (len + count > value.length) {
			resize(len + count);
		}

		int base = count;
		for (int i = 0; i < len; i++, count++) {
			value[base + i] = str.charAt(i);
		}

		return this;
	}

	public int length() {
		return count;
	}

	public int capacity() {
		return value.length;
	}

	public void resize(int minCapacity) {
		value = Arrays.copyOf(value, minCapacity);
	}

	@Override
	public String toString() {
		char[] item = Arrays.copyOf(value, count);
		return new String(item);
	}


	public static void main(String[] args) {
		MyStringBuffer msb = new MyStringBuffer();

		msb.append("123456789");
		msb.append("987654321");
		msb.append("123").append(null);

		System.out.println(msb);
	}

}