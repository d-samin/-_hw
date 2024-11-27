package presenter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import model.Note;
import model.NoteService;
import view.ConsoleUI;


public class Presenter {
    private NoteService noteService;
    private ConsoleUI view;

    public Presenter(NoteService noteService, ConsoleUI view) {
        this.noteService = noteService;
        this.view = view;
    }

    public void addNote() {
        LocalDateTime dateTime = view.getDateTimeInput();
        String description = view.getDescriptionInput();
        noteService.addNote(dateTime, description);
        view.displayMessage("Заметка добавлена.");
    }

    public void displayAllNotes() {
        List<Note> notes = noteService.displayAllNotes().getNotes();
        if (notes.isEmpty()) {
            view.displayMessage("Нет заметок.");
        } else {
            notes.forEach(note -> view.displayMessage(note.toString()));
        }
    }

    public void getNotesForDay() {
        LocalDateTime dateTime = view.getDateTimeInput();
        List<Note> notes = noteService.getNotesForDay(dateTime);
        if (notes.isEmpty()) {
            view.displayMessage("На эту дату заметок нет.");
        } else {
            notes.forEach(note -> view.displayMessage(note.toString()));
        }
    }

    public void getNotesForWeek() {
        LocalDateTime startOfWeek = view.getDateTimeInput(); // Ввод начала недели
        List<Note> notes = noteService.getNotesForWeek(startOfWeek);
        if (notes.isEmpty()) {
            view.displayMessage("На эту неделю заметок нет.");
        } else {
            notes.forEach(note -> view.displayMessage(note.toString()));
        }
    }

    public void saveNotesToFile() {
        String fileName = view.getFileNameInput();
        try {
            noteService.saveNotesToFile(fileName);
            view.displayMessage("Заметки сохранены в файл.");
        } catch (IOException e) {
            view.displayMessage("Ошибка при сохранении файла: " + e.getMessage());
        }
    }

    public void loadNotesFromFile() {
        String fileName = view.getFileNameInput();
        try {
            noteService.loadNotesFromFile(fileName);
            view.displayMessage("Заметки загружены из файла.");
        } catch (IOException e) {
            view.displayMessage("Ошибка при загрузке файла: " + e.getMessage());
        }
    }
}
