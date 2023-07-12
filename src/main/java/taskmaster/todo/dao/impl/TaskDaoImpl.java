package taskmaster.todo.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import taskmaster.todo.dao.TaskDao;
import taskmaster.todo.model.Task;

@Repository
public class TaskDaoImpl extends AbstractDao<Task, Long> implements TaskDao {

    @Autowired
    protected TaskDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Task.class);
    }

}
