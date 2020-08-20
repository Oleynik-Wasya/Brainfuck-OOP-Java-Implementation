package ua.brainfuck.compiler.commands;

import ua.brainfuck.compiler.app.Memory;
import ua.brainfuck.compiler.visitor.Visitor;

import java.util.Objects;

public class DecrementCurrentCell implements Operation {
    @Override
    public void execute(Memory memory) {
        memory.decrementCurrentCell();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof DecrementCurrentCell;
    }

    @Override
    public int hashCode() {
        return DecrementCurrentCell.class.hashCode();
    }
}
