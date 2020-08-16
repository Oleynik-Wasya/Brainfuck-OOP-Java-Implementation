package ua.brainfuck.compiler.app;

public class MemoryImpl implements Memory {
    private int ptr;
    private int[] memory;

    public MemoryImpl(int capacity) {
        this.memory = new int[capacity];
    }

    public MemoryImpl() {
        this.memory = new int[30000];
    }

    public void moveToNextCell() {
        this.ptr++;
    }

    public void moveToPrevCell() {
        this.ptr--;
    }

    public void incrementCurrentCell() {
        this.memory[ptr]++;
    }

    public void decrementCurrentCell() {
        this.memory[ptr]--;
    }

    public char getCurrentChar() {
        return (char)this.memory[ptr];
    }

    public int getCurrentValue() {
        return this.memory[ptr];
    }

    public void clean() {
        this.memory = new int[this.memory.length];
    }
}
