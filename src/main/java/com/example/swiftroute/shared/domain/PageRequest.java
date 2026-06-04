package com.example.swiftroute.shared.domain;

public class PageRequest {
    private final int page;
    private final int size;

    public PageRequest(int page, int size) {
        if(page<0) throw new IllegalArgumentException("Page index must not be negative");
        if(size < 1 || size > 100) throw new IllegalArgumentException("Page size must be between 1 and 100");
        this.page = page;
        this.size = size;
    }

    public int getOffset() {
        return page * size;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    
}
