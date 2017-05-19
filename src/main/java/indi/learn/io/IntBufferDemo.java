package indi.learn.io;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class IntBufferDemo {

	public static void main(String[] args) {
		ByteBuffer bb = ByteBuffer.allocate(1024);
		IntBuffer ib = bb.asIntBuffer();
		ib.put(new int[] { 11, 42, 47, 99, 143, 811, 1016 });
		System.out.println(ib.get(3));
		System.out.println(bb.get(2));
		ib.put(3, 1181);
		ib.flip();
		while (ib.hasRemaining()) {
			int i = ib.get();
			System.out.println(i);
		}

	}

}
