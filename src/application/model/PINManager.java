package application.model;

public class PINManager {
    private final String pin = "0815";

    public boolean userEnterPIN(String enteredPIN) {
        return enteredPIN.equals(pin);
    }

}
