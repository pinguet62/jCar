package fr.pinguet62.jcar;

public abstract class ManagedController {

    protected ManagerController mainController;

    public void setMainController(ManagerController controller) {
        mainController = controller;
    }

}
