package user.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class User {
    @Id
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid",strategy="uuid")
    private String id;

    @NonNull
    private String username;

    @NonNull
    private String nickname;

    @NonNull
    private String password;

    @NonNull
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
