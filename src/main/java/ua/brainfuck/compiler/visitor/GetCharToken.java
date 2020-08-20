package ua.brainfuck.compiler.visitor;

import ua.brainfuck.compiler.commands.GetCurrentChar;

public class GetCharToken implements Token {
    @Override
    public void accept(Visitor visitor) {
        visitor.visitCommand(new GetCurrentChar());
    }
}
