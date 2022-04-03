package org.mustabelmo.grouper;

import java.util.ArrayList;

/**
 * Grouping elements by {@link K}, the resulted groups will be implemented using {@link ArrayList}
 *
 * @param <K> The key type
 * @param <V> the values type
 */
public class ArrayListGrouper<K, V> extends Grouper<K, V> {

    public ArrayListGrouper() {
        super(ArrayList::new);
    }
}
