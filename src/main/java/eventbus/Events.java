package eventbus;

import controller.ControllerCommands;

public class Events {
    private Events() {}

    public interface Event {}

    // начало игровой сессии
    public record StartNewSession() implements Event {}

    // событие передает команду управления
    public record KeyPressed(ControllerCommands value) implements Event {}

    // ввод имени
    public record EnteredNameAddChar(char userName) implements Event {};
    public record EnteredNameSubmit() implements Event {}
    public record EnteredNameBackspace() implements Event {}

    // обновляем экран
    public record RenderRefresh() implements Event {};

    // Начало новой игры
    public record StartNewGame() implements Event {};

    // Сохранить игру
    public record SaveGame() implements Event {};

    // Загрузить игру
    public record LoadGame() implements Event {};

    // таблица рекордов
    public record HallOfFame() implements Event {};

    // выход
    public record QuitRequest() implements Event {};
}
