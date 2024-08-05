package application.controller;

import application.view.SceneManager;

/**
 * Das Interface InitializableController definiert eine Methode zur Initialisierung
 * eines Controllers mit einem SceneManager. Es ermöglicht die flexible
 * Initialisierung von Controllern, die unterschiedliche Szenen verwalten.
 */
public interface InitializableController {

    /**
     * Initialisiert den Controller mit einem gegebenen SceneManager.
     * Diese Methode sollte aufgerufen werden, um dem Controller den
     * erforderlichen Zugriff auf den SceneManager zu geben.
     *
     * @param sceneManager Der SceneManager, der zur Verwaltung von Szenen verwendet wird.
     */
    void initializeWithSceneManager(SceneManager sceneManager);
}
