package com.kashyapbari.gameoflife.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class Set<T> {
    private HashSet<T> set;

    public Boolean add(T entity){
        return set.add(entity);
    }

    public Boolean remove(T entity){
        return set.remove(entity);
    }

    public Boolean contains(T entity){
        return set.contains(entity);
    }

    public Integer size(){
        return set.size();
    }


    public Set<T> union(Set<T> set){
        HashSet<T> unionSet = new HashSet<T>(this.set);
        unionSet.addAll(set.getSet());
        return new Set<>(unionSet);
    }

    public Set<T> intersection(Set<T> set){
        HashSet<T> intersectSet = new HashSet<>(this.set);
        intersectSet.retainAll(set.getSet());
        return new Set<>(intersectSet);
    }

    public Set<T> diff(Set<T> set){
        HashSet<T> diffSet = new HashSet<>(this.set);
        diffSet.removeAll(set.getSet());
        return new Set<>(diffSet);
    }
    
    public Boolean addAll(Set<T> set){
        return this.set.addAll(set.getSet());
    }

    public Boolean removeAll(Set<T> set){
        return this.set.removeAll(set.getSet());
    }

    public void clear(){
        this.set.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Set<?> set1 = (Set<?>) o;
//        return Objects.equals(getSet(), set1.getSet());
        return getSet().containsAll(set1.getSet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSet());
    }

    @Override
    public String toString() {
        return "Set{" +set +
                '}';
    }
}
