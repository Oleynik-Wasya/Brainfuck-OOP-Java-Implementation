package ua.brainfuck.compiler.visitor;

import ua.brainfuck.compiler.commands.Loop;

public class LoopBeginToken implements Token {
    @Override
    public void accept(Visitor visitor) {
        visitor.visitLoopBegin();
    }
}
