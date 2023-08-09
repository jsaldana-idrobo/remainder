package technical.test.remainder.controller.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import technical.test.remainder.service.RemainderService;
import technical.test.remainder.service.RemainderServiceImpl;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RemainderServiceTests {

    @Mock
    private RemainderServiceImpl remainderServiceImpl;

    @InjectMocks
    private RemainderService remainderService = new RemainderServiceImpl();

    @Test
    public void testCheckDataValid() {
        when(remainderServiceImpl.checkData(10, 5, 15)).thenReturn(true);
        assertTrue(remainderService.checkData(10, 3, 15));
    }

    @Test
    public void testCheckDataInvalid() {
        when(remainderServiceImpl.checkData(5, 7, 10)).thenReturn(false);
        assertFalse(remainderService.checkData(5, 7, 10));
    }

    @Test
    public void testCalculateRemainder() {
        when(remainderServiceImpl.calculateRemainder(anyInt(), anyInt(), anyInt())).thenReturn(15);

        int result = remainderService.calculateRemainder(10, 5, 15);
        assertEquals(15, result);
    }

    @Test
    public void testCalculateRemainderInvalid() {
        when(remainderServiceImpl.calculateRemainder(anyInt(), anyInt(), anyInt())).thenReturn(0);

        int result = remainderService.calculateRemainder(2, 3, 10);
        assertEquals(0, result);
    }
}
