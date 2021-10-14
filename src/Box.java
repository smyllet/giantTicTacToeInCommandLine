import exception.BoxAlreadyConsumedException;
import exception.BoxNotPlayedException;

public class Box {
    private Player player;
    private boolean lineConsommation;
    private boolean columnConsommation;
    private boolean diagUpDownConsommation;
    private boolean diagDownUpConsommation;

    Box() {
        player = null;
        lineConsommation = false;
        columnConsommation = false;
        diagUpDownConsommation = false;
        diagDownUpConsommation = false;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void consumeLine() throws BoxAlreadyConsumedException, BoxNotPlayedException {
        if(lineConsommation) throw new BoxAlreadyConsumedException();
        else if(player == null) throw new BoxNotPlayedException();
        else lineConsommation = true;
    }

    public void consumeColumn() throws BoxAlreadyConsumedException, BoxNotPlayedException {
        if(columnConsommation) throw new BoxAlreadyConsumedException();
        else if(player == null) throw new BoxNotPlayedException();
        else columnConsommation = true;
    }

    public void consumeDiagUpDown() throws BoxAlreadyConsumedException, BoxNotPlayedException {
        if(diagUpDownConsommation) throw new BoxAlreadyConsumedException();
        else if(player == null) throw new BoxNotPlayedException();
        else diagUpDownConsommation = true;
    }

    public void consumeDiagDownUp() throws BoxAlreadyConsumedException, BoxNotPlayedException {
        if(diagDownUpConsommation) throw new BoxAlreadyConsumedException();
        else if(player == null) throw new BoxNotPlayedException();
        else diagDownUpConsommation = true;
    }

    public boolean isLineConsommation() {
        return lineConsommation;
    }

    public boolean isColumnConsommation() {
        return columnConsommation;
    }

    public boolean isDiagUpDownConsommation() {
        return diagUpDownConsommation;
    }

    public boolean isDiagDownUpConsommation() {
        return diagDownUpConsommation;
    }
}
