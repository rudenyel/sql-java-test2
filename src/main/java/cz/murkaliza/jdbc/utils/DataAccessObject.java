package cz.murkaliza.jdbc.utils;

import java.sql.*;
import java.util.List;

public abstract class DataAccessObject <T extends DataTransferObject> {

    protected final Connection connection;
    protected final static String LAST_ID = "SELECT seq FROM sqlite_sequence WHERE name = '%s'";
    public DataAccessObject(Connection connection) {
        super();
        this.connection = connection;
    }

    public abstract T findById(long id);
    public abstract List<T> findAll();
    public abstract T update(T dto);
    public abstract T create(T dto);
    public abstract void delete(long id);

    protected int getLastId(String sequence) {
        int key = 0;
        String sql = String.format(LAST_ID, sequence);
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                key = rs.getInt(1);
            }
            return key;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
