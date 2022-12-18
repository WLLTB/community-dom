package blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogVo {
    private String author;

    private String title;

    private String type;

    private Integer views;

    private Integer marks;

    private String content;

    private String htmlContent;
}
