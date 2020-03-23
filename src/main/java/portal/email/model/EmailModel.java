package portal.email.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import portal.core.model.BaseModel;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "EMAIL")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Setter
@Getter
public class EmailModel extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "X_MESSAGE_ID")
    private String xMessageId;

    @Column(name = "SUBJECT", nullable = false)
    private String subject;

    @Column(name = "TASK_ID", nullable = false)
    private String taskId;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @Column(name = "EMAIL_TO", nullable = false)
    private String emailTo;

    @Column(name = "EMAIL_TO_NAME")
    private String emailToName;

    @Column(name = "TYPE", nullable = false)
    private String type;

    @Column(name = "REQUESTED_AT", nullable = false)
    private Timestamp requestedAt;
}

