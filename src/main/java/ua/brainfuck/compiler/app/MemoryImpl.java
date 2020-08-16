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

    @Override
    public void moveToNextCell() {
        this.ptr++;
    }

    @Override
    public void moveToPrevCell() {
        this.ptr--;
    }

    @Override
    public void incrementCurrentCell() {
        this.memory[ptr]++;
    }

    @Override
    public void decrementCurrentCell() {
        this.memory[ptr]--;
    }

    @Override
    public char getCurrentChar() {
        return (char)this.memory[ptr];
    }

    @Override
    public int getCurrentValue() {
        return this.memory[ptr];
    }

    @Override
    public void clean() {
        this.memory = new int[this.memory.length];
    }
}
