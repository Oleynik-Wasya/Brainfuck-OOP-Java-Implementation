package ua.brainfuck.compiler.commands;

import ua.brainfuck.compiler.app.Memory;

import java.util.Objects;

public class MoveToNextCell implements Operation {


    @Override
    public void execute(Memory memory) {
        memory.moveToNextCell();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof MoveToNextCell;
    }

    @Override
    public int hashCode() {
        return MoveToNextCell.class.hashCode();
    }
}
