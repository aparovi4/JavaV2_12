import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSimpleTaskMatches() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");
        boolean actual = simpleTask.matches("Позвонить");

        Assertions.assertTrue(actual);
    }

    @Test
    public void testSimpleTaskNotMatches() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");
        boolean actual = simpleTask.matches("Написать");

        Assertions.assertFalse(actual);
    }

    @Test
    public void testEpicMatches() {

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);
        boolean actual = epic.matches("Молоко");

        Assertions.assertTrue(actual);
    }

    @Test
    public void testEpicNotMatches() {

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);
        boolean actual = epic.matches("Сыр");

        Assertions.assertFalse(actual);
    }

    @Test
    public void testMeetingMatches() {

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );
        boolean actual = meeting.matches("Приложение");

        Assertions.assertTrue(actual);
    }

    @Test
    public void testMeetingMatches2() {

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );
        boolean actual = meeting.matches("версии");

        Assertions.assertTrue(actual);
    }

    @Test
    public void testMeetingNotMatches() {

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );
        boolean actual = meeting.matches("Версии");

        Assertions.assertFalse(actual);
    }

    @Test
    public void oneTaskSearched() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] actual = todos.search("Приложение");
        Task[] expected = {meeting};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void twoTasksSearched() {
        SimpleTask simpleTask = new SimpleTask(5, "Купить Хлеб");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] actual = todos.search("Хлеб");
        Task[] expected = {simpleTask, epic};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void tasksNotSearched() {
        SimpleTask simpleTask = new SimpleTask(5, "Купить Хлеб");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] actual = todos.search("Сыр");
        Task[] expected = {};

        Assertions.assertArrayEquals(expected, actual);
    }
}