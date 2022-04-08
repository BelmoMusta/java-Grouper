package org.mustabelmo.grouper;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * A class to group elements by the provided key
 *
 * @param <K> The key type
 * @param <V> the values type
 */
public abstract class Grouper<K, V> {
    private final Map<K, Collection<V>> internalMap;
    private final GroupingStrategy<K, V> groupingStrategy;

    protected Grouper(GroupingStrategy<K, V> groupingStrategy) {
        this.groupingStrategy = groupingStrategy;
        this.internalMap = groupingStrategy.getMapStrategy();
    }

    public V put(K key, V value) {
        Collection<V> collection = get(key);
        if (collection.isEmpty()) {
            collection = groupingStrategy.getCollection();
            internalMap.put(key, collection);
        }
        collection.add(value);
        return value;
    }

    /**
     * Remove all the values identified by a key from the group
     * @param key
     * @return
     */
    public Collection<V> remove(K key) {
        return internalMap.remove(key);
    }

    /**
     *
     * @param key
     * @param value
     * @return
     */
    public boolean remove(K key, V value) {
        Collection<V> vs = get(key);
        return vs.remove(value);
    }


    public Collection<V> get(K key) {
        return Optional.ofNullable(internalMap.get(key))
                .orElse(groupingStrategy.getCollection());
    }

    @Override
    public String toString() {
        return internalMap.toString();
    }

    public Collection<V> reduce() {
        Collection<V> collection = groupingStrategy.getCollection();
        internalMap.forEach((k, v) -> collection.addAll(v));
        return collection;
    }
}
