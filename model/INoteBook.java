package model;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface INoteBook {
    void addNote(Note note);
    List<Note> getNotes();
    List<Note> getNotesForDay(LocalDateTime dateTime);
    List<Note> getNotesForWeek(LocalDateTime startOfWeek);
    void saveToFile(String fileName) throws IOException;
    void loadFromFile(String fileName) throws IOException;
}

