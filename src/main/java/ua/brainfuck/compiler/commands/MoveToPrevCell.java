package ua.brainfuck.compiler.commands;

import ua.brainfuck.compiler.app.Memory;

import java.util.Objects;

public class MoveToPrevCell implements Operation {

    @Override
    public void execute(Memory memory) {
        memory.moveToPrevCell();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof MoveToPrevCell;
    }

    @Override
    public int hashCode() {
        return MoveToPrevCell.class.hashCode();
    }
}
