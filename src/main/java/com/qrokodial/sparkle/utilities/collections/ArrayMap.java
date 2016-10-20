package com.qrokodial.sparkle.utilities.collections;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;

/**
 * An ordered implementation of the {@link ConcurrentHashMap}
 */
public class ArrayMap<K, V> implements ConcurrentMap<K, V> {
    private ConcurrentMap<K, V> map;
    private List<K> list;

    /**
     * Instantiates the class.
     *
     * @param initialCapacity the initial capacity of the map
     */
    public ArrayMap(int initialCapacity) {
        map = new ConcurrentHashMap<>(initialCapacity);
        list = Collections.synchronizedList(new ArrayList<>(initialCapacity));
    }

    /**
     * Instantiates the class with an initial capacity of 16.
     */
    public ArrayMap() {
        this(16);
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec The default implementation is equivalent to, for this
     * {@code map}:
     * <pre> {@code
     * for ((Map.Entry<K, V> entry : map.entrySet())
     *     action.accept(entry.getKey(), entry.getValue());
     * }</pre>
     *
     * @implNote The default implementation assumes that
     * {@code IllegalStateException} thrown by {@code getKey()} or
     * {@code getValue()} indicates that the entry has been removed and cannot
     * be processed. Operation continues for subsequent entries.
     *
     * @throws NullPointerException {@inheritDoc}
     * @since 1.8
     */
    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        Objects.requireNonNull(action);
        for (K key : list) {
            V value = map.get(key);
            action.accept(key, value);
        }
    }

    /**
     * Returns the number of key-value mappings in this map.  If the
     * map contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of key-value mappings in this map
     */
    @Override
    public int size() {
        return list.size();
    }

    /**
     * Returns <tt>true</tt> if this map contains no key-value mappings.
     *
     * @return <tt>true</tt> if this map contains no key-value mappings
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Returns <tt>true</tt> if this map contains a mapping for the specified
     * key.  More formally, returns <tt>true</tt> if and only if
     * this map contains a mapping for a key <tt>k</tt> such that
     * <tt>(key==null ? k==null : key.equals(k))</tt>.  (There can be
     * at most one such mapping.)
     *
     * @param key key whose presence in this map is to be tested
     * @return <tt>true</tt> if this map contains a mapping for the specified
     * key
     * @throws ClassCastException   if the key is of an inappropriate type for
     *                              this map
     *                              (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified key is null and this map
     *                              does not permit null keys
     *                              (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public boolean containsKey(Object key) {
        return list.contains(key);
    }

    /**
     * Returns <tt>true</tt> if this map maps one or more keys to the
     * specified value.  More formally, returns <tt>true</tt> if and only if
     * this map contains at least one mapping to a value <tt>v</tt> such that
     * <tt>(value==null ? v==null : value.equals(v))</tt>.  This operation
     * will probably require time linear in the map size for most
     * implementations of the <tt>Map</tt> interface.
     *
     * @param value value whose presence in this map is to be tested
     * @return <tt>true</tt> if this map maps one or more keys to the
     * specified value
     * @throws ClassCastException   if the value is of an inappropriate type for
     *                              this map
     *                              (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified value is null and this
     *                              map does not permit null values
     *                              (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    /**
     * Attempts to find the index of the key in the map.
     *
     * @param key the key to search for
     * @return the index of the key in the map, or -1 of the key isn't present
     */
    public int indexOfKey(K key) {
        return list.indexOf(key);
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     * <p>
     * <p>More formally, if this map contains a mapping from a key
     * {@code k} to a value {@code v} such that {@code (key==null ? k==null :
     * key.equals(k))}, then this method returns {@code v}; otherwise
     * it returns {@code null}.  (There can be at most one such mapping.)
     * <p>
     * <p>If this map permits null values, then a return value of
     * {@code null} does not <i>necessarily</i> indicate that the map
     * contains no mapping for the key; it's also possible that the map
     * explicitly maps the key to {@code null}.  The {@link #containsKey
     * containsKey} operation may be used to distinguish these two cases.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     * {@code null} if this map contains no mapping for the key
     * @throws ClassCastException   if the key is of an inappropriate type for
     *                              this map
     *                              (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified key is null and this map
     *                              does not permit null keys
     *                              (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public V get(Object key) {
        return map.get(key);
    }

    /**
     * Associates the specified value with the specified key in this map
     * (optional operation).  If the map previously contained a mapping for
     * the key, the old value is replaced by the specified value.  (A map
     * <tt>m</tt> is said to contain a mapping for a key <tt>k</tt> if and only
     * if {@link #containsKey(Object) m.containsKey(k)} would return
     * <tt>true</tt>.)
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with <tt>key</tt>, or
     * <tt>null</tt> if there was no mapping for <tt>key</tt>.
     * (A <tt>null</tt> return can also indicate that the map
     * previously associated <tt>null</tt> with <tt>key</tt>,
     * if the implementation supports <tt>null</tt> values.)
     * @throws UnsupportedOperationException if the <tt>put</tt> operation
     *                                       is not supported by this map
     * @throws ClassCastException            if the class of the specified key or value
     *                                       prevents it from being stored in this map
     * @throws NullPointerException          if the specified key or value is null
     *                                       and this map does not permit null keys or values
     * @throws IllegalArgumentException      if some property of the specified key
     *                                       or value prevents it from being stored in this map
     */
    @Override
    public V put(K key, V value) {
        if (!list.contains(key)) {
            list.add(key);
        }
        return map.put(key, value);
    }

    /**
     * Inserts the specified element at the specified position in this map (optional operation).  Shifts the element
     * currently at that position (if any) and any subsequent elements to the right (adds one to their indices). If the
     * index is out of bounds, it corrects it to the closest element.
     *
     * @param key the key of the element
     * @param value the value of the element
     * @param index the index to aim for
     */
    public V put(K key, V value, int index) {
        index = Math.max(Math.min(index, size() - 1), 0);

        int i = list.indexOf(key);
        if (i != -1) {
            if (i == index) {
                return map.get(key);
            } else if (i < index) {
                index--;
            }
            list.remove(key);
        }

        list.add(index, key);
        return map.put(key, value);
    }

    /**
     * Removes the mapping for a key from this map if it is present
     * (optional operation).   More formally, if this map contains a mapping
     * from key <tt>k</tt> to value <tt>v</tt> such that
     * <code>(key==null ?  k==null : key.equals(k))</code>, that mapping
     * is removed.  (The map can contain at most one such mapping.)
     * <p>
     * <p>Returns the value to which this map previously associated the key,
     * or <tt>null</tt> if the map contained no mapping for the key.
     * <p>
     * <p>If this map permits null values, then a return value of
     * <tt>null</tt> does not <i>necessarily</i> indicate that the map
     * contained no mapping for the key; it's also possible that the map
     * explicitly mapped the key to <tt>null</tt>.
     * <p>
     * <p>The map will not contain a mapping for the specified key once the
     * call returns.
     *
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with <tt>key</tt>, or
     * <tt>null</tt> if there was no mapping for <tt>key</tt>.
     * @throws UnsupportedOperationException if the <tt>remove</tt> operation
     *                                       is not supported by this map
     * @throws ClassCastException            if the key is of an inappropriate type for
     *                                       this map
     *                                       (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException          if the specified key is null and this
     *                                       map does not permit null keys
     *                                       (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public V remove(Object key) {
        list.remove(key);
        return map.remove(key);
    }

    /**
     * Copies all of the mappings from the specified map to this map
     * (optional operation).  The effect of this call is equivalent to that
     * of calling {@link #put(Object, Object) put(k, v)} on this map once
     * for each mapping from key <tt>k</tt> to value <tt>v</tt> in the
     * specified map.  The behavior of this operation is undefined if the
     * specified map is modified while the operation is in progress.
     *
     * @param m mappings to be stored in this map
     * @throws UnsupportedOperationException if the <tt>putAll</tt> operation
     *                                       is not supported by this map
     * @throws ClassCastException            if the class of a key or value in the
     *                                       specified map prevents it from being stored in this map
     * @throws NullPointerException          if the specified map is null, or if
     *                                       this map does not permit null keys or values, and the
     *                                       specified map contains null keys or values
     * @throws IllegalArgumentException      if some property of a key or value in
     *                                       the specified map prevents it from being stored in this map
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        m.keySet().stream().filter(key -> !list.contains(key)).forEach(list::add);
        map.putAll(m);
    }

    /**
     * Removes all of the mappings from this map (optional operation).
     * The map will be empty after this call returns.
     *
     * @throws UnsupportedOperationException if the <tt>clear</tt> operation
     *                                       is not supported by this map
     */
    @Override
    public void clear() {
        list.clear();
        map.clear();
    }

    /**
     * Returns a {@link Set} view of the keys contained in this map. The set is NOT backed by the map, so changes made
     * here won't change the underling map.
     *
     * @return a set view of the keys contained in this map
     */
    @Override
    public Set<K> keySet() {
        return Collections.unmodifiableSet(map.keySet());
    }

    /**
     * Returns a {@link List} view of the keys contained in this map. The list is NOT backed by the map, so changes
     * made here won't change the underling map.
     *
     * @return a list view of the keys contained in this map
     */
    public List<K> keyList() {
        return Collections.unmodifiableList(list);
    }

    /**
     * Returns a {@link Collection} view of the values contained in this map. This collection is NOT backed by the map,
     * so changes made here won't change the underlying map.
     *
     * @return a collection view of the values contained in this map
     */
    @Override
    public Collection<V> values() {
        return Collections.unmodifiableCollection(map.values());
    }

    /**
     * Returns a {@link Set} view of the mappings contained in this map. The set is NOT backed by the map, so changes
     * made here won't change the underling map.
     *
     * @return a set view of the mappings contained in this map
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
        return Collections.unmodifiableSet(map.entrySet());
    }

    /**
     * If the specified key is not already associated
     * with a value, associate it with the given value.
     * This is equivalent to
     * <pre> {@code
     * if (!map.containsKey(key))
     *   return map.put(key, value);
     * else
     *   return map.get(key);
     * }</pre>
     * <p>
     * except that the action is performed atomically.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with the specified key, or
     * {@code null} if there was no mapping for the key.
     * (A {@code null} return can also indicate that the map
     * previously associated {@code null} with the key,
     * if the implementation supports null values.)
     * @throws UnsupportedOperationException if the {@code put} operation
     *                                       is not supported by this map
     * @throws ClassCastException            if the class of the specified key or value
     *                                       prevents it from being stored in this map
     * @throws NullPointerException          if the specified key or value is null,
     *                                       and this map does not permit null keys or values
     * @throws IllegalArgumentException      if some property of the specified key
     *                                       or value prevents it from being stored in this map
     * @implNote This implementation intentionally re-abstracts the
     * inappropriate default provided in {@code Map}.
     */
    @Override
    public V putIfAbsent(K key, V value) {
        if (!list.contains(key)) {
            list.add(key);
        }
        return map.putIfAbsent(key, value);
    }

    /**
     * Removes the entry for a key only if currently mapped to a given value.
     * This is equivalent to
     * <pre> {@code
     * if (map.containsKey(key) && Objects.equals(map.get(key), value)) {
     *   map.remove(key);
     *   return true;
     * } else
     *   return false;
     * }</pre>
     * <p>
     * except that the action is performed atomically.
     *
     * @param key   key with which the specified value is associated
     * @param value value expected to be associated with the specified key
     * @return {@code true} if the value was removed
     * @throws UnsupportedOperationException if the {@code remove} operation
     *                                       is not supported by this map
     * @throws ClassCastException            if the key or value is of an inappropriate
     *                                       type for this map
     *                                       (<a href="../Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException          if the specified key or value is null,
     *                                       and this map does not permit null keys or values
     *                                       (<a href="../Collection.html#optional-restrictions">optional</a>)
     * @implNote This implementation intentionally re-abstracts the
     * inappropriate default provided in {@code Map}.
     */
    @Override
    public boolean remove(Object key, Object value) {
        boolean result = map.remove(key, value);
        if (result) {
            list.remove(key);
        }
        return result;
    }

    /**
     * Replaces the entry for a key only if currently mapped to a given value.
     * This is equivalent to
     * <pre> {@code
     * if (map.containsKey(key) && Objects.equals(map.get(key), oldValue)) {
     *   map.put(key, newValue);
     *   return true;
     * } else
     *   return false;
     * }</pre>
     * <p>
     * except that the action is performed atomically.
     *
     * @param key      key with which the specified value is associated
     * @param oldValue value expected to be associated with the specified key
     * @param newValue value to be associated with the specified key
     * @return {@code true} if the value was replaced
     * @throws UnsupportedOperationException if the {@code put} operation
     *                                       is not supported by this map
     * @throws ClassCastException            if the class of a specified key or value
     *                                       prevents it from being stored in this map
     * @throws NullPointerException          if a specified key or value is null,
     *                                       and this map does not permit null keys or values
     * @throws IllegalArgumentException      if some property of a specified key
     *                                       or value prevents it from being stored in this map
     * @implNote This implementation intentionally re-abstracts the
     * inappropriate default provided in {@code Map}.
     */
    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        return map.replace(key, oldValue, newValue);
    }

    /**
     * Replaces the entry for a key only if currently mapped to some value.
     * This is equivalent to
     * <pre> {@code
     * if (map.containsKey(key)) {
     *   return map.put(key, value);
     * } else
     *   return null;
     * }</pre>
     * <p>
     * except that the action is performed atomically.
     *
     * @param key   key with which the specified value is associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with the specified key, or
     * {@code null} if there was no mapping for the key.
     * (A {@code null} return can also indicate that the map
     * previously associated {@code null} with the key,
     * if the implementation supports null values.)
     * @throws UnsupportedOperationException if the {@code put} operation
     *                                       is not supported by this map
     * @throws ClassCastException            if the class of the specified key or value
     *                                       prevents it from being stored in this map
     * @throws NullPointerException          if the specified key or value is null,
     *                                       and this map does not permit null keys or values
     * @throws IllegalArgumentException      if some property of the specified key
     *                                       or value prevents it from being stored in this map
     * @implNote This implementation intentionally re-abstracts the
     * inappropriate default provided in {@code Map}.
     */
    @Override
    public V replace(K key, V value) {
        return map.replace(key, value);
    }
}
