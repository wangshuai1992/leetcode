package util;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ConfluencePageVo extends ConfluenceContent implements Serializable {
    private String url;
    private List<ConfluencePageVo> childrenPages;
    private String auth;
    private String authId;
    private int visitNum;
}
