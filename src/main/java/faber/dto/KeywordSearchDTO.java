package faber.dto;

import java.sql.Date;

/**
 *
 * @author Nguyen Hung Hau
 */
public class KeywordSearchDTO {
    
    private Integer id;
    private String keyword;
    private Date timeSave;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Date getTimeSave() {
        return timeSave;
    }

    public void setTimeSave(Date timeSave) {
        this.timeSave = timeSave;
    }
    
}
