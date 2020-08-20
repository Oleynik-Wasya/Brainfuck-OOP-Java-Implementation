package ua.brainfuck.compiler.visitor;

import ua.brainfuck.compiler.commands.DecrementCurrentCell;

public class DecrementCellToken implements Token {
    @Override
    public void accept(Visitor visitor) {
        visitor.visitCommand(new DecrementCurrentCell());
    }
}
