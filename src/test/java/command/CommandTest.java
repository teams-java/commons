package command;

import com.manu.command.Command;
import com.manu.result.*;
import com.manu.exceptions.BusinessException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CommandTest {

    @Test
    void commandShouldReturnSuccess() {
        Command<String> successCommand = () -> new Success<>("Hello Manuel");

        Result<String> result = successCommand.execute();

        assertThat(result).isInstanceOf(Success.class);
        assertThat(((Success<String>) result).value()).isEqualTo("Hello Manuel");
    }

    @Test
    void commandShouldReturnFailure() {
        Command<String> failureCommand = () -> new Failure<>(new BusinessException("Invalid operation"));

        Result<String> result = failureCommand.execute();

        assertThat(result).isInstanceOf(Failure.class);
        assertThat(((Failure<String>) result).exception()).isInstanceOf(BusinessException.class);
        assertThat(((Failure<String>) result).exception().getMessage()).isEqualTo("Invalid operation");
    }

    @Test
    void commandCanBeComposed() {
        Command<Integer> lengthCommand = () -> new Success<>("Hello").map(String::length);

        Result<Integer> result = lengthCommand.execute();

        assertThat(result).isInstanceOf(Success.class);
        assertThat(((Success<Integer>) result).value()).isEqualTo(5);
    }
}
