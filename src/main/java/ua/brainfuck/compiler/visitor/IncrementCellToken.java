package ua.brainfuck.compiler.visitor;

import ua.brainfuck.compiler.commands.IncrementCurrentCell;

public class IncrementCellToken implements Token {

    @Override
    public void accept(Visitor visitor) {
        visitor.visitCommand(new IncrementCurrentCell());
    }
}
