package ua.brainfuck.compiler.visitor;

public class LoopEndToken implements Token {
    @Override
    public void accept(Visitor visitor) {
        visitor.visitLoopEnd();
    }
}
