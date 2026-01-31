package com.guardrail;

public final class Main {

    private Main() { }

    public static void main(String[] args) {
        Application app = new Application(new Greeter());
        app.run();
    }
}
