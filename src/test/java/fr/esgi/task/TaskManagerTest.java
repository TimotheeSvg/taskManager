package fr.esgi.task;

import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TaskManagerTest {

    private final static List<Task> tasks = new ArrayList<>() {{
        add(new Task("Test 1", false));
        add(new Task("Test 2", true));
    }};
    private final String strMenu = """
                        Choisir une action ?
                        1 - Liste des tâches
                        2 - Ajout d'une tâche
                        3 - Supprimer une tâche
                        4 - Noter une tâche comme finie
                        5 - Quitter""";
    private final String strTryAgain = "Mauvaise saisie";
    private final String strListe = "Liste des tâches :";
    private final String strAjout = "Ajout d'une tâche, Veuillez saisir la description: ";
    private final String strAjoutValide = "Tâche ajoutée !";
    private final String strFini = "Saisir la tâche qui à été compléter: ";
    private final String strFiniValide = "Tâche fini !";
    private final String strSupp = "Saisir la tâche à supprimer: ";
    private final String strSuppValide = "Tâche supprimée !";
    private final String strQuitter = "Vous avez quitter...";

    @Test
    @Order(1)
    void testInit(){
        IConsoleManager consoleMock = mock(IConsoleManager.class);
        when(consoleMock.ReadLong())
                .thenReturn(1L)
                .thenReturn(5L);

        TaskList taskList = new TaskList(tasks);

        TaskManager taskManager = new TaskManager(consoleMock, taskList);
        taskManager.start();
        ArgumentCaptor<String> argsCaptor = ArgumentCaptor.forClass(String.class);
        verify(consoleMock, times(6)).WriteLine(argsCaptor.capture());
        List<String> args = argsCaptor.getAllValues();
        assertEquals(strMenu, args.get(0));
        assertEquals(strQuitter, args.get(5));
    }

    @Test
    @Order(2)
    void testAdd(){
        IConsoleManager consoleMock = mock(IConsoleManager.class);
        when(consoleMock.ReadLong())
                .thenReturn(2L)
                .thenReturn(5L);

        TaskList taskList = new TaskList(tasks);

        TaskManager taskManager = new TaskManager(consoleMock, taskList);
        taskManager.start();

        ArgumentCaptor<String> argsCaptor = ArgumentCaptor.forClass(String.class);
        verify(consoleMock, times(5)).WriteLine(argsCaptor.capture());

        List<String> args = argsCaptor.getAllValues();
        assertEquals(strMenu, args.get(0));
        assertEquals(strAjout, args.get(1));
        assertEquals(strAjoutValide, args.get(2));
        assertEquals(strMenu, args.get(3));
        assertEquals(strQuitter, args.get(4));
    }

    @Test
    @Order(3)
    void testSupprime(){
        IConsoleManager consoleManagerMock = mock(IConsoleManager.class);
        when(consoleManagerMock.ReadLong())
                .thenReturn(3L)
                .thenReturn(1L)
                .thenReturn(5L);

        TaskList taskListStub = new TaskList(tasks);

        TaskManager target = new TaskManager(consoleManagerMock, taskListStub);

        target.start();

        ArgumentCaptor<String> argsCaptor = ArgumentCaptor.forClass(String.class);
        verify(consoleManagerMock, times(9)).WriteLine(argsCaptor.capture());
        List<String> args = argsCaptor.getAllValues();
        assertEquals(strMenu, args.get(0));
        assertEquals(tasks.get(0).toString(), args.get(1));
        assertEquals(tasks.get(1).toString(), args.get(2));
        assertEquals(strSupp, args.get(5));
        assertEquals(strSuppValide, args.get(6));
        assertEquals(strMenu, args.get(7));
        assertEquals(strQuitter, args.get(8));
    }

    @Test
    @Order(4)
    void testComplete(){
        IConsoleManager consoleManagerMock = mock(IConsoleManager.class);
        List<Task> tasksTarget = new ArrayList<>() {{
            add(new Task("tâche finie rapidement", false));
        }};

        when(consoleManagerMock.ReadLong())
                .thenReturn(4L)
                .thenReturn(tasksTarget.get(0).getId())
                .thenReturn(5L);




        TaskList tasksList = new TaskList(tasksTarget);

        TaskManager taskManager = new TaskManager(consoleManagerMock, tasksList);

        taskManager.start();

        ArgumentCaptor<String> argsCaptor = ArgumentCaptor.forClass(String.class);
        verify(consoleManagerMock, times(6)).WriteLine(argsCaptor.capture());
        List<String> args = argsCaptor.getAllValues();

        assertEquals(strMenu, args.get(0));
        assertEquals(tasksTarget.get(0).toString(), args.get(1));
        assertEquals(strFini, args.get(2));
        assertEquals(strFiniValide, args.get(3));
        assertEquals(strMenu, args.get(4));
        assertEquals(strQuitter, args.get(5));
    }
}