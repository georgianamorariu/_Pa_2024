package org.example;

import java.time.LocalDate;

public class Author {
    private int authorId;
    private String authorName;
    private LocalDate birthDate;

    public Author(int authorId, String authorName, LocalDate birthDate) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.birthDate = birthDate;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
