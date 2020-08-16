package ua.brainfuck.compiler.commands;

import ua.brainfuck.compiler.app.Memory;

import java.util.Objects;

public class MoveToPrevCell extends Operation {


    public MoveToPrevCell(Memory memory) {
        super(memory);
    }

    public void execute() {
        memory.moveToPrevCell();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoveToPrevCell that = (MoveToPrevCell) o;
        return memory.equals(that.memory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memory);
    }
}
