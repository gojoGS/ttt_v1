package App.Model;

import java.util.*;

public class GameState {
    Map<Player, List<Position>> positions;
    Player currentPlayer;

    public GameState() {
        positions = new HashMap<>();
        positions.put(Player.BLUE, new ArrayList<>());
        positions.put(Player.RED, new ArrayList<>());

        currentPlayer = Player.RED;
    }

    public boolean isOccupied(Position position) {
        if(positions.get(Player.BLUE).contains(position)
                || positions.get(Player.RED).contains(position)) {
            return true;
        } else {
            return false;
        }
    }

    private void changePlayer() {
        currentPlayer = currentPlayer.equals(Player.RED) ? Player.BLUE : Player.RED;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void endTurn() {
        changePlayer();
    }

    public void addSelection(Position position) {
        positions.get(currentPlayer).add(position);
    }

    public boolean isCurrentPlayerWinner() {
        var currentPlayerPositions = positions.get(currentPlayer);

        if(currentPlayerPositions.size() < 3) {
            return false;
        }

        var rowFrequency = new HashMap<Integer, Integer>();
        var colFrequency = new HashMap<Integer, Integer>();

        for (var pos: currentPlayerPositions) {
            rowFrequency.put(pos.y(), 1 + rowFrequency.getOrDefault(pos.y(), 0));
            colFrequency.put(pos.x(), 1 + colFrequency.getOrDefault(pos.x(), 0));
        }

        if(rowFrequency.containsValue(3) || colFrequency.containsValue(3)) {
            return true;
        } else {
            return false;
        }

    }
}
