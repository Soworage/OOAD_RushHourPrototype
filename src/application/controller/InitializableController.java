package application.controller;

import application.view.SceneManager;

public interface InitializableController {
    void initializeWithSceneManager(SceneManager sceneManager);
}
