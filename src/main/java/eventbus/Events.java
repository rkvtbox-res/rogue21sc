package eventbus;

import controller.ControlKeys;

public class Events {
    private Events() {}

    public interface Event {}

    public record KeyPressed(ControlKeys value) implements Event {}
    public record Quit() implements Event {}


}
