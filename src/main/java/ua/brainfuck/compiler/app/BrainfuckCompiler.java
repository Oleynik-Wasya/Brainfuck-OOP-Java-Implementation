package ua.brainfuck.compiler.app;

import ua.brainfuck.compiler.commands.*;
import ua.brainfuck.compiler.visitor.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

class BrainfuckCompiler {

    private Memory memory;

    BrainfuckCompiler(Memory memory) {
        this.memory = memory;
    }

    List<Operation> compile(String brainFuckCode) throws IllegalStateException {

        List<Token> tokens = tokenize(brainFuckCode);
        List<Operation> operations = new ArrayList<>();

        Visitor visitor = new VisitorImpl(operations);
        for (Token token : tokens) {
            token.accept(visitor);
        }

        return operations;
    }

    String run(List<Operation> operations) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        for (Operation operation : operations) {
            operation.execute(memory);
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

    private List<Token> tokenize(String brainFuckCode) {
        List<Token> tokens = new ArrayList<>();
        char[] brainFuckCodeAsChar = brainFuckCode.toCharArray();

        if (!checkBracketsValid(brainFuckCodeAsChar)) {
            throw new IllegalStateException("Unmatched brackets!");
        }

        for (char c : brainFuckCodeAsChar) {
            switch (c) {
                case '>':
                    tokens.add(new MoveNextToken());
                    break;
                case '<':
                    tokens.add(new MovePrevToken());
                    break;
                case '+':
                    tokens.add(new IncrementCellToken());
                    break;
                case '-':
                    tokens.add(new DecrementCellToken());
                    break;
                case '.':
                    tokens.add(new GetCharToken());
                    break;
                case '[':
                    tokens.add(new LoopBeginToken());
                    break;
                case ']':
                    tokens.add(new LoopEndToken());
                    break;
                default:
                    throw new IllegalStateException("Syntax error!");
            }
        }
        return tokens;
    }
}
