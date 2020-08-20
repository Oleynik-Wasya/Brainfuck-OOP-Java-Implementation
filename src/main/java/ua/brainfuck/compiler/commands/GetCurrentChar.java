package ua.brainfuck.compiler.commands;

import ua.brainfuck.compiler.app.Memory;

import java.util.Objects;

public class GetCurrentChar implements Operation {

    @Override
    public void execute(Memory memory) {
        System.out.print(memory.getCurrentChar());
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof GetCurrentChar;
    }

    @Override
    public int hashCode() {
        return GetCurrentChar.class.hashCode();
    }
}
