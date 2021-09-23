package com.runewatch;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
@AllArgsConstructor
public class Case {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d-MMM-yyyy");

    /**
     * rsn
     * example: "Zezima"
     */
    @SerializedName("accused_rsn")
    private String rsn;

    /**
     * date
     * example: "2019-03-10 13:35:34" GMT
     */
    @SerializedName("published_date")
    private Date date;

    /**
     * short_code
     * example: "a0a6200"
     */
    @SerializedName("short_code")
    private String code;

    /**
     * reason
     * example: "Accused Of Stealing Borrowed Items"
     */
    private String reason;

    /**
     * rating
     * example: "4"
     */
    @SerializedName("evidence_rating")
    private String rating;

    /**
     * source
     * example: "RW"
     */
    @SerializedName("source")
    private String source;

    public String date() {
        log.debug(String.valueOf(date));
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(DATE_FORMAT);
    }

    public String getSource(){
        return (source == null) ? "RW" : source;
    }

    public boolean isRW(){
        return !getSource().toLowerCase().equals("wdr");
    }

    public String sourcePossessive() {
        switch (getSource().toLowerCase()) {
            case "rw": return "RuneWatch";
            case "wdr": return "We Do Raids";
            default: return "Unknown";
        }
    }
}