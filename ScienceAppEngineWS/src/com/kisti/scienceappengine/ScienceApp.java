package com.kisti.scienceappengine;

public class ScienceApp {
	private final long id;
    private final String content;

    public ScienceApp(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
