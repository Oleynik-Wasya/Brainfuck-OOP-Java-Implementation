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
        memory = new MemoryImpl();
        brainfuckCompiler = new BrainfuckCompiler(memory);
    }

    @Test
    void compile() {
        String incrementInput = "++++";
        List<Operation> incrementOutput = new ArrayList<>();
        incrementOutput.add(new IncrementCurrentCell());
        incrementOutput.add(new IncrementCurrentCell());
        incrementOutput.add(new IncrementCurrentCell());
        incrementOutput.add(new IncrementCurrentCell());

        assertEquals(incrementOutput, brainfuckCompiler.compile(incrementInput));

        String decrementInput = "----";
        List<Operation> decrementOutput = new ArrayList<>();
        decrementOutput.add(new DecrementCurrentCell());
        decrementOutput.add(new DecrementCurrentCell());
        decrementOutput.add(new DecrementCurrentCell());
        decrementOutput.add(new DecrementCurrentCell());

        assertEquals(decrementOutput, brainfuckCompiler.compile(decrementInput));

        String currentCharInput = "....";
        List<Operation> currentCharOutput = new ArrayList<>();
        currentCharOutput.add(new GetCurrentChar());
        currentCharOutput.add(new GetCurrentChar());
        currentCharOutput.add(new GetCurrentChar());
        currentCharOutput.add(new GetCurrentChar());

        assertEquals(currentCharOutput, brainfuckCompiler.compile(currentCharInput));

        String moveToNextCellInput = ">>>>";
        List<Operation> moveToNextCellOutput = new ArrayList<>();
        moveToNextCellOutput.add(new MoveToNextCell());
        moveToNextCellOutput.add(new MoveToNextCell());
        moveToNextCellOutput.add(new MoveToNextCell());
        moveToNextCellOutput.add(new MoveToNextCell());

        assertEquals(moveToNextCellOutput, brainfuckCompiler.compile(moveToNextCellInput));


        String moveToPrevCellInput = "<<<<";
        List<Operation> moveToPrevCellOutput = new ArrayList<>();
        moveToPrevCellOutput.add(new MoveToPrevCell());
        moveToPrevCellOutput.add(new MoveToPrevCell());
        moveToPrevCellOutput.add(new MoveToPrevCell());
        moveToPrevCellOutput.add(new MoveToPrevCell());

        assertEquals(moveToPrevCellOutput, brainfuckCompiler.compile(moveToPrevCellInput));

        String loopInput = "[>+.-<]";
        List<Operation> loopOutput = new ArrayList<>();
        List<Operation> loopOperations = new ArrayList<>();
        loopOperations.add(new MoveToNextCell());
        loopOperations.add(new IncrementCurrentCell());
        loopOperations.add(new GetCurrentChar());
        loopOperations.add(new DecrementCurrentCell());
        loopOperations.add(new MoveToPrevCell());

        loopOutput.add(new Loop(loopOperations));

        assertEquals(loopOutput, brainfuckCompiler.compile(loopInput));


        String subLoopsInput = "[>[+[.]-]<]";
        List<Operation> subLoopsOutput = new ArrayList<>();

        List<Operation> subLoop1Operations = new ArrayList<>();
        List<Operation> subLoop2Operations = new ArrayList<>();
        List<Operation> subLoop3Operations = new ArrayList<>();

        subLoop1Operations.add(new MoveToNextCell());
        subLoop1Operations.add(new Loop(subLoop2Operations));
        subLoop1Operations.add(new MoveToPrevCell());

        subLoop2Operations.add(new IncrementCurrentCell());
        subLoop2Operations.add(new Loop(subLoop3Operations));
        subLoop2Operations.add(new DecrementCurrentCell());

        subLoop3Operations.add(new GetCurrentChar());

        subLoopsOutput.add(new Loop(subLoop1Operations));

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