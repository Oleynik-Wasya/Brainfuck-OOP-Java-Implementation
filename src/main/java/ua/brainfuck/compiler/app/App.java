package ua.brainfuck.compiler.app;

import ua.brainfuck.compiler.commands.Operation;

import java.util.List;

public class App {
    public static void main(String[] args) {
        Memory memory = new MemoryImpl();

        String brainFuckCode = "++++[>+++++<-]>[<+++++>-]+<+[>[>+>+<<-]++>>[<<+>>-]>>>[-]++>[-]+>>>+[[-]++++++>>>]<<<[[<++++++++<++>>-]+<.<[>----<-]<]<<[>>>>>[>>>[-]+++++++++<[>-<-]+++++++++>[-[<->-]+[<<<]]<[>+<-]>]<<-]<<-]";

        BrainfuckCompiler compiler = new BrainfuckCompiler(memory);

        List<Operation> operations = compiler.compile(brainFuckCode);
        String result = compiler.run(operations);
        System.out.println(result);
    }
}
