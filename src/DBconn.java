
import java.sql.*;

public class DBconn {
    public static Connection connectDB()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/task",
                    "root", "Shanmugam");
            System.out.println(con);
            return con;
        }
        catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
            return null;
        }
    }

        public String insertEvent(Event event) throws SQLException {
            Connection conn = connectDB();
            String sql = "INSERT INTO events (event_id,name,date,location,capacity) VALUES (?, ?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1,event.getEvent_id());
                stmt.setString(2, event.getName());
                stmt.setString(3, event.getDate());
                stmt.setString(4,event.getLocation());
                stmt.setInt(5,event.getCapacity());
                stmt.executeUpdate();
                return "Event inserted successfully";

    }
    public void getEvents() throws SQLException {
        Connection conn=connectDB();
        String sql = "SELECT * FROM events";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getInt(1)+"  "+rs.getString(2)
            +"   "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getInt(5));
        }
    }
    public void UpdateEventName(int id,String name) throws SQLException {
        Connection conn = connectDB();
        String sql = "UPDATE events SET name=? WHERE event_id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,name);
        stmt.setInt(2,id);
    }
    public void UpdateEventDate(int id,String date) throws SQLException {
        Connection conn = connectDB();
        String sql = "UPDATE events SET name=? WHERE event_id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,date);
        stmt.setInt(2,id);
    }
    public void UpdateEventLocation(int id,String location) throws SQLException {
        Connection conn = connectDB();
        String sql = "UPDATE events SET name=? WHERE event_id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,location);
        stmt.setInt(2,id);
    }
    public void UpdateEventCapacity(int id,int capacity) throws SQLException {
        Connection conn = connectDB();
        String sql = "UPDATE events SET capacity=? WHERE event_id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1,capacity);
        stmt.setInt(2,id);
    }
    public void removeEvent(int id) throws SQLException {
        Connection conn = connectDB();
        String sql = "DELETE FROM events WHERE event_id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1,id);
        stmt.executeUpdate();

    }
    public String insertParticipant(Participant participant) throws SQLException {
        Connection conn = connectDB();
        String sql = "INSERT INTO participants (participant_id,name,email,phone_number) VALUES (?, ?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1,participant.getParticipant_id());
        stmt.setString(2, participant.getName());
        stmt.setString(3, participant.getEmail());
        stmt.setString(4,participant.getPhone_number());
        stmt.executeUpdate();
        return "participant added successfully";

    }
    public void getParticipants() throws SQLException {
        Connection conn=connectDB();
        String sql = "SELECT * FROM participants";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getInt(1)+"  "+rs.getString(2)
                    +"   "+rs.getString(3)+"  "+rs.getString(4));
        }
    }
    public void UpdatePartcipantName(int id,String name) throws SQLException {
        Connection conn = connectDB();
        String sql = "UPDATE events SET name=? WHERE participant_id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,name);
        stmt.setInt(2,id);
    }
    public void UpdateParticipantEmail(int id,String email) throws SQLException {
        Connection conn = connectDB();
        String sql = "UPDATE events SET email=? WHERE participant_id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,email);
        stmt.setInt(2,id);
    }

    public void UpdateParticipantPhoneNumber(int id,String phone_number) throws SQLException {
        Connection conn = connectDB();
        String sql = "UPDATE events SET phone_number=? WHERE event_id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,phone_number);
        stmt.setInt(2,id);
    }
    public void removeParticipant(int id) throws SQLException {
        Connection conn = connectDB();
        String sql = "DELETE FROM events WHERE participant_id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
    public void registerparticipantforevent(Registeration reg) throws SQLException {
        Connection conn = connectDB();
        String sql = "INSERT INTO registeration (reg_id,event_id,participant_id,date) VALUES (?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1,reg.getEvent_id());
        stmt.setInt(2,reg.getEvent_id());
        stmt.setInt(3,reg.getParticipant_id());
        stmt.setString(4,reg.getDate());

        stmt.executeUpdate();
        ChangeCapacity(reg.getEvent_id(),-1);
    }
    public void cancelregisteration(int reg_id) throws SQLException {

        Connection conn = connectDB();
        String sql="Select event_id from registeration where reg_id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1,reg_id);
        ResultSet rs = stmt.executeQuery();
        int event_id=rs.getInt(1);
        sql = "DELETE FROM registeration WHERE reg_id=?";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1,reg_id);
        stmt.executeUpdate();
        ChangeCapacity(event_id,1);
    }
    public void viewregisterationdetails() throws SQLException {
        Connection conn=connectDB();
        String sql = "SELECT * FROM registeration";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getInt(1)+"  "+rs.getInt(2)
                    +"   "+rs.getInt(3)+"  "+rs.getString(4));
        }
    }
    public void getParticipantsOfEvent(int event_id) throws SQLException {
        Connection conn = connectDB();
        String sql = "SELECT p.* FROM participant p JOIN registration r ON p.participant_id = r.participant_id WHERE r.event_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1,event_id);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getInt(1)+"  "+rs.getString(2)
                    +"   "+rs.getString(3)+"  "+rs.getString(4));
        }


    }
    public void ChangeCapacity(int event_id,int capacity) throws SQLException {
        Connection conn = connectDB();
        String sql = "SELECT capacity FROM events WHERE event_id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
            int old_capacity=rs.getInt(1);
        sql = "UPDATE events SET capacity=? WHERE event_id=?";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1,capacity+old_capacity);
    }

    }
