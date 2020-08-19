package ua.brainfuck.compiler.factories;

import ua.brainfuck.compiler.commands.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CommandFactoryImpl implements CommandFactory {

    List<Operation> operations = new ArrayList<>();
    LinkedList<List<Operation>> stack = new LinkedList<>();
    List<Operation> currentOperationsList = operations;

    @Override
    public void addCommand(char c) {
        switch (c) {
            case '>':
                currentOperationsList.add(new MoveToNextCell());
                break;
            case '<':
                currentOperationsList.add(new MoveToPrevCell());
                break;
            case '+':
                currentOperationsList.add(new IncrementCurrentCell());
                break;
            case '-':
                currentOperationsList.add(new DecrementCurrentCell());
                break;
            case '.':
                currentOperationsList.add(new GetCurrentChar());
                break;
            case '[':
                stack.addFirst(currentOperationsList);
                currentOperationsList = new ArrayList<>();
                stack.peekFirst().add(new Loop(currentOperationsList));
                break;
            case ']':
                currentOperationsList = stack.pollFirst();
                break;
            default:
                throw new IllegalStateException("Syntax error!");
        }
    }

    @Override
    public List<Operation> getOperations() {
        return operations;
    }
}
