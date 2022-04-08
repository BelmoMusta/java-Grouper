package org.mustabelmo.grouper;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Grouping elements by {@link K}, the resulted groups will be implemented using {@link ArrayList}
 *
 * @param <K> The key type
 * @param <V> the values type
 */
public class HashSetGrouper<K, V> extends Grouper<K, V> {

    public HashSetGrouper() {
        super(HashSet::new);
    }
}
