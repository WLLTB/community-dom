package common.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId
    private String id;

    @NonNull
    private String username;

    @NonNull
    private String nickname;

    @NonNull
    private String password;

    @NonNull
    private String email;
}
