package ua.brainfuck.compiler.commands;

import ua.brainfuck.compiler.app.Memory;

import java.util.List;
import java.util.Objects;

public class Loop implements Operation {

    List<Operation> operations;

    public Loop(List<Operation> operations) {
        this.operations = operations;
    }

    @Override
    public void execute(Memory memory) {
        while (memory.getCurrentValue() != 0) {
            for (Operation operation : operations) {
                operation.execute(memory);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loop loop = (Loop) o;
        return operations.equals(loop.operations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operations);
    }
}
