package ua.brainfuck.compiler.commands;

import ua.brainfuck.compiler.app.Memory;

import java.util.Objects;

public class GetCurrentChar implements Operation {

    @Override
    public void execute(Memory memory) {
        System.out.print(memory.getCurrentChar());
    }
}
