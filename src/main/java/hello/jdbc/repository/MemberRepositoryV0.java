package hello.jdbc.repository;

import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;

/**
 * JDBC - Driver Manager 사용
 */

@Slf4j
public class MemberRepositoryV0 {

    // member 등록
    public Member save(Member member) throws SQLException {
        String sql = "insert into member(member_id, money) values(?, ?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);

            // 파리미터를 바인딩 해줌
            pstmt.setString(1, member.getMemberId());
            pstmt.setInt(2, member.getMoney());

            pstmt.executeUpdate();
            return member;
        } catch (SQLException e){
            log.error("db error", e);
            throw e;
        } finally {
            close(con, pstmt, null); // 꼭 마지막 절차로 close()해줘야 함.
        }
    }

    private Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }

    private void close(Connection con, Statement stmt, ResultSet rs){

        if (rs != null){
            try{
                rs.close();
            } catch (SQLException e){
                log.info("error", e);
            }
        }

        if (stmt != null){
            try{
                stmt.close();
            } catch (SQLException e){
                log.info("error", e);
            }
        }

        if (con != null){
            try{
                con.close();
            } catch (SQLException e){
                log.info("error", e);
            }
        }


    }
}
