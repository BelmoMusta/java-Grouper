package org.mustabelmo.grouper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

/**
 * A class to group elements by the provided key
 *
 * @param <K> The key type
 * @param <V> the values type
 */
public abstract class Grouper<K, V> implements Iterable<V>{
    private final Map<K, Collection<V>> internalMap;
    private final GroupingStrategy<K, V> groupingStrategy;

    protected Grouper(GroupingStrategy<K, V> groupingStrategy) {
        this.groupingStrategy = groupingStrategy;
        this.internalMap = groupingStrategy.getMapStrategy();
    }

    public V put(K key, V value) {
        Collection<V> collection = internalMap.get(key);
        if (collection == null) {
            collection = groupingStrategy.getCollection();
            internalMap.put(key, collection);
        }
        collection.add(value);
        return value;
    }
    
    public Collection<V> putAll(K key, Collection<V> values) {
        Collection<V> collection = internalMap.get(key);
        if (collection == null) {
            collection = groupingStrategy.getCollection();
            internalMap.put(key, collection);
        }
        collection.addAll(values);
        return values;
    }
    
    public Collection<K> findGroupsOf(V value) {
        Collection<K> groupsOfV = new ArrayList<>();
        internalMap.forEach((k,v) -> {
            if (v.contains(value)){
                groupsOfV.add(k);
            }
        });
        return groupsOfV;
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
        reduceTo(collection);
        return collection;
    }
    
    public void reduceTo(Collection<V> collection) {
        internalMap.forEach((k, v) -> collection.addAll(v));
    }
    private static class InternalIterator<R> implements Iterator<R> {
        final Iterator<R> innerIterator;
    
        private InternalIterator(Collection<R> innerCollection) {
            innerIterator = innerCollection.iterator();
        }
    
        @Override
        public boolean hasNext() {
            return innerIterator.hasNext();
        }
    
        @Override
        public R next() {
            return innerIterator.next();
        }
    }
    @Override
    public Iterator<V> iterator() {
        return new InternalIterator<>(reduce());
    }
}
