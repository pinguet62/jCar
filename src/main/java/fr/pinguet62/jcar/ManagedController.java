package fr.pinguet62.jcar;

public abstract class ManagedController {

    protected ManagerController mainController;

    /** Override this method to execute code when this view is hidden. */
    public void onClose() {
    }

    public void setMainController(ManagerController controller) {
        mainController = controller;
    }

}
