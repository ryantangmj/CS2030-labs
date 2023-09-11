import java.util.Optional;
import java.util.Map;

class KeyableMap<V extends Keyable> implements Keyable {
    private final String identifier;
    private final ImmutableMap<String, V> map;

    KeyableMap(String identifier) {
        this.identifier = identifier;
        this.map = new ImmutableMap<String, V>();
    }

    KeyableMap(String identifier, ImmutableMap<String, V> map) {
        this.identifier = identifier;
        this.map = map;
    }

    ImmutableMap<String, V> getMap() {
        return map;
    }

    @Override
    public String getKey() {
        return identifier;
    }

    KeyableMap<V> put(V item) {
        return new KeyableMap<V>(identifier, map.put(item.getKey(), item));
    }

    Optional<V> get(String key) {
        return map.get(key);
    }
    
    String getValue() {
        String output  = "";

        for (Map.Entry<String, V> e: map) {
            output += String.format("%s, ",  e.getValue());
        }

        return output.length() == 0 ? output : output.substring(0, output.length() - 2);
    }

    @Override
    public String toString() {
        return String.format("%s: {%s}", identifier, this.getValue());
    }
}
