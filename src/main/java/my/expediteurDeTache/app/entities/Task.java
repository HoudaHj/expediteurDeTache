package my.expediteurDeTache.app.entities;

import jakarta.persistence.*;
import lombok.*;
import my.expediteurDeTache.app.dto.TaskDTO;

import java.io.Serializable;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Tasks")
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false,updatable = false)
    private Long taskId;
    @Column
    private String description;
    @Column
    private String modificationDate;
    @Column
    private long counter;


    public Task(TaskDTO taskDTO){
        this.taskId = taskDTO.getTaskId();
        this.description = taskDTO.getDescription();
        this.modificationDate = taskDTO.getModificationDate();
        this.counter = taskDTO.getCounter();
    }
}

