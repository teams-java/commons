
import com.manu.exceptions.*;
import com.manu.result.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ResultIntegrationTest {

    @Test
    void failureShouldWrapBusinessException() {
        BusinessException ex = new BusinessException("Invalid business rule");
        Failure<String> result = new Failure<>(ex);

        assertThat(result.isFailure()).isTrue();
        assertThat(result.exception()).isInstanceOf(BusinessException.class);
        assertThat(result.exception().getMessage()).isEqualTo("Invalid business rule");

        // map should propagate the same failure
        Result<Integer> mapped = result.map(String::length);
        assertThat(mapped).isInstanceOf(Failure.class);
        assertThat(((Failure<Integer>) mapped).exception()).isEqualTo(ex);
    }

    @Test
    void failureShouldWrapTechnicalException() {
        TechnicalException ex = new TechnicalException("System failure");
        Failure<String> result = new Failure<>(ex);

        assertThat(result.isFailure()).isTrue();
        assertThat(result.exception()).isInstanceOf(TechnicalException.class);
        assertThat(result.exception().getMessage()).isEqualTo("System failure");

        // flatMap should propagate the same failure
        Result<Integer> mapped = result.flatMap(s -> new Success<>(s.length()));
        assertThat(mapped).isInstanceOf(Failure.class);
        assertThat(((Failure<Integer>) mapped).exception()).isEqualTo(ex);
    }

    @Test
    void successShouldChainIntoFailure() {
        Result<String> result = new Success<>("Hello");

        // flatMap returns a Failure when mapper throws
        Result<Integer> mapped = result.flatMap(s -> new Failure<>(new BusinessException("Chained failure")));

        assertThat(mapped).isInstanceOf(Failure.class);
        assertThat(((Failure<Integer>) mapped).exception()).isInstanceOf(BusinessException.class);
        assertThat(((Failure<Integer>) mapped).exception().getMessage()).isEqualTo("Chained failure");
    }
}
