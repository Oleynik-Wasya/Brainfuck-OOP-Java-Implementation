package ua.brainfuck.compiler.commands;

import ua.brainfuck.compiler.app.Memory;

import java.util.Objects;

public class GetCurrentChar extends Operation {

    public GetCurrentChar(Memory memory) {
        super(memory);
    }

    public void execute() {
        System.out.print(memory.getCurrentChar());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetCurrentChar that = (GetCurrentChar) o;
        return memory.equals(that.memory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memory);
    }
}
