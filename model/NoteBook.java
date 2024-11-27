package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
    
    public class NoteBook implements INoteBook {
        private List<Note> notes = new ArrayList<>();
    
        public void addNote(Note note) {
            notes.add(note);
        }
    
        public List<Note> getNotes() {
            return notes;
        }

        public List<Note> getNotesForDay(LocalDateTime dateTime) {
            List<Note> dailyNotes = new ArrayList<>(); 
            for (Note note : notes) {
                if (note.getNoteTime().toLocalDate().isEqual(dateTime.toLocalDate())) {
                    dailyNotes.add(note);
                }   
            }
            dailyNotes.sort(Comparator.comparing(Note::getNoteTime));
            return dailyNotes;
        }
    
        public List<Note> getNotesForWeek(LocalDateTime startOfWeek) {
            LocalDateTime endOfWeek = startOfWeek.plusWeeks(1);
            List<Note> weeklyNotes = new ArrayList<>(); 
            for (Note note : notes) {
                if (!note.getNoteTime().isBefore(startOfWeek) && !note.getNoteTime().isAfter(endOfWeek)) {
                    weeklyNotes.add(note);
                }
            }
            weeklyNotes.sort(Comparator.comparing(Note::getNoteTime));
            return weeklyNotes;
        }
    


    public void saveToFile(String fileName) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
        for (Note note : notes) {
            writer.write(note.getNoteTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "|" + note.getDescription());
            writer.newLine(); 
        }
    }
}

public void loadFromFile(String fileName) throws IOException {
    notes.clear(); 
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\|", 2); 
            if (parts.length != 2) {
                System.err.println("Некорректный формат строки: " + line);
                continue; 
            }
            String dateTimePart = parts[0].trim();
            String description = parts[1].trim();
            try {
                dateTimePart = dateTimePart.replace('T', ' ');  
                LocalDateTime dateTime = LocalDateTime.parse(dateTimePart, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                notes.add(new Note(dateTime, description));
            } catch (DateTimeParseException e) {
                System.err.println("Ошибка разбора даты: " + dateTimePart);
            }
        }
    }
}

}
    