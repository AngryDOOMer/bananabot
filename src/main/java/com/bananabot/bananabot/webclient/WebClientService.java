package com.bananabot.bananabot.webclient;

public interface WebClientService<T, V> {

    V call(T t);
}
