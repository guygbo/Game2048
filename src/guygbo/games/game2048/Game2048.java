package guygbo.games.game2048;

public class Game2048 {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Game aGame = new Game();
        View aView = new View();
        Controller aController = new Controller(aGame, aView);
        aView.setVisible(true);
    }
}
