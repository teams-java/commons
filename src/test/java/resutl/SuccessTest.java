package resutl;

import com.manu.result.Result;
import com.manu.result.Success;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SuccessTest {

    @Test
    void successShouldBeRecognized() {
        Result<String> result = new Success<>("Hello");

        assertThat(result.isSuccess()).isTrue();
        assertThat(result.isFailure()).isFalse();
    }

    @Test
    void mapShouldTransformValue() {
        Result<String> result = new Success<>("Hello");
        Result<Integer> mapped = result.map(String::length);

        assertThat(mapped).isInstanceOf(Success.class);
        assertThat(((Success<Integer>) mapped).value()).isEqualTo(5);
    }

    @Test
    void flatMapShouldChainResults() {
        Result<String> result = new Success<>("Hello");
        Result<Integer> mapped = result.flatMap(s -> new Success<>(s.length()));

        assertThat(mapped).isInstanceOf(Success.class);
        assertThat(((Success<Integer>) mapped).value()).isEqualTo(5);
    }
}
