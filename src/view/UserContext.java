package view;

import java.util.Observable;

import model.SkinPropertiesIO;

public class UserContext extends Observable {

    private SkinPropertiesIO skinIO;

    /**
     * @return the skinIO
     */
    public SkinPropertiesIO getSkinIO() {
        return skinIO;
    }

    /**
     * @param skinIO the skinIO to set
     */
    public void setSkinIO(SkinPropertiesIO skinIO) {
        this.skinIO = skinIO;
        this.setChanged();
        this.notifyObservers(skinIO);
    }
    
}
