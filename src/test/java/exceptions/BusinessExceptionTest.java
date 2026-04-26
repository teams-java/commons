package exceptions;

import com.manu.exceptions.BusinessException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BusinessExceptionTest {

    @Test
    void businessExceptionShouldCarryMessage() {
        BusinessException ex = new BusinessException("Invalid operation");
        assertThat(ex.getMessage()).isEqualTo("Invalid operation");
    }

    @Test
    void businessExceptionShouldBeThrownAndCaught() {
        Exception thrown = assertThrows(BusinessException.class, () -> {
            throw new BusinessException("Business rule violated");
        });

        assertThat(thrown).isInstanceOf(BusinessException.class);
        assertThat(thrown.getMessage()).isEqualTo("Business rule violated");
    }
}
