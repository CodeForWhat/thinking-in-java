package indi.learn.resuing;

class Game {
	Game(int i) {
		System.out.println("Game constructor");
	}
}

class BoardGame extends Game {
	BoardGame(int i) {
		super(i);
		System.out.println("BoardGame constructor");
	}
}

public class Chess extends BoardGame {
	Chess() {
		super(11);   // using super keyword with argument list to call base class constructors with arguments
		System.out.println("Chess constructor");
	}

	public static void main(String[] args) {
		Chess x = new Chess();
	}
}
