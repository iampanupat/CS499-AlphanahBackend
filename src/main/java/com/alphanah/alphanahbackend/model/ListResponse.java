package com.alphanah.alphanahbackend.model;

import lombok.Getter;

import java.util.Collection;
import java.util.Objects;

@Getter
public class ListResponse {
    private int size;
    private Object data;

    public ListResponse(Object data) {
        if (Objects.isNull(data))
            return;
        this.size = data instanceof Collection ? ((Collection<?>) data).size() : 1;
        this.data = data;
    }
}