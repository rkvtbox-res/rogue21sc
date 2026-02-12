package eventbus;

import controller.ControllerCommands;

public class Events {
    private Events() {}

    public interface Event {}

    // постит конроллер

    public record KeyPressed(ControllerCommands value) implements Event {}
    public record RenderRefresh() implements Event {};




}
