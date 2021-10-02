package App.GUI;

import App.Model.GameState;
import App.Model.Position;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GameController {
    GameState gameState;

    @FXML
    GridPane gridPane;

    @FXML
    Label msg;

    @FXML
    public void initialize() {
        gameState = new GameState();

        for (int i = 0; i< 3; ++i) {
            for (int j = 0; j< 3; ++j) {
                var circle = new Circle(64, Color.TRANSPARENT);

                circle.setOnMouseClicked(this::onCircleClick);

                gridPane.add(circle, i, j);
            }
        }
    }

    private Position getCirclePosition(Circle circle) {
        int x = GridPane.getColumnIndex(circle);
        int y = GridPane.getRowIndex(circle);

        return new Position(x, y);
    }

    private void onCircleClick(MouseEvent event) {
        var node = (Circle) event.getSource();
        var pos = getCirclePosition(node);

        if(gameState.isOccupied(pos)) {
            msg.setText("Jól van az úgy!");
            return;
        }

        msg.setText("");
        gameState.addSelection(pos);

        switch (gameState.getCurrentPlayer()) {
            case RED -> node.setFill(Color.RED);
            case BLUE -> node.setFill(Color.BLUE);
        }

        if(gameState.isCurrentPlayerWinner()) {
            msg.setText(String.format("%s is winner", gameState.getCurrentPlayer()));
        } else {
            gameState.endTurn();

        }
    }

}
