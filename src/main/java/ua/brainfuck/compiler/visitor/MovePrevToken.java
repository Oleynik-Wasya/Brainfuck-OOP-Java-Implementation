package ua.brainfuck.compiler.visitor;

import ua.brainfuck.compiler.commands.MoveToPrevCell;

public class MovePrevToken implements Token {
    @Override
    public void accept(Visitor visitor) {
        visitor.visitCommand(new MoveToPrevCell());
    }
}
