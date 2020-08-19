package ua.brainfuck.compiler.factories;

import ua.brainfuck.compiler.commands.Operation;

import java.util.List;

public interface CommandFactory {
    void addCommand(char c);
    List<Operation> getOperations();
}
