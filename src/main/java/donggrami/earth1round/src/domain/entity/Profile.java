package donggrami.earth1round.src.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Profile")
@Getter
@Setter
@DynamicInsert
@NoArgsConstructor
public class Profile {
    public enum UserStatus {
        ACTIVE, INACTIVE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 30, nullable = false)
    @ColumnDefault("'User'")
    private String nickname;

    @Column(length = 255)
    private String profileImg;

    @Column(nullable = false)
    private int level;

    @PrePersist
    public void prePersist(){
        this.level = this.level == 0 ? 1 : this.level;
    }

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'ACTIVE'")
    private User.UserStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date created_at;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updated_at;

    @Builder
    public Profile(Long profileId, User user, String nickname, String profileImg, int level, User.UserStatus status, Date created_at, Date updated_at) {
        this.profileId = profileId;
        this.user = user;
        this.nickname = nickname;
        this.profileImg = profileImg;
        this.level = level;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
