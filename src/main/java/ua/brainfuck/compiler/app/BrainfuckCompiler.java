package ua.brainfuck.compiler.app;

import ua.brainfuck.compiler.commands.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

class BrainfuckCompiler {

    private Memory memory;

    BrainfuckCompiler(Memory memory) {
        this.memory = memory;
    }

    List<Operation> compile(String brainFuckCode) throws IllegalStateException {
        List<Operation> operations = new ArrayList<>();
        char[] brainFuckCodeAsChar = brainFuckCode.toCharArray();

        if (!checkBracketsValid(brainFuckCodeAsChar)) {
            throw new IllegalStateException("Unmatched brackets!");
        }

        LinkedList<List<Operation>> stack = new LinkedList<>();
        List<Operation> currentOperationsList = operations;
        for (char c : brainFuckCodeAsChar) {
            switch (c) {
                case '>':
                    currentOperationsList.add(new MoveToNextCell(memory));
                    break;
                case '<':
                    currentOperationsList.add(new MoveToPrevCell(memory));
                    break;
                case '+':
                    currentOperationsList.add(new IncrementCurrentCell(memory));
                    break;
                case '-':
                    currentOperationsList.add(new DecrementCurrentCell(memory));
                    break;
                case '.':
                    currentOperationsList.add(new GetCurrentChar(memory));
                    break;
                case '[':
                    stack.addFirst(currentOperationsList);
                    currentOperationsList = new ArrayList<>();
                    stack.peekFirst().add(new Loop(memory, currentOperationsList));
                    break;
                case ']':
                    currentOperationsList = stack.pollFirst();
                    break;
                default:
                    throw new IllegalStateException("Syntax error!");
            }
        }
        return operations;
    }

    String run(List<Operation> operations) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        for (Operation operation : operations) {
            operation.execute();
        }

        System.out.flush();
        System.setOut(old);

        memory.clean();
        return baos.toString();
    }

    private boolean checkBracketsValid(char[] brainFuckCode) {
        int k = 0;
        for (char c : brainFuckCode) {
            if (c == '[') {
                k++;
            } else if (c == ']') {
                k--;
            }
            if (k < 0) {
                return false;
            }
        }
        return k == 0;
    }

}
