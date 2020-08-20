package ua.brainfuck.compiler.visitor;

public interface Token {
    void accept(Visitor visitor);
}
