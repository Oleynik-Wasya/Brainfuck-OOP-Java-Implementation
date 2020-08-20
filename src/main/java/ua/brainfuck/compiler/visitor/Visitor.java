package ua.brainfuck.compiler.visitor;

import ua.brainfuck.compiler.commands.Operation;

import java.util.List;

public interface Visitor {
    void visitCommand(Operation operations);
    void visitLoopBegin();
    void visitLoopEnd();
}
