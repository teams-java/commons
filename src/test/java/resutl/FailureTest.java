package resutl;

import com.manu.result.Failure;
import com.manu.result.Result;
import com.manu.result.Success;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FailureTest {

    @Test
    void failureShouldBeRecognized() {
        Exception ex = new RuntimeException("Error");
        Result<String> result = new Failure<>(ex);

        assertThat(result.isSuccess()).isFalse();
        assertThat(result.isFailure()).isTrue();
    }

    @Test
    void mapShouldPropagateFailure() {
        Exception ex = new RuntimeException("Error");
        Result<String> result = new Failure<>(ex);
        Result<Integer> mapped = result.map(String::length);

        assertThat(mapped).isInstanceOf(Failure.class);
        assertThat(((Failure<Integer>) mapped).exception()).isEqualTo(ex);
    }

    @Test
    void flatMapShouldPropagateFailure() {
        Exception ex = new RuntimeException("Error");
        Result<String> result = new Failure<>(ex);
        Result<Integer> mapped = result.flatMap(s -> new Success<>(s.length()));

        assertThat(mapped).isInstanceOf(Failure.class);
        assertThat(((Failure<Integer>) mapped).exception()).isEqualTo(ex);
    }
}
