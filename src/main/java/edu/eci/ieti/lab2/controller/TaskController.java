package edu.eci.ieti.lab2.controller;

import edu.eci.ieti.lab2.data.Task;
import edu.eci.ieti.lab2.dto.TaskDto;
import edu.eci.ieti.lab2.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping( "/v1/task" )
public class TaskController {
    private final TaskService taskService;
    private final AtomicLong counter = new AtomicLong(0);

    public TaskController(@Autowired TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping
    public ResponseEntity<List<Task>> getAll() {

        return new ResponseEntity<List<Task>>(taskService.getAll() , HttpStatus.OK );

    }

    @GetMapping( "/{id}" )
    public ResponseEntity<Task> findById(@PathVariable String id ) {


        return new ResponseEntity<Task>(taskService.findById(id) , HttpStatus.OK );
    }


    @PostMapping
    public ResponseEntity<Task> create( @RequestBody TaskDto taskDto ) {

        Task userCreation = new Task((Integer.toString((int) counter.incrementAndGet())), taskDto.getName() , taskDto.getDescription() ,
                taskDto.getStatus(), taskDto.getAssignedTo() , taskDto.getDueDate(),  LocalDateTime.now().toString());

        return new ResponseEntity<Task>(taskService.create(userCreation), HttpStatus.OK );
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<Task> update( @RequestBody TaskDto taskDto, @PathVariable String id ) {
        Task task = taskService.findById(id);
        task.setAssignedTo(taskDto.getAssignedTo());
        task.setDescription(taskDto.getDescription());
        task.setDueDate(taskDto.getDueDate());
        task.setName(taskDto.getName());
        return new ResponseEntity<Task>(taskService.update(task , id ), HttpStatus.OK );
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<Boolean> delete( @PathVariable String id ) throws Exception {
        return new ResponseEntity<Boolean>(taskService.deleteById(id), HttpStatus.OK);
    }
}
