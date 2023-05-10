package be.itseasy.itsenvoice.view.controllers.utils;

public enum CRUDActionEnum {
    ADD("add"),
    DELETE("delete"),
    EDIT("edit"),
    SAVE("save"),
    CANCEL("cancel");

    private final String action;

    CRUDActionEnum(String action) {
        this.action=action;
    }

    public String getAction() {
        return action;
    }
    public String getFullBundleKey() {
        return "crud.actions."+getAction();
    }
}
