package org.cruciata.dictserver.dataaccess;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Dict {

    @Id
    private String id;
    private String word;
    private String wordEnglish;
    private String wordLatin;
    @Column(columnDefinition = "TEXT")
    private String explanation;


    public Dict() {
    }

    public Dict(String id, String word, String wordEnglish, String wordLatin, String explanation) {
        this.id = id;
        this.word = word;
        this.wordEnglish = wordEnglish;
        this.wordLatin = wordLatin;
        this.explanation = explanation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWordEnglish() {
        return wordEnglish;
    }

    public void setWordEnglish(String wordEnglish) {
        this.wordEnglish = wordEnglish;
    }

    public String getWordLatin() {
        return wordLatin;
    }

    public void setWordLatin(String wordLatin) {
        this.wordLatin = wordLatin;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
