package org.tmplcl.qs.app.ping;

import java.time.LocalDateTime;

public class Ping {

    public int counter;
    public String message;
    public LocalDateTime timestamp;

    public Ping(int counter, String message, LocalDateTime timestamp) {
        this.counter = counter;
        this.message = message;
        this.timestamp = timestamp;
    }
}
