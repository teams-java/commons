package exceptions;

import com.manu.exceptions.TechnicalException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TechnicalExceptionTest {

    @Test
    void technicalExceptionShouldCarryMessage() {
        TechnicalException ex = new TechnicalException("System failure");
        assertThat(ex.getMessage()).isEqualTo("System failure");
    }

    @Test
    void technicalExceptionShouldBeThrownAndCaught() {
        Exception thrown = assertThrows(TechnicalException.class, () -> {
            throw new TechnicalException("Unexpected error");
        });

        assertThat(thrown).isInstanceOf(TechnicalException.class);
        assertThat(thrown.getMessage()).isEqualTo("Unexpected error");
    }
}
