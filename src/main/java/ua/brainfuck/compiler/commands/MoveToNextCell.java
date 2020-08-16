package ua.brainfuck.compiler.commands;

import ua.brainfuck.compiler.app.Memory;

import java.util.Objects;

public class MoveToNextCell extends Operation {


    public MoveToNextCell(Memory memory) {
        super(memory);
    }

    public void execute() {
        memory.moveToNextCell();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoveToNextCell that = (MoveToNextCell) o;
        return memory.equals(that.memory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memory);
    }
}
