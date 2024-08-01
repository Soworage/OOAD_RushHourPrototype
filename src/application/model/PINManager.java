package application.model;

public class PINManager {
    private final String pin = "0815";

    public boolean userEnterPIN(String enteredPIN){
        if(enteredPIN.equals(pin)){
            return true;
        }
        return false;
    }

}
