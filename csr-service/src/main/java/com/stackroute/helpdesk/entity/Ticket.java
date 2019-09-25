package com.stackroute.helpdesk.entity;
import lombok.*;
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
@AllArgsConstructor
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
    private Date timeCreated;
    private Date timeResolved;
    private Date assignMeTime;
    private List<String> commandsUsed;
    private String type;
    private String solvedBy;
    private List<String> tags;

    public Ticket(String description, String usermail, String status, int rating, Date timeCreated, Date timeResolved, Date assignMeTime, List<String> commandsUsed, String type, String solvedBy, List<String> tags) {
        this.description = description;
        this.usermail = usermail;
        this.status = status;
        this.rating = rating;
        this.timeCreated = timeCreated;
        this.timeResolved = timeResolved;
        this.assignMeTime = assignMeTime;
        this.commandsUsed = commandsUsed;
        this.type = type;
        this.solvedBy = solvedBy;
        this.tags = tags;
    }
}