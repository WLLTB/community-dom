package common.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    @TableId
    private String id;

    private String author;

    private String title;

    private Integer typeId;

    private Integer views;

    private Integer marks;

    private String content;

    private String htmlContent;
}
