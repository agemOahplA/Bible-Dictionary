package org.cruciata.dictserver.dataaccess;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Everyday {

    public Everyday() {
    }

    public Everyday(String id, String date, String etiquette, String saint, String mass, String recite, String morningPrayer, String dayPrayer, String eveningPrayer, String nightPrayer) {
        this.id = id;
        this.date = date;
        this.etiquette = etiquette;
        this.saint = saint;
        this.mass = mass;
        this.recite = recite;
        this.morningPrayer = morningPrayer;
        this.dayPrayer = dayPrayer;
        this.eveningPrayer = eveningPrayer;
        this.nightPrayer = nightPrayer;
    }

    @Id
    private String id;
    // 日期
    String date;
    // 礼仪
    @Column(columnDefinition = "TEXT")
    String etiquette;
    // 圣人
    @Column(columnDefinition = "TEXT")
    String saint;
    // 弥撒
    @Column(columnDefinition = "TEXT")
    String mass;
    // 涌祷
    @Column(columnDefinition = "TEXT")
    String recite;
    // 晨祷
    @Column(columnDefinition = "TEXT")
    String morningPrayer;
    // 日祷
    @Column(columnDefinition = "TEXT")
    String dayPrayer;
    // 晚祷
    @Column(columnDefinition = "TEXT")
    String eveningPrayer;
    // 夜祷
    @Column(columnDefinition = "TEXT")
    String nightPrayer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEtiquette() {
        return etiquette;
    }

    public void setEtiquette(String etiquette) {
        this.etiquette = etiquette;
    }

    public String getSaint() {
        return saint;
    }

    public void setSaint(String saint) {
        this.saint = saint;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getRecite() {
        return recite;
    }

    public void setRecite(String recite) {
        this.recite = recite;
    }

    public String getMorningPrayer() {
        return morningPrayer;
    }

    public void setMorningPrayer(String morningPrayer) {
        this.morningPrayer = morningPrayer;
    }

    public String getDayPrayer() {
        return dayPrayer;
    }

    public void setDayPrayer(String dayPrayer) {
        this.dayPrayer = dayPrayer;
    }

    public String getEveningPrayer() {
        return eveningPrayer;
    }

    public void setEveningPrayer(String eveningPrayer) {
        this.eveningPrayer = eveningPrayer;
    }

    public String getNightPrayer() {
        return nightPrayer;
    }

    public void setNightPrayer(String nightPrayer) {
        this.nightPrayer = nightPrayer;
    }
}
