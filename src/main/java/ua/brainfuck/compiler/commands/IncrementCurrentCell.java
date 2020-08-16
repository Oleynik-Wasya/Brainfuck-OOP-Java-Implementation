package ua.brainfuck.compiler.commands;

import ua.brainfuck.compiler.app.Memory;

import java.util.Objects;

public class IncrementCurrentCell extends Operation {


    public IncrementCurrentCell(Memory memory) {
        super(memory);
    }

    public void execute() {
        memory.incrementCurrentCell();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncrementCurrentCell that = (IncrementCurrentCell) o;
        return memory.equals(that.memory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memory);
    }
}
