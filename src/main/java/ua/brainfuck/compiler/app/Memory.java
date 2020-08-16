package ua.brainfuck.compiler.app;

public interface Memory {

    void moveToNextCell();

    void moveToPrevCell();

    void incrementCurrentCell();

    void decrementCurrentCell();

    char getCurrentChar();

    int getCurrentValue();

    void clean();

}
