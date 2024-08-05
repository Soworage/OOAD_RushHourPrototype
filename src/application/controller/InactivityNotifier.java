package application.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/* Hauptverantwortlicher: Matthias Henzel */
public class InactivityNotifier {
    private static final int NOTIFY_INACTIVITY_SECONDS = 180; // 3 minutes
    private static final int RETURN_TO_MAIN_MENU_SECONDS = 300; // 5 minutes

    private static InactivityNotifier instance;

    private final Coordinator coordinator;
    private Timeline inactivityTimer;
    private boolean notified;
    private int secondsInactive;

    private InactivityNotifier(Coordinator coordinator) {
        this.coordinator = coordinator;
        initializeTimer();
        System.out.println("InactivityManager instance created.");
    }

    public static InactivityNotifier getInstance(Coordinator coordinator) {
        if (instance == null) {
            instance = new InactivityNotifier(coordinator);
            System.out.println("New InactivityManager instance initialized.");
        } else {
            System.out.println("Existing InactivityManager instance retrieved.");
        }
        return instance;
    }

    private void initializeTimer() {
        inactivityTimer = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateInactivity()));
        inactivityTimer.setCycleCount(Timeline.INDEFINITE);
        System.out.println("Inactivity timer initialized.");
    }

    public void startTimer() {
        inactivityTimer.stop();
        secondsInactive = 0;
        notified = false;
        inactivityTimer.playFromStart();
        System.out.println("Inactivity timer reset and started.");
    }

    public void resetTimer() {
        inactivityTimer.stop();
        secondsInactive = 0;
        notified = false;
        System.out.println("Inactivity timer reset and paused.");
    }

    private void updateInactivity() {
        secondsInactive++;
        System.out.println("Inactivity timer updated: " + secondsInactive + " seconds of inactivity.");

        if (secondsInactive == NOTIFY_INACTIVITY_SECONDS && !notified) {
            notifyInactivity();
            notified = true;
        }

        if (secondsInactive == RETURN_TO_MAIN_MENU_SECONDS) {
            returnToMainMenu();
        }
    }

    private void notifyInactivity() {
        System.out.println("Inactivity notification: No interaction detected for 3 minutes. The application will return to the main menu soon.");
        // TODO: Notify the user about the inactivity
    }

    private void returnToMainMenu() {
        inactivityTimer.stop();
        System.out.println("Inactivity reached 5 minutes. Returning to the main menu.");
        coordinator.showMainMenu();
    }
}
