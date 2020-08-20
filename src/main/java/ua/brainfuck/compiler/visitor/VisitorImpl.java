package ua.brainfuck.compiler.visitor;

import ua.brainfuck.compiler.commands.Loop;
import ua.brainfuck.compiler.commands.Operation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class VisitorImpl implements Visitor {

    List<Operation> operations;
    LinkedList<List<Operation>> stack = new LinkedList<>();
    List<Operation> currentOperationsList;

    public VisitorImpl(List<Operation> operations) {
        this.operations = operations;
        this.currentOperationsList = operations;
    }

    @Override
    public void visitCommand(Operation operations) {
        currentOperationsList.add(operations);
    }

    @Override
    public void visitLoopBegin() {
        stack.addFirst(currentOperationsList);
        currentOperationsList = new ArrayList<>();
        stack.peekFirst().add(new Loop(currentOperationsList));
    }

    @Override
    public void visitLoopEnd() {
        currentOperationsList = stack.pollFirst();
    }
}
