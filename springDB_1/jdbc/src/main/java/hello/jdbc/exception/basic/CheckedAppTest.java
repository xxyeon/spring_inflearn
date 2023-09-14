package hello.jdbc.exception.basic;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.net.ConnectException;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;

public class CheckedAppTest {

    @Test
    void checked() {
        Controller controller = new Controller();
        assertThatThrownBy(() -> controller.request())
                .isInstanceOf(Exception.class); //Exception이 상위 예외
    }

    static class Controller {
        Service service = new Service();
        public void request() throws SQLException, ConnectException {
            service.logic();
        }
    }

    static class Service {
        Repository repository = new Repository();
        NetworkClient networkClient = new NetworkClient();

        public void logic() throws SQLException, ConnectException {
            repository.call();
            networkClient.call();
        }

    }

    static class NetworkClient { //네트워크를 통해서 서버 호출
        public void call() throws ConnectException {
            throw new ConnectException("연결 실패");
        }

    }
    static class Repository {
        public void call() throws SQLException {
            throw new SQLException("ex");
        }

    }
}
