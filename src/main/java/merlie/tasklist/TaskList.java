package merlie.tasklist;

import merlie.task.Task;
import merlie.ui.Ui;
import merlie.listfile.ListFile;
import merlie.exception.MerlieException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.Iterator;

public class TaskList implements Iterable<Task> {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public void markDone(int index) {
        this.tasks.get(index).markDone();
    }

    public void markUndone(int index) {
        this.tasks.get(index).markUndone();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task delete(int index) {
        return this.tasks.remove(index);
    }

    public int size() {
        return this.tasks.size();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public Stream<Task> stream() {
        return this.tasks.stream();
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    /**
     * Checks if a new task is a duplicate of existing tasks.
     * Updates the task if necessary.
     *
     * @param newTask The task to check.
     * @param listFile ListFile object to handle saving of tasks.
     * @return true If duplicate or updated, false otherwise.
     */
    public boolean isTaskInList(Task newTask, Ui ui, ListFile listFile) throws MerlieException {
        for (Task task : this.tasks) {
            if (task.isDuplicate(newTask)) {
                ui.errorOutput(newTask.getDescription() + " is already in your list!");
                return true;
            }
            if (task.isUpdateSuccessful(newTask)) {
                listFile.save(this);
                ui.updateOutput(task);
                return true;
            }
        }
        return false;
    }

}