package org.hofftech.parking.processor;

import org.hofftech.parking.service.FileProcessingService;
import org.hofftech.parking.service.JsonFileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ConsoleCommandProcessorTest {

    @Mock
    private FileProcessingService fileProcessingService;

    @Mock
    private JsonFileService jsonFileService;

    @InjectMocks
    private ConsoleCommandProcessor consoleCommandProcessor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHandle_ExitCommand() {
        consoleCommandProcessor.handle("exit");
        verifyNoInteractions(fileProcessingService, jsonFileService);
    }

    @Test
    public void testHandle_ImportJsonCommand() throws IOException {
        when(jsonFileService.importJson("input.json")).thenReturn(List.of("ONE", "TWO"));

        consoleCommandProcessor.handle("import_json input.json");

        verify(jsonFileService).importJson("input.json");
    }


}
