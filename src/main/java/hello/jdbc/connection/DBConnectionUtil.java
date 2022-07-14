package hello.jdbc.connection;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;

@Slf4j
public class DBConnectionUtil {
    // static 사용 이유: 객체를 호출하지 않아도 사용 가능
    public static Connection getConnection(){
        try {
            // JDBC가 제공하는 DriverManager에서 getConnection을 사용하면
            // 라이브러리에 있는 h2 드라이버를 찾아서 h2 드라이버가 제공하는 커넥션 반환
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            log.info("get connection={}, class={}", connection, connection.getClass());
            return connection;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

    }
}
