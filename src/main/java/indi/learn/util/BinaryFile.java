package indi.learn.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BinaryFile {

	public static byte[] read(String filename) {
		BufferedInputStream bis = null;

		try {
			bis = new BufferedInputStream(new FileInputStream(filename));
			byte[] bytes = new byte[bis.available()];

			bis.read(bytes);

			return bytes;
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
