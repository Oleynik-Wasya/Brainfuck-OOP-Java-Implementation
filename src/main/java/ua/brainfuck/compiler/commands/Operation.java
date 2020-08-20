package ua.brainfuck.compiler.commands;

import ua.brainfuck.compiler.app.Memory;
import ua.brainfuck.compiler.visitor.Visitor;

public interface Operation {
    void execute(Memory memory);
}
