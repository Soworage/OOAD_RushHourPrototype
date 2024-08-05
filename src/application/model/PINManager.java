package application.model;


/* Hauptverantwortlicher: Alex Mihel */

public class PINManager {

    public boolean userEnterPIN(String enteredPIN) {
        String pin = "0815";
        return enteredPIN.equals(pin);
    }

}
