package ua.brainfuck.compiler.app;

import ua.brainfuck.compiler.commands.*;
import ua.brainfuck.compiler.factories.CommandFactoryImpl;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

class BrainfuckCompiler {

    private Memory memory;

    BrainfuckCompiler(Memory memory) {
        this.memory = memory;
    }

    List<Operation> compile(String brainFuckCode) throws IllegalStateException {
        char[] brainFuckCodeAsChar = brainFuckCode.toCharArray();

        if (!checkBracketsValid(brainFuckCodeAsChar)) {
            throw new IllegalStateException("Unmatched brackets!");
        }

        CommandFactoryImpl commandFactoryImpl = new CommandFactoryImpl();
        for (char c : brainFuckCodeAsChar) {
            commandFactoryImpl.addCommand(c);
        }

        return commandFactoryImpl.getOperations();
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
}
