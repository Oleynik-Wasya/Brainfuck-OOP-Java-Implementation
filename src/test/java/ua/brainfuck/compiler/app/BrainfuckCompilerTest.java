package ua.brainfuck.compiler.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.brainfuck.compiler.commands.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BrainfuckCompilerTest {

    private BrainfuckCompiler brainfuckCompiler;
    private Memory memory;

    @BeforeEach
    void setUp() {
        memory = new Memory();
        brainfuckCompiler = new BrainfuckCompiler(memory);
    }

    @Test
    void compile() {
        String incrementInput = "++++";
        List<Operation> incrementOutput = new ArrayList<>();
        incrementOutput.add(new IncrementCurrentCell(memory));
        incrementOutput.add(new IncrementCurrentCell(memory));
        incrementOutput.add(new IncrementCurrentCell(memory));
        incrementOutput.add(new IncrementCurrentCell(memory));

        assertEquals(incrementOutput, brainfuckCompiler.compile(incrementInput));

        String decrementInput = "----";
        List<Operation> decrementOutput = new ArrayList<>();
        decrementOutput.add(new DecrementCurrentCell(memory));
        decrementOutput.add(new DecrementCurrentCell(memory));
        decrementOutput.add(new DecrementCurrentCell(memory));
        decrementOutput.add(new DecrementCurrentCell(memory));

        assertEquals(decrementOutput, brainfuckCompiler.compile(decrementInput));

        String currentCharInput = "....";
        List<Operation> currentCharOutput = new ArrayList<>();
        currentCharOutput.add(new GetCurrentChar(memory));
        currentCharOutput.add(new GetCurrentChar(memory));
        currentCharOutput.add(new GetCurrentChar(memory));
        currentCharOutput.add(new GetCurrentChar(memory));

        assertEquals(currentCharOutput, brainfuckCompiler.compile(currentCharInput));

        String moveToNextCellInput = ">>>>";
        List<Operation> moveToNextCellOutput = new ArrayList<>();
        moveToNextCellOutput.add(new MoveToNextCell(memory));
        moveToNextCellOutput.add(new MoveToNextCell(memory));
        moveToNextCellOutput.add(new MoveToNextCell(memory));
        moveToNextCellOutput.add(new MoveToNextCell(memory));

        assertEquals(moveToNextCellOutput, brainfuckCompiler.compile(moveToNextCellInput));


        String moveToPrevCellInput = "<<<<";
        List<Operation> moveToPrevCellOutput = new ArrayList<>();
        moveToPrevCellOutput.add(new MoveToPrevCell(memory));
        moveToPrevCellOutput.add(new MoveToPrevCell(memory));
        moveToPrevCellOutput.add(new MoveToPrevCell(memory));
        moveToPrevCellOutput.add(new MoveToPrevCell(memory));

        assertEquals(moveToPrevCellOutput, brainfuckCompiler.compile(moveToPrevCellInput));

        String loopInput = "[>+.-<]";
        List<Operation> loopOutput = new ArrayList<>();
        List<Operation> loopOperations = new ArrayList<>();
        loopOperations.add(new MoveToNextCell(memory));
        loopOperations.add(new IncrementCurrentCell(memory));
        loopOperations.add(new GetCurrentChar(memory));
        loopOperations.add(new DecrementCurrentCell(memory));
        loopOperations.add(new MoveToPrevCell(memory));

        loopOutput.add(new Loop(memory, loopOperations));

        assertEquals(loopOutput, brainfuckCompiler.compile(loopInput));


        String subLoopsInput = "[>[+[.]-]<]";
        List<Operation> subLoopsOutput = new ArrayList<>();

        List<Operation> subLoop1Operations = new ArrayList<>();
        List<Operation> subLoop2Operations = new ArrayList<>();
        List<Operation> subLoop3Operations = new ArrayList<>();

        subLoop1Operations.add(new MoveToNextCell(memory));
        subLoop1Operations.add(new Loop(memory, subLoop2Operations));
        subLoop1Operations.add(new MoveToPrevCell(memory));

        subLoop2Operations.add(new IncrementCurrentCell(memory));
        subLoop2Operations.add(new Loop(memory, subLoop3Operations));
        subLoop2Operations.add(new DecrementCurrentCell(memory));

        subLoop3Operations.add(new GetCurrentChar(memory));

        subLoopsOutput.add(new Loop(memory, subLoop1Operations));

        assertEquals(subLoopsOutput, brainfuckCompiler.compile(subLoopsInput));
    }

    @Test
    void run() {
        String five = "+++++[>+++++++++++++<-]>.";
        String output = "A";
        assertEquals(output, brainfuckCompiler.run(brainfuckCompiler.compile(five)));

        String helloWorld = ">+++++++++[<++++++++>-]<.>+++++++[<++++>-]<+.+++++++..+++.>>>++++++++[<++++>-]" +
                "<.>>>++++++++++[<+++++++++>-]<---.<<<<.+++.------.--------.>>+.";
        String outputHelloWorld = "Hello World!";
        assertEquals(outputHelloWorld, brainfuckCompiler.run(brainfuckCompiler.compile(helloWorld)));

        String alphabet = "+++++[>+++++++++++++<-]>.>+++++++++++++++++++++++++[<+.>-]";
        String alphabetOutput = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        assertEquals(alphabetOutput, brainfuckCompiler.run(brainfuckCompiler.compile(alphabet)));
    }
}