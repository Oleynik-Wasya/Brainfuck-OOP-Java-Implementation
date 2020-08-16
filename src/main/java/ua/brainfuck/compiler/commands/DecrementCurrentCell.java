package ua.brainfuck.compiler.commands;

import ua.brainfuck.compiler.app.Memory;

import java.util.Objects;

public class DecrementCurrentCell extends Operation {


    public DecrementCurrentCell(Memory memory) {
        super(memory);
    }

    public void execute() {
        memory.decrementCurrentCell();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DecrementCurrentCell that = (DecrementCurrentCell) o;
        return memory.equals(that.memory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memory);
    }
}
