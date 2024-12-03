package com.aliyo.todoapp.repository;

import com.aliyo.todoapp.model.Task;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CouchbaseRepository<Task, String> {
    List<Task> findByUserId(String userId);

    List<Task> findByTaskListId(String taskListId);

    List<Task> findByUserIdAndCompleted(String userId, boolean completed);

    List<Task> findByUserIdAndTaskListId(String userId, String taskListId);

    long countByTaskListId(String taskListId);

    long countByUserIdAndCompleted(String userId, boolean completed);
}
