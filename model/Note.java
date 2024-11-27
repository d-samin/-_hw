package model;

import java.time.LocalDateTime;

public class Note {

    private final LocalDateTime noteTime; 
    private final String description;      

    public Note(LocalDateTime noteTime, String description) {
        this.noteTime = noteTime;
        this.description = description;
    }

    public LocalDateTime getNoteTime(){ 
        return noteTime;
    }

    public String getDescription() {
        return description;
    }

    @Override 
    public String toString(){ 
        return noteTime.toString() + "|" + description;
    }
}
