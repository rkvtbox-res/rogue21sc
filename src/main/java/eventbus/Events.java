package eventbus;

public class Events {
    private Events() {}

    public interface Event {}

    public record KeyPressed(controller.ControlKeys value) implements Event {}
    public record Quit() implements Event {}


}
