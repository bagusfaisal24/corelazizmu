package portal.email.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailDataQueue {

    private String taskId;

    private String emailType;

    private Target to;

    private List<Target> cc;

    private List<Target> bcc;

    private String emailTemplate;

    private String subject;

    private Map<String, Object> data;

    private List<Attachment> attachments;

    public void addAttachment(Attachment attachment) {
        if (attachments == null) attachments = new ArrayList<>();
        attachments.add(attachment);
    }

    @NoArgsConstructor
    @Setter
    @Getter
    public static class Attachment {

        String name;
        String location;

        public Attachment(String name, String location) {
            this.name = name;
            this.location = location;
        }
    }

    @NoArgsConstructor
    @Setter
    @Getter
    public static class Target {

        String email;
        String name;

        public Target(String email) {
            this.email = email;
        }

        public Target(String email, String name) {
            this.email = email;
            this.name = name;
        }

    }

}
