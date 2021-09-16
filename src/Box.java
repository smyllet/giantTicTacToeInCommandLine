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

    public void consumeLine() {
        if(player != null) lineConsommation = true;
    }

    public void consumeColumn() {
        if(player != null) columnConsommation = true;
    }

    public void consumeDiagUpDown() {
        if(player != null) diagUpDownConsommation = true;
    }

    public void consumeDiagDownUp() {
        if(player != null) diagDownUpConsommation = true;
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

    @Override
    public String toString() {
        if(this.player == null) return " ";
        else if(isLineConsommation() || isColumnConsommation() || isDiagUpDownConsommation() || isDiagDownUpConsommation()) return "\u001B[36m" + player.getToken() + "\u001B[0m";
        else return "\u001B[31m" + player.getToken() + "\u001B[0m";
    }
}
