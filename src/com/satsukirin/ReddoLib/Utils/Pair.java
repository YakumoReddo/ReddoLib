package com.satsukirin.ReddoLib.Utils;


public class Pair<E extends Object, F extends Object> {
    private E first;
    private F second;
    public Pair(){
    }
    public Pair(E f, F s) {
    	first=f;
    	second=s;
    }
    public E getFirst() {
        return first;
    }
    public void setFirst(E first) {
        this.first = first;
    }
    public F getSecond() {
        return second;
    }
    public void setSecond(F second) {
        this.second = second;
    }
}