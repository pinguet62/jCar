package fr.pinguet62.jcar;

import java.io.Closeable;

public abstract class ManagedController implements Closeable {

    protected ManagerController mainController;

    /**
     * Override this method to execute code when this view is hidden.
     * <p>
     * Don't forgot the call super method {@code super.close()} at end of
     * overridden methods.
     */
    @Override
    public void close() {
    }

    void setMainController(ManagerController controller) {
        mainController = controller;
    }

}
