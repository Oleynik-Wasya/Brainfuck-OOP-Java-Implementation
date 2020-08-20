package ua.brainfuck.compiler.commands;

import ua.brainfuck.compiler.app.Memory;

import java.util.Objects;

public class IncrementCurrentCell implements Operation {

    @Override
    public void execute(Memory memory) {
        memory.incrementCurrentCell();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof IncrementCurrentCell;
    }

    @Override
    public int hashCode() {
        return IncrementCurrentCell.class.hashCode();
    }
}
