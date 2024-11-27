package model;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class NoteService {
    private INoteBook notes;

    public NoteService(INoteBook notes) {
        this.notes = notes;
    }

    public void addNote(LocalDateTime noteTime, String description) {
        Note note = new Note(noteTime, description);
        notes.addNote(note);
    }

    public INoteBook displayAllNotes() {
        return notes;
    }

    public List<Note> getNotesForDay(LocalDateTime dateTime) {
        return notes.getNotesForDay(dateTime);
    }

    public List<Note> getNotesForWeek(LocalDateTime startOfWeek) {
        return notes.getNotesForWeek(startOfWeek);
    }

    public void saveNotesToFile(String fileName) throws IOException {
        notes.saveToFile(fileName);
    }

    public void loadNotesFromFile(String fileName) throws IOException {
        notes.loadFromFile(fileName);
    }
}
