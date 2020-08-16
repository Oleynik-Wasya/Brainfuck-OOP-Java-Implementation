package ua.brainfuck.compiler.commands;

import ua.brainfuck.compiler.app.Memory;

public abstract class Operation {
    public Memory memory;

    public Operation(Memory memory) {
        this.memory = memory;
    }

    public abstract void execute();
}
