package com.kanthin.handbookgenshin.model.character;

import java.io.Serializable;

public class CVCharacter implements Serializable {
    private String chinese;
    private String english;
    private String japanese;
    private String korean;

    public CVCharacter() {
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getJapanese() {
        return japanese;
    }

    public void setJapanese(String japanese) {
        this.japanese = japanese;
    }

    public String getKorean() {
        return korean;
    }

    public void setKorean(String korean) {
        this.korean = korean;
    }
}
