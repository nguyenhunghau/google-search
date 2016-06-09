package faber.dto;

/**
 *
 * @author Nguyen Hung Hau
 */
public class ResultDTO {
    
    private Integer id;
    private Integer idKeyword;
    private String title;
    private String url;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdKeyword() {
        return idKeyword;
    }

    public void setIdKeyword(Integer idKeyword) {
        this.idKeyword = idKeyword;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
