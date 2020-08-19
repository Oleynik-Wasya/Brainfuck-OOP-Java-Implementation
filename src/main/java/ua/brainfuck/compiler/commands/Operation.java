package ua.brainfuck.compiler.commands;

import ua.brainfuck.compiler.app.Memory;

public interface Operation {
    void execute(Memory memory);
}
