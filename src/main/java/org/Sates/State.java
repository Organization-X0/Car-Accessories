package org.Sates;

public interface State {
    public void handle();
    public void handleInput(Object input);
    public String getStateString();
}
