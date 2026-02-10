package eventbus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class EventBus {
    //создаем структуру ключ:значение где ключ тип события (тянем из класса Events) - значение вызываемые хендлеры (методы)
    private final Map<Class<?>, List<Consumer<?>>> eventHandlers = new HashMap<>();

    // создаем обобщенный метод (принимаем на вход событие и метод - пишем это в мапу)
    public <T> void subscribe (Class<T> eventType, Consumer<T> handler) {
        if (!eventHandlers.containsKey(eventType)) {
            eventHandlers.put(eventType, new CopyOnWriteArrayList<>());
        }
        List<Consumer<?>> list = eventHandlers.get(eventType);
        list.add(handler);
    }

    public void post (Object eventType) {
        List<Consumer<?>> list = eventHandlers.get(eventType.getClass());

        if (list == null) {
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            Consumer<Object> handler = (Consumer<Object>) list.get(i);
            handler.accept(eventType);
        }
    }





}
