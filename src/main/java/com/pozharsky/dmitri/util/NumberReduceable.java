package com.pozharsky.dmitri.util;

public interface NumberReduceable<R,T>{
    R reduce (T rawNumber);
}
