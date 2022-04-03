package org.mustabelmo.grouper;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * The grouping strategy used in the implementations of the {@link Grouper} class.
 *
 * @param <V> The type of the keys.
 * @param <K> The type of the values.
 */
public interface GroupingStrategy<K, V> {
    Collection<V> getCollection();

    default Map<K, Collection<V>> getMapStrategy() {
        return new HashMap<>();
    }
}
