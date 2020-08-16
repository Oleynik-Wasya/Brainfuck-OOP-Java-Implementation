package ua.brainfuck.compiler.commands;

import ua.brainfuck.compiler.app.Memory;

import java.util.List;
import java.util.Objects;

public class Loop extends Operation {

    List<Operation> operations;

    public Loop(Memory memory, List<Operation> operations) {
        super(memory);
        this.operations = operations;
    }

    @Override
    public void execute() {
        while (this.memory.getCurrentValue() != 0) {
            for (Operation operation : operations) {
                operation.execute();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loop loop = (Loop) o;
        return operations.equals(loop.operations) &&
                memory.equals(loop.memory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operations, memory);
    }
}
