import java.util.Optional;
import java.util.function.Function;

class Log<T> {
    private final T t;
    private final String log;

    Log(T t) {
        this.t = t;
        this.log = "";
    }

    Log(T t, String log) {
        this.t = t;
        this.log = log;
    }

    static <T> Log<T> of(T t) {
        return Log.<T>of(t, "");
    }

    static <T> Log<T> of(T t, String log) {
        return Optional.ofNullable(t)
            .filter(x -> !(x instanceof Log<?>))
            .flatMap(x -> Optional.ofNullable(log))
            .map(x -> new Log<T>(t, log))
            .orElseThrow(() -> new IllegalArgumentException("Invalid arguments"));
    }
    
    <R> Log<R> map(Function<? super T, ? extends R> mapper) {
        return Log.<R>of(mapper.apply(t), log);
    }
    
    <U> Log<U> flatMap(Function<? super T, ? extends Log<? extends U>> mapper) {
        Log<? extends U> l = mapper.apply(t);
        String newLog = String.format("%s\n%s", log, l.log);
        return Log.<U>of(l.t, newLog.trim());
    }

    T getT() {
        return t;
    }

    String getLog() {
        return log;
    }

    @Override
    public String toString() {
        return log.length() == 0 ? "Log[" + t + "]" : "Log[" + t + "]\n" + log;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Log<?> l) {
            return log.equals(l.getLog()) && t.equals(l.getT());
        }

        return false;
    }
    
}
