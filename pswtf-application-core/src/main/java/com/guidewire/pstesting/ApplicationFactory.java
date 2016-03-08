package com.guidewire.pstesting;

public interface ApplicationFactory<T extends Application> {
    T create();
}