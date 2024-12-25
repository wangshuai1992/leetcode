package util;

import lombok.Data;

import java.io.Serializable;

@Data
public class ConfluenceContent implements Serializable {
    private String contentId;
    private String title;
    private String creator;
    private String lastModifier;
    private String lastModDate;
    private String parentId;
    private String version;
    private String prevver;
    private Long spaceId;
    private String contentType;

    private String spaceName; //空间名称
}
