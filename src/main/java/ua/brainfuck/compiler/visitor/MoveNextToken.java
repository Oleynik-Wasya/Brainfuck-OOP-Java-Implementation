package ua.brainfuck.compiler.visitor;

import ua.brainfuck.compiler.commands.MoveToNextCell;

public class MoveNextToken implements Token {
    @Override
    public void accept(Visitor visitor) {
        visitor.visitCommand(new MoveToNextCell());
    }
}
