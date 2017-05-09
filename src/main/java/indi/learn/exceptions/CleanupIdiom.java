package indi.learn.exceptions;

class NeedsCleanup {// the constructor can not failed
	private static long counter;
	private final long id = counter++;

	public void dispose() {
		System.out.println("NeedsCleanup " + id + "has been cleaned");
	}
}

class ConstructionException extends Exception {
}

class NeedsCleanup2 extends NeedsCleanup {
	public NeedsCleanup2() throws ConstructionException {
	}
}

public class CleanupIdiom {

	public static void main(String[] args) {
		NeedsCleanup nc1 = new NeedsCleanup();

		try {

		} finally {
			nc1.dispose();
		}

		// if there are multiple objects
		NeedsCleanup nc2 = new NeedsCleanup();
		NeedsCleanup nc3 = new NeedsCleanup();
		NeedsCleanup nc4 = new NeedsCleanup();

		try {
		} finally {
			// release object in reverse order
			nc4.dispose();
			nc3.dispose();
			nc2.dispose();
		}

		// while the constructor throw exception we need nested try-catch blocks
		try {
			NeedsCleanup2 ncc1 = new NeedsCleanup2();
			try {
				NeedsCleanup2 ncc2 = new NeedsCleanup2();
				try {

				} finally {
					ncc2.dispose();
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				ncc1.dispose();
			}
		} catch (ConstructionException e) {
			System.out.println(e);
		}
	}
}
