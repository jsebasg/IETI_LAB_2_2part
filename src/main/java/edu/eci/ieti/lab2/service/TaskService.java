package edu.eci.ieti.lab2.service;

import edu.eci.ieti.lab2.data.Task;
import java.util.List;

public interface TaskService {
    Task create(Task task );
    Task findById( String id );
    List<Task> getAll();
    boolean deleteById( String id ) throws Exception;
    Task update( Task task, String id );
}
