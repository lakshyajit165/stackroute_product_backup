package com.stackroute.helpdesk.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Document(collection = "tickets")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Ticket {

    //    @NotBlank
//    @Size(max=100)
//    @Indexed(unique=true)
    @Id
    private String id;
    private String description;
    private String usermail;
    private String status;
    private int rating;
    private Date time_created;
    private Date time_resolved;
    private List<String> commands_used;
    private String type;
    private String solved_by;
    private List<String> tags;

    public Ticket(@NotBlank @Size(max = 100) String description, String usermail, String status, int rating, Date time_created, Date time_resolved, List<String> commands_used, String type, String solved_by, List<String> tags) {
        this.description = description;
        this.usermail = usermail;
        this.status = status;
        this.rating = rating;
        this.time_created = time_created;
        this.time_resolved = time_resolved;
        this.commands_used = commands_used;
        this.type = type;
        this.solved_by = solved_by;
        this.tags = tags;
    }
}
